package com.ahmetaltun.securedoc.service.impl;

import com.ahmetaltun.securedoc.entity.ConfirmationEntity;
import com.ahmetaltun.securedoc.entity.CredentialEntity;
import com.ahmetaltun.securedoc.entity.RoleEntity;
import com.ahmetaltun.securedoc.entity.UserEntity;
import com.ahmetaltun.securedoc.enumeration.Authority;
import com.ahmetaltun.securedoc.enumeration.EventType;
import com.ahmetaltun.securedoc.event.UserEvent;
import com.ahmetaltun.securedoc.exception.ApiException;
import com.ahmetaltun.securedoc.repository.IConfirmationRepository;
import com.ahmetaltun.securedoc.repository.ICredentialRepository;
import com.ahmetaltun.securedoc.repository.IRoleRepository;
import com.ahmetaltun.securedoc.repository.IUserRepository;
import com.ahmetaltun.securedoc.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.ahmetaltun.securedoc.utils.UserUtils.createUserEntity;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ICredentialRepository credentialRepository;
    private final IConfirmationRepository confirmationRepository;
//    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepository.save(createNewUser(firstName, lastName, email));
        var credentialEntity = new CredentialEntity(password, userEntity);
        credentialRepository.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }

    @Override
    public RoleEntity getRoleName(String name) {
        var role = roleRepository.findByNameIgnoreCase(name);
        return role.orElseThrow(() -> new ApiException("Role not found!"));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }
}
