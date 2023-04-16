package com.example.cv1.controller;

import com.example.cv1.Services.TaskService;
import com.example.cv1.domain.Task;
import com.example.cv1.dto.AppUserDTO;
import com.example.cv1.dto.TaskDTO;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class TaskQLController {
    private final TaskService taskService;

    @SchemaMapping(typeName = "AppUser")
    public List<TaskDTO> tasks(final AppUserDTO appUser) {
        return taskService.findAllByAppUserId(appUser.getId())
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
    }
}
