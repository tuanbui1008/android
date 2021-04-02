package com.example.bookingroom.hotel.bookRoom.bo;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "dbo_book_room")
public class BookRoomBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "id_customer")
    private int idCustomer;

    @Column(name = "id_room")
    private int idRoom;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "date_of_arrival")
    private Date dateOfArrival;

    @Column(name = "date_go")
    private Date dateGo;

    @Column(name = "amount_person")
    private int amountPerson;

    @Column(name = "amount_room")
    private int amountRoom;

    @Column(name = "price")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getAmountPerson() {
        return amountPerson;
    }

    public void setAmountPerson(int amountPerson) {
        this.amountPerson = amountPerson;
    }

    public int getAmountRoom() {
        return amountRoom;
    }

    public void setAmountRoom(int amountRoom) {
        this.amountRoom = amountRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Date getDateGo() {
        return dateGo;
    }

    public void setDateGo(Date dateGo) {
        this.dateGo = dateGo;
    }
}
