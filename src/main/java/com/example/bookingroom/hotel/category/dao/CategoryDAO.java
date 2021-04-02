package com.example.bookingroom.hotel.category.dao;

import com.example.bookingroom.hotel.category.bean.CategoryBean;
import com.example.bookingroom.hotel.category.bo.CategoryBO;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

public interface CategoryDAO extends CrudRepository<CategoryBO, Integer> {

}
