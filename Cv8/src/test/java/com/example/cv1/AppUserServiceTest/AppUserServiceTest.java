package com.example.cv1.AppUserServiceTest;

import com.example.cv1.Services.AppUserService;
import com.example.cv1.domain.AppUser;
import com.example.cv1.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.cv1.AppUserServiceTest.TestData.APP_USER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserServiceTest {
    private AppUser existing = null;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    void setUp() {
        existing = appUserRepository.save(APP_USER);
    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    void findById() {
        var actual = appUserService.findById(existing.getId());

        assertEquals(existing, actual);
    }
}
