package com.andree.antar_be.service;

import com.andree.antar_be.models.Customer;
import com.andree.antar_be.repository.CustomerRepository;
import com.andree.antar_be.utils.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) throws IException {
        try {
            this.customerRepository.save(customer);
        }catch (Exception e){
            throw new IException("500000", e.getLocalizedMessage(), 500);
        }
    }


}
