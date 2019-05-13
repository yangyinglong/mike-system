package cn.hdu.fragmentTax.dao.mapper;

import cn.hdu.fragmentTax.dao.entity.PaymentEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface IPaymentMapper {
    @Select("SELECT `id`, `user_id`, `phone`, `amount`, `created_time`, `state`, `order_ids` FROM `payment`")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createdTime", column = "created_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "orderIds", column = "order_ids")
    })
    List<PaymentEntity> queryAll();

    @Select("SELECT `id`, `user_id`, `phone`, `amount`, `created_time`, `state`, `order_ids` FROM `payment` WHERE `id` = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createdTime", column = "created_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "orderIds", column = "order_ids")
    })
    PaymentEntity queryByKey(@Param("id") Integer id);

    @Insert("INSERT INTO `payment`(`id`, `user_id`, `phone`, `amount`, `created_time`, `state`, `order_ids`) VALUES(#{id}, #{userId}, #{phone}, #{amount}, #{createdTime}, #{state}, #{orderIds})")
    void insert(PaymentEntity paymentEntity);

    @Update("UPDATE `payment` SET id=#{id}, user_id=#{userId}, phone=#{phone}, amount=#{amount}, created_time=#{createdTime}, state=#{state}, order_ids=#{orderIds} WHERE `id` = #{id}")
    void update(PaymentEntity paymentEntity);

    @Delete("DELETE FROM `payment` WHERE `id` = #{id}")
    void delete(@Param("id") Integer id);

    @Insert("INSERT INTO `payment`(`user_id`, `phone`, `amount`, `created_time`, `state`, `order_ids`) VALUES(#{userId}, #{phone}, #{amount}, #{createdTime}, #{state}, #{orderIds})")
    void insertPayment(PaymentEntity paymentEntity);

    @Select("SELECT `id`, `user_id`, `phone`, `amount`, `created_time`, `state`, `order_ids` FROM `payment` WHERE `state` in (${states})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createdTime", column = "created_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "orderIds", column = "order_ids")
    })
    List<PaymentEntity> queryForAdmin(@Param("states") String states);

    @Update("UPDATE `payment` SET state=#{state} WHERE `id` = #{id}")
    void updateStateById(@Param("id") Integer id, @Param("state") Integer state);
}