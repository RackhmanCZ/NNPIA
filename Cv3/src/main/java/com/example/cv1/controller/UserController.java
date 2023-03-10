package com.example.cv1.controller;

import com.example.cv1.domain.AppUser;
import com.example.cv1.repository.AppUserRepository;
import com.example.cv1.security.AuthenticationRequest;
import com.example.cv1.security.AuthenticationResponse;
import com.example.cv1.security.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app-user")
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    private AppUserRepository appUserRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping(value={"/app-user/{id}"})
    public String getUserById(@PathVariable(value="id") final int id) {
        AppUser user = appUserRepository.getUserById(id);
        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return user.toString();
    }

    @GetMapping("/app-user/addAppUser")
    public void registerUserCredential(@RequestBody AppUser user){
        appUserRepository.save(user);
    }
    @GetMapping(value={"/app-user/deleteAppUser/{id}"})
    public void registerUserCredential(@PathVariable(value="id") final long id){
        appUserRepository.deleteById(id);
    }
    @GetMapping(value={"/app-user/updateAppUser/{id}"})
    public void registerUserCredential(@PathVariable(value="id") final int id, @Valid @RequestBody AppUser user){
        AppUser u = appUserRepository.getUserById(id);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setActive(user.getActive());
        u.setCreationDate(user.getCreationDate());
        u.setUpdateDate(user.getUpdateDate());
        appUserRepository.save(u);
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
