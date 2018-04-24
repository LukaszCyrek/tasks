package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class TaskMapper {
    public Task mapToTask(final TaskDTo taskDTo) {
        return new Task(
                taskDTo.getId(),
                taskDTo.getTitle(),
                taskDTo.getContent());
    }

    public TaskDTo mapToTaskDto(final  Task task) {
        return  new TaskDTo(
                task.getId(),
                task.getTitle(),
                task.getContent());
    }

    public List<TaskDTo> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(t -> new TaskDTo(t.getId(),t.getTitle(),t.getContent()))
                .collect(Collectors.toList());
    }
}
