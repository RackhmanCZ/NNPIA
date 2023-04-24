package com.example.cv1.controller;

import com.example.cv1.domain.AppUser;
import com.example.cv1.repository.AppUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/app-user")
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    private AppUserRepository appUserRepository;

    @GetMapping(value={"/app-user/{id}"})
    public String getUserById(@PathVariable(value="id") final int id) {
        AppUser user = appUserRepository.getUserById(id);
        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return user.toString();
    }

    @GetMapping("")
    public List<AppUser> findAll() {
        return appUserRepository.findAllByActiveEquals(true);
    }

    @GetMapping(value={"/app-user/findByUsername/{username}"})
    public AppUser findByUsername(String username) {
        return appUserRepository.getUserByUsername(username);
    }
}
