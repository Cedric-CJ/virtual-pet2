package com.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer wurde nicht gefunden: " + username));

        return new User(applicationUser.getUsername(), applicationUser.getPassword(), Collections.emptyList());
    }
}
