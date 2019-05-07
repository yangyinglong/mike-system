package cn.hdu.fragmentTax.controller.endpoint;


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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IUsersMapper usersMapper;

    @Autowired
    private IPasswordsMapper passwordsMapper;


    /**
     * 注册
     * @param registerRequ
     * @return
     */
    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> register(RegisterRequ registerRequ){
        Map<String, Object> resp = new HashMap<>();
        if (!FormatUtil.isEmpty(usersMapper.queryByPhone(registerRequ.getPhone()))) {
            resp.put("c", 301);
            resp.put("r", "该手机号码已注册");
            return resp;
        }
        try {
            userService.addUser(registerRequ);
            resp.put("c", 200);
            resp.put("r", "注册成功");
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> login(LoginRequ loginRequ) {
        Map<String, Object> resp = new HashMap<>();
        UsersEntity usersEntity = usersMapper.queryByPhone(loginRequ.getPhone());
        if (FormatUtil.isEmpty(usersEntity)) {
            resp.put("c", 302);
            resp.put("r", "该号码未注册");
            return resp;
        }
        try {
            LoginResp loginResp = userService.login(loginRequ, usersEntity);
            if (!FormatUtil.isEmpty(loginResp)) {
                resp.put("c", 200);
                resp.put("r", loginResp);
            } else {
                resp.put("c", 303);
                resp.put("r", "密码错误");
            }
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return  resp;
    }


    @Path("/passwordChange")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> passwordChange(ChangePasswordRequ changePasswordRequ) {
        Map<String, Object> resp = new HashMap<>();
        // 验证token
        if (! userService.tokenVerify(changePasswordRequ.getPhone(), changePasswordRequ.getToken())) {
            resp.put("c", 304);
            resp.put("r", "请登录");
            return resp;
        }
        // 判断用户是否存在
        UsersEntity userEntity = usersMapper.queryByPhone(changePasswordRequ.getPhone());
        if (FormatUtil.isEmpty(userEntity)) {
            resp.put("c", 302);
            resp.put("r", "该号码未注册");
            return resp;
        }
        // 验证密码是否正确
        PasswordsEntity passwordEntity = passwordsMapper.queryByUserId(userEntity.getId());
        if (MD5.verify(changePasswordRequ.getOldPassword(), PropertiesUtil.prop("token_key"), passwordEntity.getToken())) {
            // 修改密码
            if (userService.passwordChange(userEntity.getId(), changePasswordRequ.getNewPassword())) {
                resp.put("c", 200);
                resp.put("r", "修改成功");
            } else {
                resp.put("c", 401);
                resp.put("r", "数据库异常，请联系管理员");
            }

        } else {
            resp.put("c", 303);
            resp.put("r", "原密码错误");
        }
        return resp;
    }
}
