package com.example.bookingroom.hotel.hotel.control;

import com.example.bookingroom.hotel.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
}
