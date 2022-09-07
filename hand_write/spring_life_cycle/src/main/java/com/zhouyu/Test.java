package com.zhouyu;

import com.spring.ZhouyuApplicationContext;
import com.zhouyu.service.IService;

/**
 * @author hjy
 **/
public class Test {

    public static void main(String[] args) throws Exception{
        ZhouyuApplicationContext context = new ZhouyuApplicationContext(AppConfig.class);

        IService service = (IService) context.getBean("userService");
        service.test();
    }
}
