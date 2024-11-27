package com.ahmetaltun.securedoc.event.listener;

import com.ahmetaltun.securedoc.event.UserEvent;
import com.ahmetaltun.securedoc.service.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 26/11/2024
 */

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final IEmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent event) {
        switch (event.getEventType()) {
            case REGISTRATION -> emailService.sendNewAccountEmail(event.getUser().getFirstName(), event.getUser().getEmail(), String.valueOf(event.getData().get("key")));
            case RESETPASSWORD -> emailService.sendPasswordResetEmail(event.getUser().getFirstName(), event.getUser().getEmail(), String.valueOf(event.getData().get("key")));
            default -> {}
        }
    }
}
