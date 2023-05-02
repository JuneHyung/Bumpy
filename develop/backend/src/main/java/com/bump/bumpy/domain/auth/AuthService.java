package com.bump.bumpy.domain.auth;

import com.bump.bumpy.database.entity.UsrMUsr;
import com.bump.bumpy.database.repository.UsrMUsrRepository;
import com.bump.bumpy.domain.auth.dto.request.LoginRequest;
import com.bump.bumpy.security.principal.PrincipalDetails;
import com.bump.bumpy.security.principal.PrincipalDetailsService;
import com.bump.bumpy.util.dto.StringMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

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
    }

}
