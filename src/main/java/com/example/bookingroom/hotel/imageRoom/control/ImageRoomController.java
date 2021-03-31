package com.example.bookingroom.hotel.imageRoom.control;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.area.control.AreaController;
import com.example.bookingroom.hotel.hotel.bo.HotelBO;
import com.example.bookingroom.hotel.hotel.dao.HotelDAO;
import com.example.bookingroom.hotel.imageRoom.bean.ImageRoomBean;
import com.example.bookingroom.hotel.imageRoom.bo.ImageRoomBO;
import com.example.bookingroom.hotel.imageRoom.dao.ImageRoomDAO;
import com.example.bookingroom.hotel.imageRoom.form.ImageRoomForm;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/image-room")
public class ImageRoomController {
    private final Logger logger = LoggerFactory.getLogger(ImageRoomController.class);

    @Autowired
    private ImageRoomDAO imageRoomDAO;

    @Autowired
    private HotelDAO hotelDAO;

    @Transactional
    @GetMapping("/fake-date")
    public @ResponseBody
    Response fakeData() {
        List<HotelBO> lstHotel = (List<HotelBO>) hotelDAO.findAll();
        List<ImageRoomBO> lstImageRoom = new ArrayList<>();
        lstHotel.forEach(item -> {
            String url = item.getImage().substring(0, (item.getImage().length() - 5));
            for (int i = 1; i <= 5; i++) {
                ImageRoomBO bo = new ImageRoomBO();
                bo.setIdHotel(item.getId());
                bo.setImage(url + i + ".jpg");
                lstImageRoom.add(bo);
            }
        });
        try {
            imageRoomDAO.saveAll(lstImageRoom);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
    @PostMapping()
    public @ResponseBody Response getListByIdHotel(@RequestBody ImageRoomForm form){
        ModelMapper modelMapper = new ModelMapper();
        List<ImageRoomBO> lst = imageRoomDAO.findImageRoomBOByIdHotel(form.getIdHotel());
        List<ImageRoomBean> listResult = new ArrayList<>();
        lst.forEach(item->{
            listResult.add(modelMapper.map(item,ImageRoomBean.class));
        });
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(listResult);
    }
}
