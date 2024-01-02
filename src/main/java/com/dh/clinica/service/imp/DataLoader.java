package com.dh.clinica.service.imp;

import com.dh.clinica.model.AppUser;
import com.dh.clinica.model.AppUserRole;
import com.dh.clinica.repository.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");
        BCryptPasswordEncoder passwordEncoder3 = new BCryptPasswordEncoder();
        String hashedPassword3 = passwordEncoder2.encode("admin");
        userRepository.save(new AppUser("admin", "admin", "admin", hashedPassword3, AppUserRole.ADMIN));
        String hashedPassword4 = passwordEncoder2.encode("user");
        userRepository.save(new AppUser("user", "user", "user", hashedPassword4, AppUserRole.USER));
        userRepository.save(new AppUser("Diego", "diego", "diego@digital.com", hashedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser("Paula", "paula", "paula@digital.com", hashedPassword2, AppUserRole.USER));
    }
}
