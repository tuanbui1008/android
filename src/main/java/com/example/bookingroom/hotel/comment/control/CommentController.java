package com.example.bookingroom.hotel.comment.control;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.category.control.CategoryController;
import com.example.bookingroom.hotel.comment.bo.CommentBO;
import com.example.bookingroom.hotel.comment.dao.CommentDAO;
import com.example.bookingroom.hotel.comment.form.CommentForm;
import com.example.bookingroom.hotel.comment.service.CommentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/comment")
public class CommentController {
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentDAO commentDAO;

    @PostMapping("/getComments")
    public @ResponseBody
    Response getCommentsByHotel(@RequestBody CommentForm form){
        return Response.success(Constants.RESPONSE_CODE.SUCCESS)
                .withData(commentService.getCommentsByHotel(form.getIdHotel()));
    }

    @PostMapping("/add")
    public @ResponseBody Response addNew(@RequestBody CommentForm form){
        ModelMapper modelMapper = new ModelMapper();
        try {
            commentDAO.save(modelMapper.map(form, CommentBO.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }
}
