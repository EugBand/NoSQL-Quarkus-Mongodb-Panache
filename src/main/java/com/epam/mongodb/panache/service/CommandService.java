package com.epam.mongodb.panache.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.epam.mongodb.panache.model.SubTask;
import com.epam.mongodb.panache.model.Task;
import com.epam.mongodb.panache.model.TaskCategoryType;
import com.epam.mongodb.panache.repository.TaskRepository;

@ApplicationScoped
public class CommandService {

    @Inject
    TaskRepository taskRepository;


    public Task findByName(String name) {
        return Task.findByName(name);
    }

    public List<Task> findAllTasks() {
        return Task.findAllTasks();
    }

    public List<Task> findOverdue() {
        return Task.findOverdue();
    }

    public List<Task> findByCategory(String category) {
        TaskCategoryType taskCategoryType = TaskCategoryType.valueOf(category);
        return Task.findByCategory(taskCategoryType);
    }

    public List<SubTask> findSubtasksByCategory(String category) {
        TaskCategoryType categoryType = TaskCategoryType.valueOf(category);
        return Task.findSubtasksByCategory(categoryType);
    }

    public String insertTask(String taskName) {
        Task task = new Task();
        task.setName(taskName);
        taskRepository.insertTask(task);
        return task.getName();
    }

    public String updateTask(String taskName) {
        Task task = Task.findByName(taskName);
        taskRepository.updateTask(task);
        return task.getName();
    }

    public String deleteTask(String name) {
        taskRepository.deleteTask(name);
        return name;
    }

    public String deleteSubTask(String taskName, String subTaskName) {
        taskRepository.deleteSubTask(taskName, subTaskName);
        return taskName + " : " + subTaskName;
    }

    public String insertSubTask(String taskName, String subTaskName) {
        SubTask subTask = new SubTask();
        subTask.setName(subTaskName);
        Task task = Optional.ofNullable(Task.findByName(taskName)).orElseGet(() -> {
            Task newTask = new Task();
            newTask.setName(taskName);
            taskRepository.insertTask(newTask);
            return newTask;
        });
        taskRepository.insertSubTask(task, subTask);
        return taskName + " : " + subTaskName;
    }

    public String createOrUpdateSubTask(String taskName, String subTaskName) {
        SubTask subTask = new SubTask();
        subTask.setName(subTaskName);
        Task task = Optional.ofNullable(Task.findByName(taskName)).orElseGet(() -> {
            Task newTask = new Task();
            newTask.setName(taskName);
            taskRepository.insertTask(newTask);
            return newTask;
        });
        taskRepository.createOrUpdateSubTask(task, subTask);
        return taskName + " : " + subTaskName;
    }

}
