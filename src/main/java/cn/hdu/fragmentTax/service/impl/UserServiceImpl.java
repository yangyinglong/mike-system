package cn.hdu.fragmentTax.service.impl;

import cn.hdu.fragmentTax.converter.IUserConverter;
import cn.hdu.fragmentTax.dao.entity.PasswordsEntity;
import cn.hdu.fragmentTax.dao.entity.UsersEntity;
import cn.hdu.fragmentTax.dao.mapper.IPasswordsMapper;
import cn.hdu.fragmentTax.dao.mapper.IUsersMapper;
import cn.hdu.fragmentTax.model.request.LoginRequ;
import cn.hdu.fragmentTax.model.request.RegisterRequ;
import cn.hdu.fragmentTax.model.response.LoginResp;
import cn.hdu.fragmentTax.service.IUserService;
import cn.hdu.fragmentTax.utils.MD5;
import cn.hdu.fragmentTax.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUsersMapper usersMapper;
    @Autowired
    private IPasswordsMapper passwordsMapper;

    @Autowired
    private IUserConverter userConverter;

    @Override
    public void addUser(RegisterRequ registerRequ) throws Exception {
        UsersEntity usersEntity = userConverter.createUsersEntity(registerRequ);
        try {
            usersMapper.insert(usersEntity);
            usersEntity = usersMapper.queryByPhone(registerRequ.getPhone());
            PasswordsEntity passwordsEntity = userConverter.createPasswordsEntity(registerRequ, usersEntity);
            passwordsMapper.insert(passwordsEntity);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public LoginResp login(LoginRequ loginRequ, UsersEntity usersEntity) throws Exception {
        PasswordsEntity passwordsEntity = passwordsMapper.queryByUserId(usersEntity.getId());
        try {
            if (MD5.verify(loginRequ.getPassword(), PropertiesUtil.prop("token_key"), passwordsEntity.getToken())) {
                return userConverter.createLoginResp(usersEntity);
            }else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
