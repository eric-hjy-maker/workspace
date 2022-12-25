package org.example;

import org.common.HelloService;
import org.zhouyu.common.URL;
import org.zhouyu.protocol.HttpServer;
import org.zhouyu.register.LocalRegister;
import org.zhouyu.register.MapRemoteRegister;

public class Provider {
    public static void main(String[] args) {

        LocalRegister.regist(HelloService.class.getName(), "1.0", HelloServiceImpl.class);
        MapRemoteRegister.regist(HelloService.class.getName(), new URL("localhost", 8080));

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
