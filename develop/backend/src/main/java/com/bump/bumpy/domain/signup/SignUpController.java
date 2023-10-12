package com.bump.bumpy.domain.signup;

import com.bump.bumpy.domain.signup.dto.EmailRequestDto;
import com.bump.bumpy.domain.signup.dto.UserIdRequestDto;
import com.bump.bumpy.domain.signup.dto.UsrMUsrDto;
import com.bump.bumpy.util.dto.PasswordDto;
import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @Operation(summary = "아이디 중복검사", description = "")
    @PostMapping("/user/check")
    public ResponseEntity<ResultMap> userCheck(@RequestBody UserIdRequestDto request) {
        return signUpService.userCheck(request);
    }

    @Operation(summary = "비밀번호 유효성 검사", description = "")
    @PostMapping("/password/check")
    public ResponseEntity<ResultMap> passwordCheck(@RequestBody @Valid PasswordDto request, BindingResult result) {
        if(result.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", result.getAllErrors().get(0).getDefaultMessage()));
        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }

    @Operation(summary = "인증용 이메일 발송", description = "")
    @PostMapping("/email/send")
    public ResponseEntity<ResultMap> sendEmail(@RequestBody EmailRequestDto request) {
        return signUpService.sendEmail(request);
    }

    @Operation(summary = "이메일 인증", description = "")
    @PostMapping("/email/validate")
    public ResponseEntity<ResultMap> validateEmail(@RequestBody EmailRequestDto request) {
        return signUpService.validateEmail(request);
    }

    @Operation(summary = "회원가입", description = "")
    @PostMapping("/submit")
    public ResponseEntity<ResultMap> submit(@RequestBody @Valid UsrMUsrDto request, BindingResult result) {
        if(result.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", result.getAllErrors().get(0).getDefaultMessage()));
        return signUpService.submit(request);
    }
}
