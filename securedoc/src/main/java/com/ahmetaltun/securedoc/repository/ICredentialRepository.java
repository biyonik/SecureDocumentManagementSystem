package com.ahmetaltun.securedoc.repository;

import com.ahmetaltun.securedoc.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */

@Repository
public interface ICredentialRepository extends JpaRepository<CredentialEntity, Long> {
    Optional<CredentialEntity> findCredentialEntitiesByUserEntityId(String userId);
}