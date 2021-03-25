package com.example.bookingroom.hotel.hotel.service;

import com.example.bookingroom.hotel.hotel.dao.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired
    private HotelDAO hotelDAO;
}
