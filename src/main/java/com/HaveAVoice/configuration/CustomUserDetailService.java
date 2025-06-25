package com.HaveAVoice.configuration;

import com.HaveAVoice.User.UserDB;
import com.HaveAVoice.User.DAL.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

    @Service
    public class CustomUserDetailService implements UserDetailsService {
        private final UserRepository userRepository;

        public CustomUserDetailService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserDB userDB =  userRepository.findByUsername(username);
            return new User(userDB.getUsername(), userDB.getPassword(), getGrantedAuthorities(userDB.getRoles()) );
        }

        private List<GrantedAuthority> getGrantedAuthorities(String roles) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_%s".formatted(roles)));
            return authorities;
        }
    }
