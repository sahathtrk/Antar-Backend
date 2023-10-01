package com.andree.antar_be.service;

import com.andree.antar_be.models.Driver;
import com.andree.antar_be.repository.DriverRepository;
import com.andree.antar_be.utils.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    public void registerDriver(Driver driver) throws IException {
        try {
            this.driverRepository.save(driver);
        }catch (Exception e){
            throw new IException("500000", e.getLocalizedMessage(), 500);
        }
    }
}
