package com.app.services;

import com.app.models.Task;
import lombok.Data;
import org.springframework.stereotype.Service;
import com.app.repository.TaskRepository;

import java.util.List;

@Service // SpringBoot knows it is a service
@Data //https://projectlombok.org/features/Data
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task taskToUpdate = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No task found for" + id));
        taskToUpdate.setCompleted(!taskToUpdate.isCompleted());
        taskRepository.save(taskToUpdate);
    }
}
