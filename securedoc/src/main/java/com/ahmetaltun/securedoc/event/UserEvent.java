package com.ahmetaltun.securedoc.event;

import com.ahmetaltun.securedoc.entity.UserEntity;
import com.ahmetaltun.securedoc.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 26/11/2024
 */

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {
    private UserEntity user;
    private EventType eventType;
    private Map<?, ?> data;
}
