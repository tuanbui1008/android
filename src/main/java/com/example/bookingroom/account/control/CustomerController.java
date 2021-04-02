package com.example.bookingroom.account.control;

import com.example.bookingroom.account.bean.CustomerBean;
import com.example.bookingroom.account.bo.CustomerBO;
import com.example.bookingroom.account.dao.CustomerDAO;
import com.example.bookingroom.account.form.CustomerForm;
import com.example.bookingroom.account.service.CustomerService;
import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.area.control.AreaController;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/customer")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerDAO customerDAO;

    @PostMapping(value = "/confirm-gmail", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response confirmGmail(@RequestBody CustomerForm form) {
        boolean confirm = customerDAO.existsByGmail(form.getGmail());
        if (confirm) {
            return Response.confirm(Constants.RESPONSE_CODE.CONFIRM);
        } else {
            return Response.success(Constants.RESPONSE_CODE.SUCCESS);
        }
    }

    @Transactional
    @PostMapping(value = "/created")
    public @ResponseBody
    Response createdUser(@RequestBody CustomerForm form) {
        ModelMapper mapper = new ModelMapper();
        CustomerBO bo = mapper.map(form, CustomerBO.class);
        try {
            customerDAO.save(bo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @PostMapping(value = "/login")
    public @ResponseBody
    Response login(@RequestBody CustomerForm form) {
        boolean exist = customerDAO.existsByGmailAndPasswordHash(form.getGmail(), form.getPasswordHash());
        if (!exist) {
            return Response.warning(Constants.RESPONSE_CODE.WARNING, "Incorrect username or password");
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @PostMapping()
    public @ResponseBody
    Response getCustomerByGmail(@RequestBody CustomerForm form) {
        ModelMapper modelMapper = new ModelMapper();
        CustomerBO bo = customerDAO.getCustomerBOByGmail(form.getGmail());
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(modelMapper.map(bo, CustomerBean.class));
    }

    @PostMapping("/update")
    public @ResponseBody
    Response updateCustomer(@RequestBody CustomerForm form) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            customerDAO.save(modelMapper.map(form, CustomerBO.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
}
