
package com.cfinnovate.interoperability.wrapper;

import com.cfinnovate.portlet.CFCPortlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.filter.PortletRequestWrapper;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpServletRequestWrapper extends PortletRequestWrapper implements HttpServletRequest {

    protected PortletRequest request;
    private PortletConfig cfg;

    public HttpServletRequestWrapper(PortletRequest request, PortletConfig portletConfig) {
        super(request);
        this.request = request;
        this.cfg = portletConfig;
    }

    @Override
    public String getAuthType() {
        return request.getAuthType();
    }

    @Override
    public String getContextPath() {
        System.out.println("getContextPath(): " + request.getContextPath());
        return request.getContextPath();
    }

    @Override
    public Cookie[] getCookies() {
        // FIXME Probably a good idea to support this....
        return null;
    }

    public long getDateHeader(String str) {
        // FIXME probably a good idea to implement
        return 0;
    }

    public String getHeader(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public Enumeration getHeaderNames() {
        // TODO Auto-generated method stub
        return null;
    }

    public Enumeration getHeaders(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getIntHeader(String arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getMethod() {
        return "GET";
    }

    public String getPathInfo() {
        // FIXME I think this should be something other than blank\
        return "";
    }

    public String getPathTranslated() {
        // FIXME I think this should be something other than blank\
        return "";
    }

    public String getQueryString() {
        // FIXME
        return "";
    }

    @Override
    public String getRemoteUser() {
        return request.getRemoteUser();
    }

    public String getRequestURI() {
        // FIXME
        return "";
    }

    public StringBuffer getRequestURL() {
        // Per spec return null
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return request.getRequestedSessionId();
    }

    public String getServletPath() {
        // Typically the request dispatcher would return the path for the
        // included jsp or servlet.
        // since we're instantiating the cfc directly we'll use that as a path.
        return normalizePath(cfg.getInitParameter(CFCPortlet.CFC_PATH_PARAM));
    }

    private String normalizePath(String path) {
        return "/index.cfm";
    }

    public HttpSession getSession() {
        return new HttpSessionWrapper(request.getPortletSession());
    }

    public HttpSession getSession(boolean arg0) {
        return new HttpSessionWrapper(request.getPortletSession(arg0));
    }

    @Override
    public Principal getUserPrincipal() {
        return request.getUserPrincipal();
    }

    public boolean isRequestedSessionIdFromCookie() {
        // FIXME do I care?
        return false;
    }

    public boolean isRequestedSessionIdFromURL() {
        // FIXME do I care?
        return false;
    }

    public boolean isRequestedSessionIdFromUrl() {
        // FIXME do I care?
        return false;
    }

    @Override
    public boolean isRequestedSessionIdValid() {

        return request.isRequestedSessionIdValid();
    }

    @Override
    public boolean isUserInRole(String arg0) {
        return request.isUserInRole(arg0);
    }

    @Override
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    @Override
    public Enumeration getAttributeNames() {
        return request.getAttributeNames();
    }

    public String getCharacterEncoding() {
        // return request.getCharacterEncoding();
        return null; // Per spec?
    }

    public int getContentLength() {
        // return request.getContentLength();
        return 0; // Per Spec?
    }

    public String getContentType() {
        // Per Spec
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        // Per Spec
        return null;
    }

    public String getLocalAddr() {
        // TODO Auto-generated method stub
        return "";
    }

    public String getLocalName() {
        // TODO Auto-generated method stub
        return "";
    }

    public int getLocalPort() {
        // FIXME
        return request.getServerPort();
    }

    @Override
    public Locale getLocale() {
        return request.getLocale();
    }

    @Override
    public Enumeration getLocales() {
        return request.getLocales();
    }

    @Override
    public String getParameter(String arg0) {
        return request.getParameter(arg0);
    }

    @Override
    public Map getParameterMap() {
        return request.getParameterMap();
    }

    @Override
    public Enumeration getParameterNames() {
        return request.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String arg0) {
        return request.getParameterValues(arg0);
    }

    public String getProtocol() {
        // Per Spec
        return null;
    }

    public BufferedReader getReader() throws IOException {
        // return request.getReader();
        return null; // per spec?
    }

    public String getRealPath(String path) {
        // The JSR168 spec says this should return null but OpenBD needs this,
        // why does it not ask the ServletContext I am not sure.
        System.out.println("getRealPath(): " + cfg.getPortletContext().getRealPath(path));
        return cfg.getPortletContext().getRealPath(path);
    }

    public String getRemoteAddr() {
        // FIXME
        return null;
    }

    public String getRemoteHost() {
        // FIXME
        return null;
    }

    public int getRemotePort() {
        // FIXME
        return 0;
    }

    public RequestDispatcher getRequestDispatcher(String arg0) {
        return (RequestDispatcher) cfg.getPortletContext().getRequestDispatcher(arg0);
    }

    @Override
    public String getScheme() {
        return request.getScheme();
    }

    @Override
    public String getServerName() {
        return request.getServerName();
    }

    @Override
    public int getServerPort() {
        return request.getServerPort();
    }

    @Override
    public boolean isSecure() {
        return request.isSecure();
    }

    @Override
    public void removeAttribute(String name) {
        request.removeAttribute(name);

    }

    @Override
    public void setAttribute(String name, Object o) {
        request.setAttribute(name, o);

    }

    public void setCharacterEncoding(String arg0)
            throws UnsupportedEncodingException {
        // per spec do nothing
    }

}
