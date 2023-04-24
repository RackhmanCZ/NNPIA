package com.example.cv1.dto;

import com.example.cv1.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserInputQLDTO {
    private String username;

    private String password;

    private Boolean active;

    private String creationDate;

    private String updateDate;

    public AppUser toEntity() {
        return new AppUser(
                getUsername(),
                getPassword(),
                getActive(),
                LocalDateTime.parse(getCreationDate(), ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse(getUpdateDate(), ISO_LOCAL_DATE_TIME)
        );
    }
}
