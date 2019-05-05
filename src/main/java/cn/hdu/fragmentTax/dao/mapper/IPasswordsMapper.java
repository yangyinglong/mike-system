package cn.hdu.fragmentTax.dao.mapper;


import cn.hdu.fragmentTax.dao.entity.PasswordsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface IPasswordsMapper {
    @Select("SELECT `id`, `user_id`, `token` FROM `passwords`")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "token", column = "token")
    })
    List<PasswordsEntity> queryAll();

    @Select("SELECT `id`, `user_id`, `token` FROM `passwords` WHERE `id` = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "token", column = "token")
    })
    PasswordsEntity queryByKey(@Param("id") Integer id);

    @Insert("INSERT INTO `passwords`(`user_id`, `token`) VALUES(#{userId}, #{token})")
    void insert(PasswordsEntity passwordsEntity);

    @Update("UPDATE `passwords` SET id=#{id}, user_id=#{userId}, token=#{token} WHERE `id` = #{id}")
    void update(PasswordsEntity passwordsEntity);

    @Delete("DELETE FROM `passwords` WHERE `id` = #{id}")
    void delete(@Param("id") Integer id);

    @Select("SELECT `id`, `user_id`, `token` FROM `passwords` WHERE `user_id` = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "token", column = "token")
    })
    PasswordsEntity queryByUserId(@Param("userId") Integer userId);
}