<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="java.util.Enumeration" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Config Example JSP</title>
</head>
<body>
<h1>Context Parameter Examples</h1>

<h2>Context parameter retrieved from servlet context</h2>
<pre>  param1 = <%= application.getInitParameter("param1") %></pre>

<h2>Context parameter injected into an object via spring config</h2>
<%
ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
String bean = beanFactory.getBean("configBean1", String.class);
%>
<pre>  bean = <%= bean %></pre>

<h2>Context parameter overridden by context.xml</h2>
<pre>  param2 = <%=application.getInitParameter("param2") %></pre>

<h2>Context parameter overridden by web.xml</h2>
<pre>  param3 = <%=application.getInitParameter("param3") %></pre>

<h2>Context parameter from context.xml</h2>
<pre>  param4 = <%=application.getInitParameter("param4") %></pre>

<h2>All Context Parameters</h2>
<%
Enumeration contextParams = application.getInitParameterNames();
while (contextParams.hasMoreElements()) {
String name = (String) contextParams.nextElement();
%>
<pre>  <%= name %> = <%=application.getInitParameter(name) %></pre>
<%
}
%>
</body>
</html>
