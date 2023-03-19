package com.example.cv1.controller;

import com.example.cv1.Services.AppUserService;
import com.example.cv1.domain.AppUser;
import com.example.cv1.dto.AppUserInputQLDTO;
import com.example.cv1.repository.AppUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RestController
public class AppUserQLController {
    private final AppUserRepository userRepository;
    private final AppUserService userService;

    @QueryMapping
    Iterable<AppUser> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Iterable<AppUser> appUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Optional<AppUser> appUserById(@Argument long id) {
        return userRepository.findById(id);
    }

    @MutationMapping
    public AppUser createAppUser(@Argument AppUserInputQLDTO appUser) {
        return userService.create(appUser.toEntity());

    }
}
