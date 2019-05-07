package cn.hdu.fragmentTax.service.impl;

import cn.hdu.fragmentTax.converter.IUserConverter;
import cn.hdu.fragmentTax.dao.entity.PasswordsEntity;
import cn.hdu.fragmentTax.dao.entity.UsersEntity;
import cn.hdu.fragmentTax.dao.mapper.IPasswordsMapper;
import cn.hdu.fragmentTax.dao.mapper.IUsersMapper;
import cn.hdu.fragmentTax.model.request.ChangePasswordRequ;
import cn.hdu.fragmentTax.model.request.LoginRequ;
import cn.hdu.fragmentTax.model.request.RegisterRequ;
import cn.hdu.fragmentTax.model.response.LoginResp;
import cn.hdu.fragmentTax.service.IUserService;
import cn.hdu.fragmentTax.utils.FormatUtil;
import cn.hdu.fragmentTax.utils.MD5;
import cn.hdu.fragmentTax.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUsersMapper usersMapper;
    @Autowired
    private IPasswordsMapper passwordsMapper;

    @Autowired
    private IUserConverter userConverter;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public void addUser(RegisterRequ registerRequ) throws Exception {
        UsersEntity usersEntity = userConverter.createUsersEntity(registerRequ);
        try {
            usersMapper.insert(usersEntity);
            usersEntity = usersMapper.queryByPhone(registerRequ.getPhone());
            PasswordsEntity passwordsEntity = userConverter.createPasswordEntity(registerRequ, usersEntity);
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
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean tokenVerify(String phone, String token) {
        try {
            String key= token.split("-")[0];
            if (phone.equals(key) &&
                    ((String) httpServletRequest.getSession().getAttribute(phone)).equals(token)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean passwordChange(Integer userId, String newPassword) {
        try {
            String token = MD5.md5(newPassword, PropertiesUtil.prop("token_key"));
            passwordsMapper.updateTokenByUserId(userId, token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
