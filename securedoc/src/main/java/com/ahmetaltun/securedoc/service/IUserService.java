package com.ahmetaltun.securedoc.service;

import com.ahmetaltun.securedoc.entity.RoleEntity;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */


public interface IUserService {
    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name);
    void verifyAccount(String key);
}
