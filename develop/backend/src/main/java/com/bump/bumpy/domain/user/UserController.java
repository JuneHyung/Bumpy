package com.bump.bumpy.domain.user;

import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "진입 확인용 API", description = "")
    @PostMapping("/check")
    public ResponseEntity<ResultMap> check() {
        String userId = getUserId();
        return userService.check();
    }

    @Operation(summary = "비밀번호 유효성검사", description = "")
    @PostMapping("/password/check")
    public ResponseEntity<ResultMap> insert() {
        return userService.passwordCheck();
    }

    @Operation(summary = "사용자정보 업데이트", description = "")
    @PostMapping("/update")
    public ResponseEntity<ResultMap> update() {
        return userService.update();
    }

    @Operation(summary = "회원 탈퇴", description = "")
    @GetMapping("/delete")
    public ResponseEntity<ResultMap> delete() {
        return userService.delete();
    }
}
