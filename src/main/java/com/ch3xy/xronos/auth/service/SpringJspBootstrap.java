package com.ch3xy.xronos.auth.service;

import com.ch3xy.xronos.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringJspBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${security.admin.username}")
    private String adminUsername;

    @Value("${security.admin.password}")
    private String adminPassword;

    private UserService userService;

    @Autowired
    public SpringJspBootstrap(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadAdminUser();
    }

    private void loadAdminUser() {
        User admin = userService.getUser(adminUsername);
        if (admin != null) {
            return;
        }
        admin = new User();
        admin.setUsername(adminUsername);
        admin.setPassword(adminPassword);
        userService.saveOrUpdate(admin);
    }
}
