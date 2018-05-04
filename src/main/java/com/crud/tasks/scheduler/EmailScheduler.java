package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Task: New Trello card";


    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail(){
        git init
        long size = taskRepository.count();
simpleEmailService.send(new Mail(
        adminConfig.getAdminMail(),
        SUBJECT,
        "Curerently in database you got: " + size + " tasks",
        "test@gmail.com"));
    }
}
