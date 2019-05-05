package cn.hdu.fragmentTax.service;

import cn.hdu.fragmentTax.dao.entity.UsersEntity;
import cn.hdu.fragmentTax.model.request.LoginRequ;
import cn.hdu.fragmentTax.model.request.RegisterRequ;
import cn.hdu.fragmentTax.model.response.LoginResp;

public interface IUserService {
    void addUser(RegisterRequ registerRequ) throws Exception;

    LoginResp login(LoginRequ loginRequ, UsersEntity usersEntity) throws Exception;
}
