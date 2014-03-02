package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ConfigExampleServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Config Example Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Context Parameter Examples</h1>");

        out.println("<h2>Context parameter retrieved from servlet context</h2>");
        out.println("<pre>  param1 = " + getServletContext().getInitParameter("param1") + "</pre>");

        out.println("<h2>Context parameter injected into an object via spring config</h2>");
        ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        String bean = beanFactory.getBean("configBean1", String.class);
        out.println("<pre>  bean = " + bean + "</pre>");

        out.println("<h2>Context parameter overridden by context.xml</h2>");
        out.println("<pre>  param2 = " + getServletContext().getInitParameter("param2") + "</pre>");

        out.println("<h2>Context parameter overridden by web.xml</h2>");
        out.println("<pre>  param3 = " + getServletContext().getInitParameter("param3") + "</pre>");

        out.println("<h2>Context parameter from context.xml</h2>");
        out.println("<pre>  param4 = " + getServletContext().getInitParameter("param4") + "</pre>");

        out.println("<h2>All Context Parameters</h2>");
        Enumeration contextParams = getServletContext().getInitParameterNames();
        while (contextParams.hasMoreElements()) {
            String name = (String) contextParams.nextElement();
            out.println("<pre>  " + name + " = " + getServletContext().getInitParameter(name) + "</pre>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
