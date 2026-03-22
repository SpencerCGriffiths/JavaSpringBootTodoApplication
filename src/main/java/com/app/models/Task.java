package com.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // https://projectlombok.org/features/Data
public class Task {
//Members become the collumns in the database as the Task becomes the table through LPA
        @Id // JPA knows this is the ID field
        @GeneratedValue(strategy = GenerationType.AUTO) // It knows to create unique ID so we dont have to manage 1, 2, 3,4 etc.
        private Long id;
        private String title;
        private boolean completed;
}
