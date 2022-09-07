package com.zhouyu;

import com.mybatis.Param;
import com.mybatis.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User selectById(int id);

    @Select("select * from user where name = #{name}")
    List<User> getUser(@Param("name") String name, @Param("age") Integer age);
}
