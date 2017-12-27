package com.ch3xy.xronos.auth.service;

import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.auth.model.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDetailsConverter implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        if (user != null) {
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getEncryptedPassword());
            userDetails.setAuthorities(new ArrayList<>());
        }
        return userDetails;
    }
}
