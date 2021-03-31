package com.example.bookingroom.hotel.imageRoom.dao;

import com.example.bookingroom.hotel.imageRoom.bo.ImageRoomBO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRoomDAO extends CrudRepository<ImageRoomBO,Integer> {
    List<ImageRoomBO> findImageRoomBOByIdHotel(int idHotel);
}
