package com.andree.antar_be.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class Model {

    @Id
    @Column(length = 36)
    @Builder.Default
    public String id = UUID.randomUUID().toString();

    @CreatedDate
    @Builder.Default
    public Timestamp created_at = new Timestamp(System.currentTimeMillis());

    @LastModifiedDate
    public Timestamp updated_at = new Timestamp(System.currentTimeMillis());

    public Timestamp deleted_at;
}
