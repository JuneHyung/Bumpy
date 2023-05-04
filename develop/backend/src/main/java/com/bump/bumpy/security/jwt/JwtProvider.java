package com.bump.bumpy.security.jwt;

import com.bump.bumpy.constants.JwtConstants;
import com.bump.bumpy.database.repository.CmMTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtEncoder jwtEncoder;
    private CmMTokenRepository cmMTokenRepository;
    private static final String ROLES = "roles";

    /**
     * UserDetails -> Authorities, subject로 AccessToken 생성
     * @param details
     * @return String
     */
    public String makeAccessToken(UserDetails details) {
        // Token 제작 및 return
        Instant now = Instant.now();
        Instant accessTokenExpire = now.plusSeconds(JwtConstants.ACCESS_TOKEN_EXPIRY);

        String scope = details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("bumpy")
                .issuedAt(now)
                .expiresAt(accessTokenExpire)
                .subject(format("%s", details.getUsername())) //getName인지 확인
                .claim(ROLES, scope)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * RefreshToken
     * @param subject
     * @return String
     */
    public String makeRefreshToken(UserDetails details) {
        // Token 제작 및 return
        Instant now = Instant.now();
        Instant refreshTokenExpire = now.plusSeconds(JwtConstants.REFRESH_TOKEN_EXPIRY);

        JwtClaimsSet refreshClaims = JwtClaimsSet.builder()
                .issuer("bumpy")
                .issuedAt(now)
                .expiresAt(refreshTokenExpire)
                .subject(format("%s", details.getUsername()))
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(refreshClaims)).getTokenValue();
    }

    /**
     * 저장된 refreshToken
     * @param subject
     * @return String
     */
    public String getRefreshToken(String subject) {
        return cmMTokenRepository.findByUserId(subject).orElseThrow(() -> new BadCredentialsException("저장된 토큰 없음")).getRefreshToken();
    }

    public long removeRefreshToken(String subject) {
        return cmMTokenRepository.deleteByUserId(subject);
    }
}
