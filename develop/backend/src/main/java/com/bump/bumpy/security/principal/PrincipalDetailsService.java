package com.bump.bumpy.security.principal;

import com.bump.bumpy.database.entity.UsrMUsr;
import com.bump.bumpy.database.repository.UsrMUsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UsrMUsrRepository usrMUsrRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsrMUsr usrMUsr = usrMUsrRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("유저 없음"));
        return new PrincipalDetails(usrMUsr);
    }
}
