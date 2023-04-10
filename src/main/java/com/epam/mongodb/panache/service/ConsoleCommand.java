package com.epam.mongodb.panache.service;

import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.epam.mongodb.panache.model.SubTask;
import com.epam.mongodb.panache.model.Task;

public enum ConsoleCommand implements BiFunction<String, String, String> {

    FINDALL() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.findAllTasks().stream().map(Task::getName).collect(Collectors.joining(", "));
        }
    },
    FINDBYID() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.findByName(arg1).getName();
        }
    },
    FINDOVERDUE() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.findOverdue().stream().map(Task::getName).collect(Collectors.joining(", "));
        }
    },
    FINDBYCATEGORY() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.findByCategory(arg1).stream().map(Task::getName).collect(Collectors.joining(", "));
        }
    },
    FINDSUBTASKBYCATEGORY() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.findSubtasksByCategory(arg1).stream().map(SubTask::getName).collect(Collectors.joining(", "));
        }
    },
    INSERTTASK() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.insertTask(arg1);
        }
    },
    UPDATETASK() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.updateTask(arg1);
        }
    },
    DELETETASK() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.deleteTask(arg1);
        }
    },
    DELETESUBTASK() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.deleteSubTask(arg1, arg2);
        }
    },
    INSERTSUBTASK() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.insertSubTask(arg1, arg2);
        }
    },
    CREATESUBTASK() {
        @Override
        public String apply(String arg1, String arg2) {
            return service.createOrUpdateSubTask(arg1, arg2);
        }
    };
    static final CommandService service = new CommandService();

}
