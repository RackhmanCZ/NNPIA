package com.example.cv1.domain;

import com.example.cv1.dto.AppUserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name="AppUser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    @NotEmpty
    @Max(255)
    private String username;

    @Column
    private String password;

    @Column
    private Boolean active;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "author")
    private List<Task> tasks = Collections.emptyList();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "App_user_role",
            joinColumns = { @JoinColumn(name = "app_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )

    @JsonManagedReference
    private List<Role> roles = Collections.emptyList();

    public AppUser(String username, String password, boolean active, LocalDateTime creation_date, LocalDateTime update_date, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creation_date;
        this.updateDate = update_date;
        this.roles = roles;
    }

    public AppUser(String username, String password, boolean active, LocalDateTime creation_date, LocalDateTime update_date) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creation_date;
        this.updateDate = update_date;
    }

    public AppUser() {

    }

    public AppUserDTO toEntity(){
        return new AppUserDTO(id, username, password, active, creationDate, updateDate, roles);
    }
}
