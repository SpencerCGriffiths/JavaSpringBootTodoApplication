package com.app.controller;

import com.app.models.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.app.services.TaskService;

import java.util.List;

@Controller // Spring boot knows this is a controller
@RequestMapping("/")
public class TaskController {

    // This makes the taskService available to the controller
    private final TaskService taskService;

    // This method injects the service at runtime.
    // It can be generated automatically if the service is annotated with Service.
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        // Users are going to interact with Thymeleaf
        // You need to return the name of the template you want to render
        // This will then look for tasks.html in the resources templates
        // We need to be able to pass the values to the model so we can use addAttribute
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/"; //This refreshes the route and the page to update with the created
    }

    // Path variable tells it to get the value from the path
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/";
    }
}
