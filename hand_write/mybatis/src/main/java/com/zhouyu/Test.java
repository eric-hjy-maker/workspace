package com.zhouyu;

import com.mybatis.MapperProxyFactory;

import java.util.List;

/**
 * @author hjy
 **/
public class Test {

    public static void main(String[] args) {
        UserMapper mapper = MapperProxyFactory.getMapper(UserMapper.class);
        List<User> result = mapper.getUser("zhouyu", 18);
        for (User user : result) {
            System.out.println(user.toString());
        }
    }
}
