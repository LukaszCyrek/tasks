package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @Test
    public  void MapToTaskTest() {
        TaskMapper taskMapper = new TaskMapper();
        TaskDTo taskDTo = new TaskDTo(1L, "Test1", "first test" );

        taskMapper.mapToTask(taskDTo);

        assertEquals(1L, taskMapper.mapToTask(taskDTo).getId(), 0);
        assertEquals("Test1", taskMapper.mapToTask(taskDTo).getTitle());
        assertEquals("first test", taskMapper.mapToTask(taskDTo).getContent());
    }

    @Test
    public  void mapToTaskDtoTest() {
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(2L, "Test2", "second test");

        taskMapper.mapToTaskDto(task);

        assertEquals(2l, taskMapper.mapToTaskDto(task).getId(), 0);
        assertEquals("Test2", taskMapper.mapToTaskDto(task).getTitle());
        assertEquals("second test", taskMapper.mapToTaskDto(task).getContent());
    }

}