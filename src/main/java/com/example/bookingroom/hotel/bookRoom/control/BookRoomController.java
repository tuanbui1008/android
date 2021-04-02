package com.example.bookingroom.hotel.bookRoom.control;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.bookRoom.bo.BookRoomBO;
import com.example.bookingroom.hotel.bookRoom.dao.BookRoomDAO;
import com.example.bookingroom.hotel.bookRoom.form.BookRoomForm;
import com.example.bookingroom.hotel.bookRoom.service.BookRoomService;
import com.example.bookingroom.hotel.room.control.RoomController;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/book-room")
public class BookRoomController {
    private final Logger logger = LoggerFactory.getLogger(BookRoomController.class);

    @Autowired
    private BookRoomService bookRoomService;

    @Autowired
    private BookRoomDAO bookRoomDAO;

    @PostMapping("/add")
    public @ResponseBody
    Response addNew(@RequestBody BookRoomForm form){
        ModelMapper mapper = new ModelMapper();
        BookRoomBO bo = mapper.map(form,BookRoomBO.class);
        try {
            bookRoomDAO.save(bo);
        } catch (Exception e) {
           logger.error(e.getMessage());
           return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

}
