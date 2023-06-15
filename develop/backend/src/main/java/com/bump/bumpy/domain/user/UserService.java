package com.bump.bumpy.domain.user;

import com.bump.bumpy.database.entity.usr.UsrMUsr;
import com.bump.bumpy.database.repository.usr.UsrMUsrRepository;
import com.bump.bumpy.domain.user.dto.UserUpdateDto;
import com.bump.bumpy.util.dto.PasswordDto;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsrMUsrRepository usrMUsrRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<ResultMap> entranceCheck(PasswordDto request) {
        UsrMUsr user = usrMUsrRepository.findByUserId(getUserId());

        // null check
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResultMap());
        }

        if(passwordEncoder.matches(request.getPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap());
    }

    public ResponseEntity<ResultMap> update(UserUpdateDto request) {

        // update
        UsrMUsr user = usrMUsrRepository.findByUserId(getUserId());
        if(user == null) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }

        user = request.updateEntity(user);

        usrMUsrRepository.save(user);

        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }

    public ResponseEntity<ResultMap> delete() {
        UsrMUsr user = usrMUsrRepository.findByUserId(getUserId());

        usrMUsrRepository.delete(user);

        return ResponseEntity.ok(new ResultMap("message", "OK"));
    }

    public void updatePassword(@Valid PasswordDto passwordDto) {
        UsrMUsr user = usrMUsrRepository.findByUserId(getUserId());

        // null check
        if (user == null) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }

        // Encoding Password
        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));

        // update
        usrMUsrRepository.save(user);
    }
}
