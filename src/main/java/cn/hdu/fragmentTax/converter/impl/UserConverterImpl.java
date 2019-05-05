package cn.hdu.fragmentTax.converter.impl;

import cn.hdu.fragmentTax.converter.IUserConverter;
import cn.hdu.fragmentTax.dao.entity.PasswordsEntity;
import cn.hdu.fragmentTax.dao.entity.UsersEntity;
import cn.hdu.fragmentTax.model.request.RegisterRequ;
import cn.hdu.fragmentTax.model.response.LoginResp;
import cn.hdu.fragmentTax.utils.DateUtil;
import cn.hdu.fragmentTax.utils.MD5;
import cn.hdu.fragmentTax.utils.PropertiesUtil;
import cn.hdu.fragmentTax.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class UserConverterImpl implements IUserConverter {
    @Override
    public UsersEntity createUsersEntity(RegisterRequ registerRequ) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setState(1);
        usersEntity.setCreatedTime(DateUtil.getCurrentDatetime());
        BeanUtils.copyProperties(registerRequ, usersEntity);
        return usersEntity;
    }

    @Override
    public PasswordsEntity createPasswordsEntity(RegisterRequ registerRequ, UsersEntity usersEntity) throws Exception {
        PasswordsEntity passwordsEntity = new PasswordsEntity();
        passwordsEntity.setUserId(usersEntity.getId());
        passwordsEntity.setToken(MD5.md5(registerRequ.getPassword(), PropertiesUtil.prop("token_key")));
        return passwordsEntity;
    }

    @Override
    public LoginResp createLoginResp(UsersEntity usersEntity) {
        LoginResp loginResp = new LoginResp();
        BeanUtils.copyProperties(usersEntity, loginResp);
        return loginResp;
    }
}
