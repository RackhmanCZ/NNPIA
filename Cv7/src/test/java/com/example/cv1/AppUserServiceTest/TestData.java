package com.example.cv1.AppUserServiceTest;

import com.example.cv1.domain.AppUser;

import java.time.LocalDateTime;

public class TestData {
    public static final AppUser APP_USER = new AppUser( 100L, "st55774", "12345", true, LocalDateTime.now(), LocalDateTime.now());

    private TestData() {
    }
}
