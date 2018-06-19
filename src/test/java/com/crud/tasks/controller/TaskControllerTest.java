package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import javafx.beans.binding.When;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.ws.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService service;
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testGetTasks() throws Exception {
        //given
        List<Task> listTasks = Arrays.asList(new Task(1L, "First Task", "Test"), new Task(2L, "Second Task", "Test2"));
        when(service.getAllTasks()).thenReturn(listTasks);

        List<TaskDTo> taskDto = Arrays.asList(new TaskDTo(1L, "First Task", "Test"), new TaskDTo(2L, "Second Task", "Test2"));
        //When
        when(taskMapper.mapToTaskDtoList(listTasks)).thenReturn(taskDto);
        ResultActions zz = mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))
                );
    }

   @Test
    public void testPostTask() throws Exception {
        //given
        Task task = new Task(1L, "test", "something to do");
        TaskDTo taskDto = new TaskDTo(1L, "test", "something to do");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        // when & then

        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"));
    }
/*    @Test
    public void testPutTask() throws Exception {
        //given
        Task task = new Task(1L,"test", "something to do");
        TaskDTo taskDto = new TaskDTo(1L,"test", "something to do");

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDTo.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        // when & then
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
               .contentType(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("something to do")));
    }*/


}

