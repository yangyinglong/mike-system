package cn.hdu.fragmentTax.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private String phone;
    @Column
    private String mikeType;
    @Column
    private Integer number;
    @Column
    private String sendDate;
    @Column
    private String address;
    @Column
    private String note;
    @Column
    private Integer state;  // 1-未付款，2-已付款，3-配送中，4-已送达，0-已删除
    @Column
    private float price;

    public OrderEntity() {
    }

    public OrderEntity(Integer id, Integer userId, String phone, String mikeType, Integer number, String sendDate, String address, String note, Integer state, float price) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.mikeType = mikeType;
        this.number = number;
        this.sendDate = sendDate;
        this.address = address;
        this.note = note;
        this.state = state;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMikeType() {
        return mikeType;
    }

    public void setMikeType(String mikeType) {
        this.mikeType = mikeType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}