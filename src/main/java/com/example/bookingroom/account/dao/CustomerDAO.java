package com.example.bookingroom.account.dao;

import com.example.bookingroom.account.bo.CustomerBO;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<CustomerBO, Integer> {
    boolean existsByGmail(String gmail);

    boolean existsByGmailAndPasswordHash(String gmail, String password);

    CustomerBO getCustomerBOByGmail(String gmail);


}
