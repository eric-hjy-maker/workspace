package org.zhouyu.protocol;

import javax.servlet.http.HttpServlet;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        new HttpServerHandler().handler(req, resp);
    }
}
