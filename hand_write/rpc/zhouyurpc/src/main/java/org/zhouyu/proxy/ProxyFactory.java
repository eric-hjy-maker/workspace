package org.zhouyu.proxy;

import org.zhouyu.common.Invocation;
import org.zhouyu.common.URL;
import org.zhouyu.loadbalance.Loadbanlance;
import org.zhouyu.protocol.HttpClient;
import org.zhouyu.register.LocalRegister;
import org.zhouyu.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {
        // 用户配置

        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(),
                        method.getName(), method.getParameterTypes(), args);
                HttpClient httpClient = new HttpClient();

                // 服务发现
                List<URL> urls = MapRemoteRegister.get(interfaceClass.getName());

                // 负载均衡
                URL url = Loadbanlance.random(urls);

                // 服务调用
                String result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                return result;
            }
        });
        return (T) proxyInstance;
    }
}
