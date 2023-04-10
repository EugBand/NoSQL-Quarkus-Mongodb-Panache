package com.epam.mongodb.panache.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.epam.mongodb.panache.model.SubTask;
import com.epam.mongodb.panache.model.Task;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class TaskRepository implements PanacheMongoRepository<Task> {

    public void insertTask(Task task) {
        persist(task);
    }

    public void updateTask(Task task) {
        update(task);
    }

    public void deleteTask(String name) {
        delete("name", name);
    }

    public void deleteSubTask(String taskName, String subtaskName) {
        Optional<Task> task = findByName(taskName);
        task.flatMap(t -> t.getSubTasks().stream().filter(s -> s.getName().equals(subtaskName)).findFirst())
                .ifPresent(st -> task.get().getSubTasks().remove(st));
        task.ifPresent(this::updateTask);

    }

    public void insertSubTask(Task task, SubTask subTask) {
        task.getSubTasks().add(subTask);
        updateTask(task);
    }

    public void createOrUpdateSubTask(Task task, SubTask subTask) {
        List<SubTask> subTasks = task.getSubTasks();
        Optional<SubTask> subTaskUpd = subTasks.stream().filter(s -> s.getName().contains(subTask.getName())).findFirst();
        subTaskUpd.ifPresent(subTasks::remove);
        task.getSubTasks().add(subTask);
        updateTask(task);
    }

    private Optional<Task> findByName(String name) {
        return Optional.ofNullable(find("name", name).firstResult());
    }
}
