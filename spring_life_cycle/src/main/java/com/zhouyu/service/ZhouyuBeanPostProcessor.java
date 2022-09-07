package com.zhouyu.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.Proxy;

/**
 * @author hjy
 **/
@Component("zhouyuBeanPostProcessor")
public class ZhouyuBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (beanName.equals("userService")){
            System.out.println("所有Bean初始化前");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("userService")){
            Object proxyInstance = Proxy.newProxyInstance(ZhouyuBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                System.out.println("前代理逻辑--");
                Object res = method.invoke(bean, args);
                System.out.println("后代理逻辑--");
                return res;
            });
            System.out.println("所有Bean初始化后");
            return proxyInstance;
        }
        return bean;
    }
}
