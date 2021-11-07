package com.example.demo.q10;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TestMapper {

    @Select("select id from user where user_id=#{id}")
    int selectUserById(@Param("id")Integer id);

    @Select("select id from order where order_id=#{id}")
    int selectOrderById(@Param("id")Integer id);
}
