package com.example.bookingroom.hotel.room.control;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.category.bo.CategoryBO;
import com.example.bookingroom.hotel.category.dao.CategoryDAO;
import com.example.bookingroom.hotel.hotel.bo.HotelBO;
import com.example.bookingroom.hotel.hotel.dao.HotelDAO;
import com.example.bookingroom.hotel.room.bo.RoomBO;
import com.example.bookingroom.hotel.room.dao.RoomDAO;
import com.example.bookingroom.hotel.room.form.RoomForm;
import com.example.bookingroom.hotel.room.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("api/room")
public class RoomController {
    private final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private HotelDAO hotelDAO;

    @GetMapping("/fake-data")
    public @ResponseBody
    Response fakeData() {
        List<CategoryBO> categories = (List<CategoryBO>) categoryDAO.findAll();
        List<HotelBO> lstHotel = (List<HotelBO>) hotelDAO.findAll();
        List<RoomBO> lst = new ArrayList<>();
        Random random = new Random();
        DecimalFormat format = new DecimalFormat("0.000");
        int min = 1000;
        int max = 9999;
        for (HotelBO hotel : lstHotel) {
            for (int i = 1; i <= 20; i++) {
                RoomBO bo = new RoomBO();
                bo.setIdHotel(hotel.getId());
                bo.setIdCategory(categories.get(random.nextInt(categories.size())).getId());
                bo.setRoomCode("Room " + i);
                bo.setAmountMax(random.nextInt(10) + 1);
                bo.setPrice(Double.parseDouble(format.format(min + (max - min) * random.nextDouble())));
                lst.add(bo);
            }
        }
        try {
            roomDAO.saveAll(lst);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error(Constants.RESPONSE_CODE.ERROR, e.getMessage());
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
    @PostMapping("/getRoom")
    public @ResponseBody Response getRoomByIdHotelAndIdCategory(@RequestBody RoomForm form){
        List<RoomBO> lst = roomDAO.findAllByIdHotelAndIdCategory(form.getIdHotel(), form.getIdCategory());
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(lst);
    }
}
