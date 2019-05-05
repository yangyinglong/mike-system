package cn.hdu.fragmentTax.dao.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class UsersEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private Integer state;  // 1-有效，0-无效
    @Column
    private String createdTime;

    public UsersEntity() {
    }

    public UsersEntity(Integer id, String name, String phone, Integer state, String createdTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}