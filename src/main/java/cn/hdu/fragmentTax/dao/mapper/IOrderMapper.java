package cn.hdu.fragmentTax.dao.mapper;

import cn.hdu.fragmentTax.dao.entity.OrderEntity;
import cn.hdu.fragmentTax.model.response.OrderResp;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface IOrderMapper {
    @Select("SELECT `id`, `user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price` FROM `order`")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mikeType", column = "mike_type"),
            @Result(property = "number", column = "number"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "address", column = "address"),
            @Result(property = "note", column = "note"),
            @Result(property = "state", column = "state"),
            @Result(property = "price", column = "price")
    })
    List<OrderEntity> queryAll();

    @Select("SELECT `id`, `user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price` FROM `order` WHERE `id` = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mikeType", column = "mike_type"),
            @Result(property = "number", column = "number"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "address", column = "address"),
            @Result(property = "note", column = "note"),
            @Result(property = "state", column = "state"),
            @Result(property = "price", column = "price")
    })
    OrderEntity queryByKey(@Param("id") Integer id);

    @Insert("INSERT INTO `order`(`id`, `user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price`) VALUES(#{id}, #{userId}, #{phone}, #{mikeType}, #{number}, #{sendDate}, #{address}, #{note}, #{state}, #{price})")
    void insert(OrderEntity orderEntity);

    @Update("UPDATE `order` SET id=#{id}, user_id=#{userId}, phone=#{phone}, mike_type=#{mikeType}, number=#{number}, send_date=#{sendDate}, address=#{address}, note=#{note}, state=#{state}, price=#{price} WHERE `id` = #{id}")
    void update(OrderEntity orderEntity);

    @Delete("DELETE FROM `order` WHERE `id` = #{id}")
    void delete(@Param("id") Integer id);

    @Insert("INSERT INTO `order`(`user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price`) VALUES(#{userId}, #{phone}, #{mikeType}, #{number}, #{sendDate}, #{address}, #{note}, #{state}, #{price})")
    void insertOrder(OrderEntity orderEntity);

    @Select("SELECT `id`, `user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price` FROM `order` WHERE `phone` = #{phone} and `state` = 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mikeType", column = "mike_type"),
            @Result(property = "number", column = "number"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "address", column = "address"),
            @Result(property = "note", column = "note"),
            @Result(property = "state", column = "state"),
            @Result(property = "price", column = "price")
    })
    List<OrderEntity> queryNoPayByPhone(@Param("phone") String phone);

    @Select("SELECT `id`, `user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price` FROM `order` WHERE `phone` = #{phone} and `state` <> 0")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mikeType", column = "mike_type"),
            @Result(property = "number", column = "number"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "address", column = "address"),
            @Result(property = "note", column = "note"),
            @Result(property = "state", column = "state"),
            @Result(property = "price", column = "price")
    })
    List<OrderEntity> queryAllByPhone(@Param("phone") String phone);

    @Update("UPDATE `order` SET state=0 WHERE `id` = #{id}")
    void deleteById(@Param("id") Integer id);

    @Update("UPDATE `order` SET state=#{state} WHERE `id` in (${ids})")
    void updateStateById(@Param("ids") String orderIds, @Param("state") Integer state);

    @Select("SELECT `id`, `user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price` FROM `order` WHERE `send_date` in (${sendDates}) and `state` in (${states})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mikeType", column = "mike_type"),
            @Result(property = "number", column = "number"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "address", column = "address"),
            @Result(property = "note", column = "note"),
            @Result(property = "state", column = "state"),
            @Result(property = "price", column = "price")
    })
    List<OrderEntity> queryForAdmin1(@Param("sendDates") String dates, @Param("states") String states);

    @Select("SELECT `id`, `user_id`, `phone`, `mike_type`, `number`, `send_date`, `address`, `note`, `state`, `price` FROM `order` WHERE `send_date` = #{sendDate} and `state` in (${states})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mikeType", column = "mike_type"),
            @Result(property = "number", column = "number"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "address", column = "address"),
            @Result(property = "note", column = "note"),
            @Result(property = "state", column = "state"),
            @Result(property = "price", column = "price")
    })
    List<OrderEntity> queryForAdmin2(@Param("sendDate") String date, @Param("states") String states);

    @Update("UPDATE `order` SET state= #{state} WHERE `id` = #{id}")
    void updateState(@Param("id") Integer id, @Param("state") Integer state);
}