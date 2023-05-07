package com.bump.bumpy.domain.auth;

import com.bump.bumpy.domain.auth.dto.request.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "로그인", description = "form 활용 로그인")
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest httpServletRequest, @RequestBody LoginRequest request) {

        // 사용자를 인증합니다.
        UsernamePasswordAuthenticationToken authReq =
                new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authReq);

        // 인증 결과를 SecurityContextHolder에 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 세션에 로그인 정보를 저장합니다.
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        session.setAttribute("userId",
                request.getUserId());

        return ResponseEntity.ok("main");
    }
}
