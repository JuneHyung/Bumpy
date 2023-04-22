package com.bump.bumpy.domain.auth;

import com.bump.bumpy.database.entity.UsrMUsr;
import com.bump.bumpy.database.repository.UsrMUsrRepository;
import com.bump.bumpy.domain.auth.dto.request.LoginRequest;
import com.bump.bumpy.security.jwt.JwtProvider;
import com.bump.bumpy.security.principal.PrincipalDetails;
import com.bump.bumpy.security.principal.PrincipalDetailsService;
import com.bump.bumpy.util.dto.StringMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsrMUsrRepository usrMUsrRepository;
    private final AuthenticationManager authenticationManager;
    private final PrincipalDetailsService principalDetailsService;
    private final JwtProvider jwtProvider;
    private final JwtDecoder jwtDecoder;
//    private final CmMTokenRepository tokenStoreRepository;

    /**
     * Map형태로 반환 (accessToken, refreshToken)
     * @param request
     * @return StringMap
     */
    public StringMap login(LoginRequest request) {
        StringMap resultMap = new StringMap();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword()));
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();

        resultMap.put("accessToken", jwtProvider.makeAccessToken(userDetails));
        resultMap.put("refreshToken", jwtProvider.makeRefreshToken(userDetails));

        return resultMap;
    }

    /**
     * 리프레시 토큰 decode -> 유저확인 -> 만료확인 -> 토큰일치여부 -> 재발급
     * Map형태로 반환 (accessToken, refreshToken)
     * @param token
     * @return ResultMap
     */
    public StringMap reissue(String token) {
        StringMap stringMap = new StringMap();
        try {
            String subject = tokenCheckGetSubject(token);

            UsrMUsr usrMUsr = usrMUsrRepository.findById(subject).orElseThrow(() -> new BadCredentialsException("유저 없음"));
            PrincipalDetails details = (PrincipalDetails) principalDetailsService.loadUserByUsername(usrMUsr.getUserId());

            stringMap.put("accessToken", jwtProvider.makeAccessToken(details));
            stringMap.put("refreshToken", jwtProvider.makeRefreshToken(details));

            return stringMap;
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("재발급 실패 : " + ex.getMessage());
        } catch (JwtException ex) {
            throw new BadCredentialsException("JWT 인증 불가");
        }
    }

    public void logout(String token) {
        String subject = tokenCheckGetSubject(token);
        jwtProvider.removeRefreshToken(subject);
    }

    private String tokenCheckGetSubject(String token) {
        if(token == null || token.isEmpty()) { throw new BadCredentialsException("토큰이 비어있음"); }

        Jwt decode = jwtDecoder.decode(token);

        String subject = decode.getSubject();

        if(subject == null || subject.length() == 0) {
            throw new BadCredentialsException("토큰이 비어있음");
        }

        Instant expires = decode.getExpiresAt();

        if(expires != null) {
            if(expires.isBefore(Instant.now())) {
                throw new BadCredentialsException("토큰이 만료됨");
            }
        } else {
            throw new BadCredentialsException("토큰이 위조됨");
        }

        String storedToken = jwtProvider.getRefreshToken(subject);

        if(!storedToken.equals(decode.getTokenValue())) {
            throw new BadCredentialsException("토큰값 불일치");
        }

        return decode.getSubject();
    }
}
