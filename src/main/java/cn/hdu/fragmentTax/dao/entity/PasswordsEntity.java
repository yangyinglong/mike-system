package cn.hdu.fragmentTax.dao.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class PasswordsEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private String token;

    public PasswordsEntity() {
    }

    public PasswordsEntity(Integer id, Integer userId, String token) {
        this.id = id;
        this.userId = userId;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}