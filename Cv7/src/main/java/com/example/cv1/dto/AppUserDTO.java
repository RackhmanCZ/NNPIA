package com.example.cv1.dto;

import com.example.cv1.domain.AppUser;
import com.example.cv1.domain.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class AppUserDTO {
    private long id;
    @NotNull
    @NotBlank
    @Size(max = 256, min = 1)
    private String username;

    private String password;

    private Boolean active;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    private List<TaskDTO> tasks = Collections.emptyList();

    private List<Role> roles = Collections.emptyList();

    public AppUserDTO(long id, String username, String password, Boolean active, LocalDateTime creationDate, LocalDateTime updateDate, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.roles = roles;
    }

    public AppUser toEntity() {
        return new AppUser(
                getUsername(),
                getPassword(),
                getActive(),
                getCreationDate(),
                getUpdateDate()
        );
    }
}
