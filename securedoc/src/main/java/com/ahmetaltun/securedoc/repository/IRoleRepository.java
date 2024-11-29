package com.ahmetaltun.securedoc.repository;

import com.ahmetaltun.securedoc.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */


@Repository
public interface IRoleRepository extends BaseRepository<RoleEntity> {
    Optional<RoleEntity> findByNameIgnoreCase(String name);
}
