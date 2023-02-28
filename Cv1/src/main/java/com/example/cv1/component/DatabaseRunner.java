package com.example.cv1.component;

import com.example.cv1.repository.AppUserRepository;
import com.example.cv1.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j // Annotation will create attribute log which can be used for formatted log-level logging
public class DatabaseRunner implements ApplicationRunner {
    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    public DatabaseRunner(AppUserRepository appUserRepository, RoleRepository roleRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void run(ApplicationArguments args) {
        appUserRepository.findAll().forEach(appUser -> log.info(appUser.toString()));

        var role = roleRepository.findAll().iterator().next();

        appUserRepository.findAllByRolesContaining(role)
                .forEach(appUser -> log.info(appUser.toString()));
    }
}