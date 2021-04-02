package com.example.bookingroom.hotel.comment.service;

import com.example.bookingroom.hotel.category.bean.CategoryBean;
import com.example.bookingroom.hotel.comment.bean.CommentBean;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {

    @Autowired
    private EntityManager entityManager;

    public List<CommentBean> getCommentsByHotel(int idHotel){
        StringBuilder strQuery = new StringBuilder("SELECT c.id as id, u.fullName as fullName, c.content as content,u.avatar as image");
        strQuery.append(" FROM dbo_comment c ");
        strQuery.append(" join dbo_user u on u.id=c.idCustomer ");
        strQuery.append(" join dbo_hotel h on h.id = c.idHotel ");
        strQuery.append(" where 1=1");
        if (Objects.nonNull(idHotel)) {
            strQuery.append(" and h.id= ").append(idHotel);
        }
        strQuery.append(" group by c.idCustomer");

        Session session = entityManager.unwrap(Session.class);
        @SuppressWarnings("deprecation")
        javax.persistence.Query query = session.createQuery(strQuery.toString())
                .setResultTransformer(Transformers.aliasToBean(CommentBean.class));
        return query.getResultList();
    }
}
