package com.app.repository;

import com.app.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

//JpaRepository<Task, Long>
// Repository connects to the database to do CRUD operations
// Jpa Repository gives pre-defined methods.
// <Task, Long> = the model and the type of the primary key