package com.example.bookingroom.hotel.hotel.dao;

import com.example.bookingroom.hotel.hotel.bo.HotelBO;
import org.springframework.data.repository.CrudRepository;

public interface HotelDAO extends CrudRepository<HotelBO,Integer> {
}
