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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Calendar;
import java.util.Objects;

import static com.bump.bumpy.constants.DomainConstants.EMAIL_VERIFY_CODE_CHACTERS;
import static com.bump.bumpy.constants.DomainConstants.EMAIL_VERIFY_CODE_LENGTH;
import static com.bump.bumpy.util.funtion.FieldValueUtil.isTodayDate;
import static com.bump.bumpy.util.funtion.UserIdValidator.UserIdIsValid;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UsrMUsrRepository usrMUsrRepository;
    private final UsrHEmailauthRepository usrHEmailauthRepository;
    private final PasswordEncoder passwordEncoder;
//    private final JavaMailSender javaMailSender;

    public ResponseEntity<ResultMap> userCheck(UserIdRequestDto request) {
        if(!UserIdIsValid(request.getUserId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", "아이디는 5~20 글자로 영문자, 숫자만 사용 가능합니다."));
        if(usrMUsrRepository.existsByUserId(request.getUserId()) || Objects.equals(request.getUserId(), "anonymousUser"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 사용중인 아이디입니다."));
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
                .isValid(false)
                .tokenIsuDate(Calendar.getInstance().getTime())
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", "인증번호가 일치하지 않습니다."));

        UsrHEmailauth usrHEmailauth = usrHEmailauthRepository.findByUserIdAndTokenAndEmail(request.getUserId(), request.getVerifyCode(), request.getEmail());

        if(!isTodayDate(usrHEmailauth.getTokenIsuDate()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", "인증번호가 만료되었습니다."));

        usrHEmailauth.setIsValid(true);
        usrHEmailauth.setValidationDate(Calendar.getInstance().getTime());
        usrHEmailauthRepository.save(usrHEmailauth);
//        usrHEmailauthRepository.deleteByUserId(request.getUserId());
        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }

    public ResponseEntity<ResultMap> submit(UsrMUsrDto request) {
        if(usrMUsrRepository.existsByUserId(request.getUserId()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResultMap("message", "이미 사용중인 아이디입니다."));
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // 유저 아이디 검증
        if(Boolean.FALSE.equals(UserIdIsValid(request.getUserId())))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", "아이디는 5~20 글자로 영문자, 숫자만 사용 가능합니다."));

        // 이메일 인증
        UsrHEmailauth tokenAuth = usrHEmailauthRepository.findByUserIdAndEmail(request.getUserId(), request.getEmail());
        
        if(tokenAuth == null) // 이메일 인증을 진행하지 않음
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", "이메일 인증을 진행해주세요."));

        if(!isTodayDate(tokenAuth.getValidationDate())) // 이메일 인증 후 날짜가 바뀜
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", "이메일 인증을 진행해주세요."));
        
        if(Boolean.FALSE.equals(tokenAuth.getIsValid())) // 이메일 인증을 통과하지 않음
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", "이메일 인증을 진행해주세요."));

        usrMUsrRepository.save(request.toEntity(request));
        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }
}
