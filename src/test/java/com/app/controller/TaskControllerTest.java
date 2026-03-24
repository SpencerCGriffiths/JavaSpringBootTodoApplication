package com.app.controller;

import com.app.models.Task;
import com.app.services.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for Task Controller")
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private Model testModel;

    @InjectMocks
    private TaskController testTaskController;

    @Nested
    @DisplayName("getTasks tests")
    class getTasksTest {

        @Test
        @DisplayName("should return all tasks when called to produce the correct view")
        void successGetTaskTest() {

            Task task1 = new Task();
            task1.setTitle("first task");

            Task task2 = new Task();
            task2.setTitle("first task");

            List<Task> tasks = List.of(task1, task2);
            when(taskService.getAllTasks()).thenReturn(tasks);

            String viewName = testTaskController.getTasks(testModel);

            assertEquals("tasks", viewName, "The method should return the view name 'tasks'");

            // 2. Verify the model was populated with the list from the service
            // Since addAttribute returns void, we use verify()
            verify(testModel, times(1)).addAttribute("tasks", tasks);

            // 3. Verify the service method was actually called
            verify(taskService, times(1)).getAllTasks();
        }
    }

    @Nested
    @DisplayName("createTask tests")
    class createTasksTest {

        @Test
        @DisplayName("should create a task when given an appropriate request parameter")
        void successCreateTaskTest() {

            Task task1 = new Task();
            task1.setTitle("Task title");

            String taskTitle = "Task title";
            when(taskService.createTask(taskTitle)).thenReturn(task1);

            String result = testTaskController.createTask("Task title");

            assertEquals("redirect:/", result, "The controller should return a redirect string to the root URL");
            verify(taskService, times(1)).createTask(taskTitle);
        }
    }

    @Nested
    @DisplayName("deleteTask tests")
    class deleteTasksTest {

        @Test
        @DisplayName("should delete a task when given an appropriate path variable")
        void successDeleteTaskTest() {

            Long testId = 1L;

            // NOTE -> No need to mock a method that is void/ a Unit

            String result = testTaskController.deleteTask(testId);

            assertEquals("redirect:/", result, "The controller should return a redirect string to the root URL");
            verify(taskService, times(1)).deleteTask(testId);
        }
    }

    @Nested
    @DisplayName("toggleTask tests")
    class toggleTasksTest {

        @Test
        @DisplayName("should toggle a task when given an appropriate path variable")
        void successToggleTaskTest() {

            Long testId = 1L;

            // NOTE -> No need to mock a method that is void/ a Unit

            String result = testTaskController.toggleTask(testId);

            assertEquals("redirect:/", result, "The controller should return a redirect string to the root URL");
            verify(taskService, times(1)).toggleTask(testId);
        }
    }

}