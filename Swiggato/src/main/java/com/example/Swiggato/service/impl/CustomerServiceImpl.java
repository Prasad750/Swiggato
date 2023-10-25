package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.service.CustomerService;
import com.example.Swiggato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl  implements CustomerService {

    final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        // req dto to customer
        Customer customer= CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        //allocate cart
        Cart cart=Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        //allocate cart to customer
        customer.setCart(cart);

        //save both customer and cart
        Customer saveCustomer= customerRepository.save(customer);

        // customer to resp dto
        return CustomerTransformer.CustomerToCustomerResponse(saveCustomer);

    }

    @Override
    public CustomerResponse getCustomerByMobileNo(String mobileNo)  {

        Optional<Customer> optionalCustomer=customerRepository.findByMobileNo(mobileNo);
        if(optionalCustomer.isEmpty())
        {
            throw new CustomerNotFoundException("Customer Not Available");
        }

        Customer customer=optionalCustomer.get();
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
