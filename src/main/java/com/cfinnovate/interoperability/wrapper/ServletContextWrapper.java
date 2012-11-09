
package com.cfinnovate.interoperability.wrapper;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.portlet.PortletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletContextWrapper implements ServletContext {

    protected PortletContext ctx;

    public ServletContextWrapper(PortletContext ctx) {
        this.ctx = ctx;
    }

    public Object getAttribute(String name) {
        return ctx.getAttribute(name);
    }

    @SuppressWarnings("unchecked")
    public Enumeration getAttributeNames() {
        return ctx.getAttributeNames();
    }

    public ServletContext getContext(String arg0) {
        throw new UnsupportedOperationException(
                "getContext is currently not functional");
    }

    public String getContextPath() {
        return "/" + ctx.getPortletContextName();
    }

    public String getInitParameter(String name) {
        return ctx.getInitParameter(name);
    }

    @SuppressWarnings("unchecked")
    public Enumeration getInitParameterNames() {
        return ctx.getInitParameterNames();
    }

    public int getMajorVersion() {
        return ctx.getMajorVersion();
    }

    public String getMimeType(String arg0) {
        return ctx.getMimeType(arg0);
    }

    public int getMinorVersion() {
        return ctx.getMinorVersion();
    }

    public RequestDispatcher getNamedDispatcher(String path) {
        return (RequestDispatcher) ctx.getNamedDispatcher(path);
    }

    public String getRealPath(String path) {
        return ctx.getRealPath(path);
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return (RequestDispatcher) ctx.getRequestDispatcher(path);
    }

    public URL getResource(String path) throws MalformedURLException {
        return ctx.getResource(path);
    }

    public InputStream getResourceAsStream(String path) {
        return ctx.getResourceAsStream(path);
    }

    @SuppressWarnings("unchecked")
    public Set getResourcePaths(String path) {
        return ctx.getResourcePaths(path);
    }

    public String getServerInfo() {
        return ctx.getServerInfo();
    }

    public Servlet getServlet(String arg0) throws ServletException {
        throw new UnsupportedOperationException(
                "getContext is currently not functional");
    }

    public String getServletContextName() {
        return ctx.getPortletContextName();
    }

    @SuppressWarnings("unchecked")
    public Enumeration getServletNames() {
        throw new UnsupportedOperationException(
                "getContext is currently not functional");
    }

    @SuppressWarnings("unchecked")
    public Enumeration getServlets() {
        throw new UnsupportedOperationException(
                "getContext is currently not functional");
    }

    public void log(String msg) {
        ctx.log(msg);

    }

    public void log(Exception arg0, String msg) {
        ctx.log(msg);
    }

    public void log(String message, Throwable throwable) {
        ctx.log(message, throwable);
    }

    public void removeAttribute(String name) {
        ctx.removeAttribute(name);
    }

    public void setAttribute(String name, Object obj) {
        ctx.setAttribute(name, obj);
    }
}
