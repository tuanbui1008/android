package com.example.bookingroom.hotel.room.dao;

import com.example.bookingroom.hotel.room.bo.RoomBO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomDAO extends CrudRepository<RoomBO,Integer> {

    List<RoomBO> findAllByIdHotelAndIdCategory(int idHotel,int idCategory);
}
