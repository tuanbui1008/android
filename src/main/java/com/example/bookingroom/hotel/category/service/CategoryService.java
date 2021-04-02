package com.example.bookingroom.hotel.category.service;

import com.example.bookingroom.hotel.category.bean.CategoryBean;
import com.example.bookingroom.hotel.category.dao.CategoryDAO;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    @Autowired
    EntityManager entityManager;

    public List<CategoryBean> getCategoryBOByIdHotel(@Param("idHotel") int idhotel) {
        StringBuilder strQuery = new StringBuilder("SELECT c.id as id, c.name as name,r.price as price ");
        strQuery.append(" FROM dbo_category c ");
        strQuery.append(" join dbo_room r on r.idCategory=c.id ");
        strQuery.append(" join dbo_hotel h on h.id = r.idHotel ");
        strQuery.append(" where 1=1");
        if (Objects.nonNull(idhotel)) {
            strQuery.append(" and h.id= ").append(idhotel);
        }
        strQuery.append(" group by c.name");
        Session session = entityManager.unwrap(Session.class);
        @SuppressWarnings("deprecation")
        javax.persistence.Query query = session.createQuery(strQuery.toString())
                .setResultTransformer(Transformers.aliasToBean(CategoryBean.class));
        return  query.getResultList();
    }
}
