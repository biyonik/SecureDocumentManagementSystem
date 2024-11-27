package com.ahmetaltun.securedoc.service.impl;

import com.ahmetaltun.securedoc.exception.ApiException;
import com.ahmetaltun.securedoc.service.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.ahmetaltun.securedoc.utils.EmailUtils.getEmailMessage;
import static com.ahmetaltun.securedoc.utils.EmailUtils.getResetPasswordMessage;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 26/11/2024
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    private static final String PASSWORD_RESET_REQUEST = "Password Reset Request";
    private final JavaMailSender sender;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountEmail(String name, String email, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getEmailMessage(name, host, token));
            sender.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ApiException("Unable to send email!");
        }
    }

    @Override
    @Async
    public void sendPasswordResetEmail(String name, String email, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(PASSWORD_RESET_REQUEST);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getResetPasswordMessage(name, host, token));
            sender.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ApiException("Unable to send email!");
        }
    }
}
