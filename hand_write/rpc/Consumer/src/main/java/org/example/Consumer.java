package org.example;

import org.common.HelloService;
import org.zhouyu.common.Invocation;
import org.zhouyu.protocol.HttpClient;
import org.zhouyu.proxy.ProxyFactory;

public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.hello("zhangsan123");
        System.out.println("调用的结果是：" + result);

        /*Invocation invocation = new Invocation(HelloService.class.getName(), "hello",
                new Class[]{String.class}, new Object[]{"zhangsan"});

        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);*/
    }
}
