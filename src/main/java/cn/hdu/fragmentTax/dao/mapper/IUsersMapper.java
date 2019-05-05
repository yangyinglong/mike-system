package cn.hdu.fragmentTax.dao.mapper;


import cn.hdu.fragmentTax.dao.entity.UsersEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface IUsersMapper {
    @Select("SELECT `id`, `name`, `phone`, `state`, `created_time` FROM `users`")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "state", column = "state"),
            @Result(property = "createdTime", column = "created_time")
    })
    List<UsersEntity> queryAll();

    @Select("SELECT `id`, `name`, `phone`, `state`, `created_time` FROM `users` WHERE `id` = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "state", column = "state"),
            @Result(property = "createdTime", column = "created_time")
    })
    UsersEntity queryByKey(@Param("id") Integer id);

    @Insert("INSERT INTO `users`(`name`, `phone`, `state`, `created_time`) VALUES( #{name}, #{phone}, #{state}, #{createdTime})")
    void insert(UsersEntity usersEntity);

    @Update("UPDATE `users` SET id=#{id}, name=#{name}, phone=#{phone}, state=#{state}, created_time=#{createdTime} WHERE `id` = #{id}")
    void update(UsersEntity usersEntity);

    @Delete("DELETE FROM `users` WHERE `id` = #{id}")
    void delete(@Param("id") Integer id);

    @Select("SELECT `id`, `name`, `phone`, `state`, `created_time` FROM `users` WHERE `phone` = #{phone} and `state` <> 0")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "state", column = "state"),
            @Result(property = "createdTime", column = "created_time")
    })
    UsersEntity queryByPhone(@Param("phone") String phone);
}