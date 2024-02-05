package com.pedro.emailservice.controllers;

import com.pedro.emailservice.application.EmailSenderService;
import com.pedro.emailservice.core.EmailRequest;
import com.pedro.emailservice.core.exceptions.EmailServiceException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;
    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        try{
            this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("email send successfully");
        }catch(EmailServiceException ex){
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error while sending email");
        }
    }

 }
