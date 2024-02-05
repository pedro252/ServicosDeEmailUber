package com.pedro.emailservice.adapters;

public interface EmailSenderGateway {

    void sendEmail(String to, String Subject, String body);
}
