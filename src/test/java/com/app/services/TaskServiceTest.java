package com.app.services;

import com.app.models.Task;
import com.app.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Access the relevant methods for Mockito
@DisplayName("TodoService Unit Test") // Name the test spec
class TaskServiceTest {

    @Mock //Mock the below value
    private TaskRepository taskRepository; // Mocked repository

    @InjectMocks // Injects all the things that are marked as a mock
    private TaskService taskService;


    @Nested
    //<- Without nested we receive an error, the nested class will not be executed. Nested indicates it needs to be executed, You can put all the test methods in the main but having them nested is more organised.
    @DisplayName("Create Tasks Tests")
    class CreateTaskTests {

        @Test
        @DisplayName("Should save and return task successfully when title is valid")
        void shouldCreateTodoSuccessfully() {
            // Given
            String taskTitle = "Finish Spring Boot Project";

            // We create a "saved" version of the task to simulate what the DB returns (with an ID)
            Task savedTask = new Task();
            savedTask.setId(1L);
            savedTask.setTitle(taskTitle);
            savedTask.setCompleted(false);

            when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

            // When
            Task result = taskService.createTask(taskTitle);

            // Then
            assertNotNull(result, "The result should not be null");
            assertEquals(1L, result.getId(), "The ID should match the mocked saved task");
            assertEquals(taskTitle, result.getTitle(), "The title should match the input");
            assertFalse(result.isCompleted(), "New tasks should not be completed");

            // Verify that the repository's save method was actually called exactly once
            verify(taskRepository, times(1)).save(any(Task.class));
        }
    }

    @Nested
    @DisplayName("Delete Tasks Tests")
    class DeleteTaskTests {

        @Test
        @DisplayName("Should delete a task successfully when passed a valid id")
        void shouldDeleteTaskSuccessfully() {
            Long testId = 1L;

            taskService.deleteTask(testId);

            // This method has no return so cannot assert based on the result

            verify(taskRepository, times(1)).deleteById(testId);
        }
    }

    @Nested
    @DisplayName("Toggle Tasks Tests")
    class ToggleTaskTests {

        @Test
        @DisplayName("Should toggle a task from Completed to incomplete when passed a valid id")
        void shouldToggleTaskSuccessfully() {
            Long testId = 1L;
            Task taskInDB = new Task();
            taskInDB.setId(testId);
            taskInDB.setCompleted(true);
            taskInDB.setTitle("Test Task");


            when(taskRepository.findById(testId)).thenReturn(Optional.of(taskInDB));
            when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);

            taskService.toggleTask(testId);

            verify(taskRepository, times(1)).findById(testId);
            verify(taskRepository).save(argThat(task -> !task.isCompleted()));
            // Assert the state of the original object was actually changed
            assertFalse(taskInDB.isCompleted());
        }
    }
}

/**
!! Annotations!!
 * @Test <- annotation to indicate this is an individual test
 * @DisplayName("") <- provides a name in the testing output for the test
 * @Disabled <- This annotation is used to ignore the test
 * @Nested <- inner class will not automatically run unless noted as a nested class
 * @Mock <- Creates a Mock of this instance similar to Scala mock[Repository]
 * @InjectMocks <- passes the Mocks to the class that needs it
 * @ExtendWith(MockitoExtension.class) <- Extends the overall class with Mockito to attach the annotations noted above that belong to Mockito

!! Test Notes !!
void return <- when a method returns a void do not need to handle a mock

!! Test Examples !!
 * void test() <- the test should return nothing as it is asserting i.e. unit
 * fail() <- tbc
 * success() <- tbc
 * <p>
 * Examples:
 * @Test
 * @DisplayName("Create test method")
 * void createTodo() {
 * fail();
 * }
 * @Test
 * @DisplayName("Create test method")
 * void success() {
 * success();
 * }
 * @Test
 * @Disabled // Annotation ignores the test
 * void ignored() {
 * }
 */
