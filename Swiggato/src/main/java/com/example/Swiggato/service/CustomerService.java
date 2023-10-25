package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import org.springframework.stereotype.Service;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerByMobileNo(String mobileNo);
}
