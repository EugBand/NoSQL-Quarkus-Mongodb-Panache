package com.epam.mongodb.panache.model;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class SubTask {

    private String id;

    private LocalDateTime deadline;

    private String name;

    private String description;
}
