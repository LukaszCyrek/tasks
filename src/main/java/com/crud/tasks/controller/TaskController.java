package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDTo;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;


    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDTo> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDTo getTask(@RequestParam Long taskId) throws TaskNotFoundException {
      //return new TaskDTo((long) 1, "test title", "test_content");
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException:: new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId) {
      service.deleteTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDTo updateTask(@RequestBody TaskDTo taskDTo) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDTo)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(@RequestBody TaskDTo taskDTo) {
      service.saveTask(taskMapper.mapToTask(taskDTo));
    }

}
