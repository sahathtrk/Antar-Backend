package com.andree.antar_be.repository;

import com.andree.antar_be.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, String> {
    Optional<Driver> findByUserId(String userID);
}
