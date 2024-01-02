package com.dh.clinica.service.imp;


import com.dh.clinica.model.AppUser;
import com.dh.clinica.repository.impl.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(AppUserService.class);
    private final com.dh.clinica.repository.impl.UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> listar(){
        List<AppUser> users = userRepository.findAll();
        LOGGER.info(users.toString());
        return  users;
    }
    @Override
    public UserDetails loadUserByUsername(String email){
        return userRepository.findByEmail(email).get();
    }
}
