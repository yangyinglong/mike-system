package cn.hdu.fragmentTax.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
public class PaymentEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private String phone;
    @Column
    private float amount;
    @Column
    private String createdTime;
    @Column
    private Integer state;  // 1-未审核，2-已通过
    @Column
    private String orderIds;

    public PaymentEntity() {
    }

    public PaymentEntity(Integer id, Integer userId, String phone, float amount, String createdTime, Integer state, String orderIds) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.amount = amount;
        this.createdTime = createdTime;
        this.state = state;
        this.orderIds = orderIds;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

}