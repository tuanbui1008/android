package com.example.bookingroom.hotel.bookRoom.service;

import com.example.bookingroom.hotel.bookRoom.bean.BookRoomBean;
import com.example.bookingroom.hotel.bookRoom.control.BookRoomController;
import com.example.bookingroom.hotel.bookRoom.dao.BookRoomDAO;
import com.example.bookingroom.hotel.category.bean.CategoryBean;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Service
public class BookRoomService {
    @Autowired
    private BookRoomDAO bookRoomDAO;

    @Autowired
    private EntityManager entityManager;

    public List<BookRoomBean> getLstBookRoomByIdCustomer(int idCustomer){
        StringBuilder strQuery = new StringBuilder("select ");
        strQuery.append(" br.id as id, ");
        strQuery.append(" h.name as nameHotel, ");
        strQuery.append(" h.address as address, ");
        strQuery.append(" c.name as category, ");
        strQuery.append(" r.roomCode as roomCode, ");
        strQuery.append(" br.createdDate as createdDate, ");
        strQuery.append(" br.dateOfArrival as dateOfArrival, ");
        strQuery.append(" br.amountPerson as amountPerson, ");
        strQuery.append(" br.amountRoom as amountRoom, ");
        strQuery.append(" br.dateGo as dateGo, ");
        strQuery.append(" br.price as price ");
        strQuery.append(" FROM dbo_book_room br ");
        strQuery.append(" join dbo_user u ON u.id = br.idCustomer ");
        strQuery.append(" join dbo_room r ON r.id = br.idRoom ");
        strQuery.append(" join dbo_category c ON c.id = r.idCategory ");
        strQuery.append(" join dbo_hotel h ON h.id = r.idHotel ");
        strQuery.append(" where 1=1");
        if (Objects.nonNull(idCustomer)){
            strQuery.append(" and u.id= ").append(idCustomer);
        }

        strQuery.append(" order by br.createdDate DESC");
        Session session = entityManager.unwrap(Session.class);
        @SuppressWarnings("deprecation")
        javax.persistence.Query query = session.createQuery(strQuery.toString())
                .setResultTransformer(Transformers.aliasToBean(BookRoomBean.class));
        return  query.getResultList();
    }
}
