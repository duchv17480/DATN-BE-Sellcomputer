package com.example.udpm14sellcomputerpartsbackend.service;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService {
    void sendMail(Map<String, Object> props, String email, String template, String subject) throws MessagingException;
}
