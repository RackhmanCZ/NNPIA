package com.example.cv1.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private long id;

    private String title;

    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
