package com.pedro.emailservice.infra.ses;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.pedro.emailservice.adapters.EmailSenderGateway;
import com.pedro.emailservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;
    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }
    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("pedroestevan@hotmail.com.br") //quem vai enviar
                .withDestination(new Destination().withToAddresses(to)) //quem vai reeber
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );
        try{
            this.amazonSimpleEmailService.sendEmail(request);
        }catch (AmazonClientException exception){
            throw new EmailServiceException("Failure while sending email");
        }
    }
}
