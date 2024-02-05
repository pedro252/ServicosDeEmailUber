package com.pedro.emailservice.core;

public interface EmailSenderUsecase {
    void sendEmail(String to, String subject, String body);
}
