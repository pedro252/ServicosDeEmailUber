package com.pedro.emailservice.infra.ses;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfig {

    @Value("${aws.region}")  // Adicione esta anotação para injetar a região via properties ou variáveis de ambiente
    private String awsRegion;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()) // Usa a cadeia padrão de provedores de credenciais
                .withRegion(awsRegion)  // Define a região
                .build();
    }
}
