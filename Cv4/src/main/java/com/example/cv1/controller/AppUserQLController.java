package com.example.cv1.controller;

import com.example.cv1.domain.AppUser;
import com.example.cv1.repository.AppUserRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AppUserQLController {
    private final AppUserRepository userRepository;

    public AppUserQLController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    Iterable<AppUser> users(){
        return userRepository.findAll();
    }
}
