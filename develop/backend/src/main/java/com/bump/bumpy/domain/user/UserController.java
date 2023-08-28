package com.bump.bumpy.domain.user;

import com.bump.bumpy.domain.user.dto.UserUpdateDto;
import com.bump.bumpy.util.dto.PasswordDto;
import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "진입 확인용 API", description = "")
    @PostMapping("/check")
    public ResponseEntity<ResultMap> entranceCheck(@RequestBody PasswordDto request) {
        return userService.entranceCheck(request);
    }

    @Operation(summary = "비밀번호 유효성검사", description = "")
    @PostMapping("/password/check")
    public ResponseEntity<ResultMap> insert(@RequestBody @Valid PasswordDto request, BindingResult result) {
        if(result.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", result.getAllErrors().get(0).getDefaultMessage()));
        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }

    @Operation(summary = "사용자정보 업데이트", description = "")
    @PutMapping("/update")
    public ResponseEntity<ResultMap> update(@RequestBody @Valid UserUpdateDto request) {
        if(request.getPassword() != null) {
            PasswordDto passwordDto = new PasswordDto();
            passwordDto.setPassword(request.getPassword());
            userService.updatePassword(passwordDto);
        }

        return userService.update(request);
    }

    @Operation(summary = "회원 탈퇴", description = "")
    @DeleteMapping("/delete")
    public ResponseEntity<ResultMap> delete(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        new CookieClearingLogoutHandler("JSESSIONID").logout(request, response, authentication);
        return userService.delete();
    }

    @Operation(summary = "사용자 정보 조회", description = "")
    @GetMapping("/info")
    public ResponseEntity<ResultMap> info() {
        return userService.info(getUserId());
    }

    @Operation(summary = "로그인 상태 확인", description = "")
    @GetMapping("/heartbeat")
    public ResponseEntity<ResultMap> heartbeat() {
        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }
}
