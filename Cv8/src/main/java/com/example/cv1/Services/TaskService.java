package com.example.cv1.Services;

import com.example.cv1.Exceptions.ResourceNotFoundException;
import com.example.cv1.domain.Task;
import com.example.cv1.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAllByAppUserId(final long appUserId) {
        return taskRepository.findAllByAuthorId(appUserId);
    }

    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) throws ResourceNotFoundException {
        var result = taskRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return result.get();
    }
}
