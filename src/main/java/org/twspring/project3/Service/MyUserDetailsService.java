package org.twspring.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.twspring.project3.Model.User;
import org.twspring.project3.Repository.AuthRepository;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
