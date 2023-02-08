package ru.kuzmina.lesson6;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "HelloWorld", urlPatterns = "/")
public class HelloWorldServlet implements Servlet {
    ServletConfig servletConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1>HelloWorld</h1>");
    }

    @Override
    public String getServletInfo() {
        return "Hello World Servlet";
    }

    @Override
    public void destroy() {

    }
}
