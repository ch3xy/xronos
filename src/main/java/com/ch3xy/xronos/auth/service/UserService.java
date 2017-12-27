package com.ch3xy.xronos.auth.service;

import com.ch3xy.xronos.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private UserDetailsConverter userDetailsConverter;

    private EncryptionService encryptionService;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserDetailsConverter userDetailsConverter,
                       EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.userDetailsConverter = userDetailsConverter;
        this.encryptionService = encryptionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return userDetailsConverter.convert(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean exists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    @Transactional
    public User saveOrUpdate(User user) {
        if (user.getPassword() != null) {
            user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
