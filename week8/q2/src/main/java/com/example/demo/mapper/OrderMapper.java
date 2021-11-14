package com.example.demo.mapper;

import com.example.demo.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select order_id orderId, create_time createTime, address, user_id userId, remark, phone" +
            " from t_order where order_id=#{orderId}")
    List<Order> selectById(@Param("orderId")Long id);

    @Select("select  order_id orderId, create_time createTime, address, user_id userId, remark, phone from t_order")
    List<Order> selectOrderList();

    @Insert("insert into t_order (order_id, create_time, address, user_id, remark, phone) " +
            "values(#{orderId}, #{createTime}, #{address},  #{userId}, #{remark}, #{phone})")
    int insert(@Param("orderId")Long id,
               @Param("createTime")Long time,
               @Param("address")String adr,
               @Param("userId")Long uid,
               @Param("remark")String remark,
               @Param("phone")String p);

    @Update("update t_order set address=#{address} where order_id=#{orderId}")
    int update(@Param("orderId")Long id,
               @Param("address")String adr);

    @Delete("delete from t_order where order_id=#{orderId}")
    int delete(@Param("orderId")Long id);
}
