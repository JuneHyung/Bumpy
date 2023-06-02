package com.bump.bumpy.domain.signup;

import com.bump.bumpy.database.entity.usr.UsrHEmailauth;
import com.bump.bumpy.database.repository.usr.UsrHEmailauthRepository;
import com.bump.bumpy.database.repository.usr.UsrMUsrRepository;
import com.bump.bumpy.domain.signup.dto.EmailRequestDto;
import com.bump.bumpy.domain.signup.dto.UserIdRequestDto;
import com.bump.bumpy.domain.signup.dto.UsrMUsrDto;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bump.bumpy.constants.DomainConstants.EMAIL_VERIFY_CODE_CHACTERS;
import static com.bump.bumpy.constants.DomainConstants.EMAIL_VERIFY_CODE_LENGTH;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UsrMUsrRepository usrMUsrRepository;
    private final UsrHEmailauthRepository usrHEmailauthRepository;
    private final PasswordEncoder passwordEncoder;
//    private final JavaMailSender javaMailSender;

    public ResponseEntity<ResultMap> userCheck(UserIdRequestDto request) {
        if(usrMUsrRepository.existsByUserId(request.getUserId()) || request.getUserId() == "anonymousUser")
            return ResponseEntity.badRequest().body(new ResultMap("message", "이미 존재하는 아이디입니다."));
        return ResponseEntity.ok(new ResultMap("message", "사용 가능한 아이디입니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> sendEmail(EmailRequestDto request) {
        usrHEmailauthRepository.deleteByUserId(request.getUserId());

        String verificationCode = null;

        do {
            verificationCode = RandomStringUtils.random(EMAIL_VERIFY_CODE_LENGTH, EMAIL_VERIFY_CODE_CHACTERS);
        } while (usrHEmailauthRepository.existsByToken(verificationCode));

        UsrHEmailauth usrHEmailauth = UsrHEmailauth.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .token(verificationCode)
                .build();

        usrHEmailauthRepository.save(usrHEmailauth);

        return ResponseEntity.ok(new ResultMap("verificationCode", verificationCode));

        // TODO : 이메일 인증 기능 구현

//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo(request.getEmail());
//        mailMessage.setSubject("[Bumpy] 이메일 인증");
//        mailMessage.setText("인증번호 : " + verificationCode);
//
//        javaMailSender.send(mailMessage);
        //        return ResponseEntity.ok(new ResultMap("message", "OK"));

    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> validateEmail(EmailRequestDto request) {
        if(!usrHEmailauthRepository.existsByUserIdAndTokenAndEmail(request.getUserId(), request.getVerifyCode(), request.getEmail()))
            return ResponseEntity.badRequest().body(new ResultMap("message", "인증번호가 일치하지 않습니다."));
        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }

    public ResponseEntity<ResultMap> submit(UsrMUsrDto request) {
        if(usrMUsrRepository.existsByUserId(request.getUserId()))
            return ResponseEntity.badRequest().body(new ResultMap("message", "이미 존재하는 아이디입니다."));
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        usrMUsrRepository.save(request.toEntity(request));
        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }
}
