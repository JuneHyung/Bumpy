package com.bump.bumpy.domain.auth;

import com.bump.bumpy.constants.JwtConstants;
import com.bump.bumpy.domain.auth.dto.request.LoginRequest;
import com.bump.bumpy.domain.auth.dto.response.AuthResponse;
import com.bump.bumpy.util.dto.StringMap;
import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @Value("${cookie.domain}")
    private String DOMAIN;
    private static final Boolean HTTP_ONLY = true;
    private static final Boolean SECURE = false;

    @Operation(summary = "로그인", description = "로그인 후 토큰 발급")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request, HttpServletResponse httpServletResponse) {
        StringMap map = authService.login(request);
        AuthResponse authResponse = AuthResponse.builder().accessToken(map.get("accessToken")).build();

        httpServletResponse.setHeader("Set-Cookie", makeCookie(map.get("refreshToken")).toString());

        return ResponseEntity.ok(authResponse);
    }

    @Operation(summary = "재발급", description = "토큰 만료 후 재발급")
    @GetMapping("/reissue")
    public ResponseEntity<AuthResponse> reissue(@CookieValue(value = "refreshToken", required = false) String token, HttpServletResponse httpServletResponse) {
        StringMap map = authService.reissue(token);
        AuthResponse authResponse = AuthResponse.builder().accessToken(map.get("accessToken")).build();

        httpServletResponse.setHeader("Set-Cookie", makeCookie(map.get("refreshToken")).toString());

        return ResponseEntity.ok(authResponse);
    }

    @Operation(summary = "로그아웃", description = "로그아웃 및 토큰 삭제")
    @GetMapping("/logout")
    public ResponseEntity<StringMap> logout(@CookieValue(value = "refreshToken", required = false) String token) {
        authService.logout(token);
        StringMap stringMap = new StringMap();
        stringMap.putMsg("로그아웃");
        return ResponseEntity.ok(stringMap);
    }

    private ResponseCookie makeCookie (String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .domain(DOMAIN)
                .sameSite(Cookie.SameSite.NONE.attributeValue())
                .path("/")
                .httpOnly(HTTP_ONLY)
                .secure(SECURE)
                .maxAge((int) JwtConstants.REFRESH_TOKEN_EXPIRY)
                .build();
    }
}
