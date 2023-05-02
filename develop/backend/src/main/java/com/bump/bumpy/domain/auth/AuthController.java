package com.bump.bumpy.domain.auth;

import com.bump.bumpy.database.entity.UsrMUsr;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "로그인", description = "form 활용 로그인")
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        UsrMUsr usrMUsr = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UsrMUsr) {
            usrMUsr = (UsrMUsr) principal;
        }
        if(usrMUsr != null) {
            return ResponseEntity.ok("Authenticated");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Denied");
        }
//        return authService.login();
    }
}
