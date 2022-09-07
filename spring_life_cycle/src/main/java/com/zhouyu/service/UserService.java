package com.zhouyu.service;

import com.spring.Autowired;
import com.spring.Component;
import com.spring.Scope;

import java.lang.reflect.Proxy;

/**
 * @author hjy
 **/
@Component("userService")
@Scope("singleton")
public class UserService implements IService { // BeanNameAware, InitailizingBean

    @Autowired
    private OrederService orederService;

    private String beanName;

    @Override
    public void test(){
        System.out.println(orederService);
        System.out.println(beanName);
    }

    public static void main(String[] a) {
        UserService bean = new UserService();
        Object proxyInstance = Proxy.newProxyInstance(ZhouyuBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("前代理逻辑--");
            Object res = method.invoke(bean, args);
            System.out.println("后代理逻辑--");
            return res;
        });
        ((IService)proxyInstance).test();

    }

//    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

//    @Override
    public void afterPropertiesSet() {
        System.out.println("UserService初始化");
    }
}
