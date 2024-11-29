package com.ahmetaltun.securedoc.entity;

import com.ahmetaltun.securedoc.domain.RequestContext;
import com.ahmetaltun.securedoc.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 25/11/2024
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt", "deletedAt"}, allowGetters = true)
public abstract class Auditable {
    @Id
    @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_key_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String referenceId = UUID.randomUUID().toString();

    @NotNull
    private Long createdBy;

    private Long updatedBy;

    private Long deletedBy;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Version
    private Long version;

    @Column(nullable = false)
    private boolean deleted = false;

    private Long validateAndGetUserId() {
        var userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("Cannot perform operation without User Id in RequestContext!");
        }
        return userId;
    }

    @PrePersist
    public void beforePersist() {
        var userId = validateAndGetUserId();
        setCreatedAt(LocalDateTime.now());
        setCreatedBy(userId);
    }

    @PreUpdate
    public void beforeUpdate() {
        if (!deleted) {
            var userId = validateAndGetUserId();
            setUpdatedBy(userId);
            setUpdatedAt(LocalDateTime.now());
        }
    }

    public void softDelete() {
        var userId = validateAndGetUserId();
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = userId;
    }

    public void restore() {
        var userId = validateAndGetUserId();
        this.deleted = false;
        this.deletedAt = null;
        this.deletedBy = null;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = userId;
    }
}
