package com.epam.mongodb.panache.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@MongoEntity(collection = "tasks")
@Data
public class Task extends PanacheMongoEntity {

    @BsonProperty("id")
    private String id;

    private LocalDateTime created;

    private LocalDateTime deadline;

    private String name;

    private String description;

    private List<SubTask> subTasks;

    private TaskStatusType status;

    private TaskCategoryType category;

    public static Task findByName(String name) {
        return find("name", name).firstResult();
    }

    public static List<Task> findAllTasks() {
        return findAll().list();
    }

    public static List<Task> findOverdue() {
        return list("category", TaskStatusType.OVERDUE);
    }

    public static List<Task> findByCategory(TaskCategoryType category) {
        return list("category", category);
    }

    public static List<SubTask> findSubtasksByCategory(TaskCategoryType categoryType) {
        return list("category", categoryType)
                .stream().flatMap(t -> ((Task) t).subTasks.stream()).collect(Collectors.toList());
    }



}
