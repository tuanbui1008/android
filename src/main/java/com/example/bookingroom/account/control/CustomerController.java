package com.example.bookingroom.account.control;

import com.example.bookingroom.account.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
}
