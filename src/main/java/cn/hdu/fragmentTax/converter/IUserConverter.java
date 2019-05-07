package cn.hdu.fragmentTax.converter;

import cn.hdu.fragmentTax.dao.entity.PasswordsEntity;
import cn.hdu.fragmentTax.dao.entity.UsersEntity;
import cn.hdu.fragmentTax.model.request.RegisterRequ;
import cn.hdu.fragmentTax.model.response.LoginResp;

public interface IUserConverter {
    UsersEntity createUsersEntity(RegisterRequ registerRequ);

    PasswordsEntity createPasswordEntity(RegisterRequ registerRequ, UsersEntity usersEntity) throws Exception;

    LoginResp createLoginResp(UsersEntity usersEntity);
}
