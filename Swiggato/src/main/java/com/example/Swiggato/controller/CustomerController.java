package com.example.Swiggato.controller;


import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.service.CustomerService;
import com.example.Swiggato.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController{

    //final--> cause bean is same for entire class
    final CustomerServiceImpl customerServiceImpl;
    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl)
    {
        this.customerServiceImpl=customerServiceImpl;
    }
    //to add customer
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest)
    {
        CustomerResponse response=customerServiceImpl.addCustomer(customerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/get")
    public ResponseEntity getCustomerByMobileNo(@RequestParam("mobileNo") String mobileNo) throws Exception
    {
        try {
            CustomerResponse response=customerServiceImpl.getCustomerByMobileNo(mobileNo);
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }
        catch (CustomerNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
