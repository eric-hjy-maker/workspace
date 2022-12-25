package org.zhouyu.protocol;

import org.apache.commons.io.IOUtils;
import org.zhouyu.common.Invocation;
import org.zhouyu.register.LocalRegister;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) {
        // 处理请求 --> 接口、方法、参数
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class classImpl = LocalRegister.get(interfaceName,"1.0");
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String)method.invoke(classImpl.newInstance(), invocation.getParameters());
            IOUtils.write(result, resp.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
