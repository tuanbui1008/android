package com.example.bookingroom.hotel.comment.dao;

import com.example.bookingroom.hotel.comment.bo.CommentBO;
import org.springframework.data.repository.CrudRepository;

public interface CommentDAO extends CrudRepository<CommentBO,Integer> {
}
