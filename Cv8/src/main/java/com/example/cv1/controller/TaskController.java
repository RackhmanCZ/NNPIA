package com.example.cv1.controller;

import com.example.cv1.Exceptions.ResourceNotFoundException;
import com.example.cv1.Services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        var result = taskService.findAll();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = taskService.findById(id);

        return ResponseEntity.ok(result);
    }
}
