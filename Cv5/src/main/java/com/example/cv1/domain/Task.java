package com.example.cv1.domain;

import com.example.cv1.dto.TaskDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Task {
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    @ToString.Exclude
    @JsonIgnore
    private AppUser author;

    public TaskDTO toDto() {
        return new TaskDTO(
                getId(),
                getTitle(),
                getDescription(),
                getCreationDate(),
                getUpdateDate()
        );
    }
}
