package com.ahmetaltun.securedoc.repository;

import com.ahmetaltun.securedoc.entity.ConfirmationEntity;
import com.ahmetaltun.securedoc.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */

@Repository
public interface IConfirmationRepository extends BaseRepository<ConfirmationEntity> {
    Optional<ConfirmationEntity> findByKey(String key);
    Optional<ConfirmationEntity> findByUserEntity(UserEntity userEntity);
}
