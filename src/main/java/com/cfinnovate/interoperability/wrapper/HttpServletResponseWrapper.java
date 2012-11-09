
package com.cfinnovate.interoperability.wrapper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.CacheControl;
import javax.portlet.PortletMode;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
import javax.portlet.filter.PortletResponseWrapper;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class HttpServletResponseWrapper extends PortletResponseWrapper implements
        HttpServletResponse,
        RenderResponse {
    private ServletOutputStream sos;
    private PrintWriter pw;
    protected PortletResponse response;

    public HttpServletResponseWrapper() {
        this((OutputStream) null);
    }

    public HttpServletResponseWrapper(PortletResponse response) {
        super(response);
        this.response = response;
    }

    public HttpServletResponseWrapper(OutputStream os) {
        super(null);
        sos = new ServletOutputStreamImpl(os);
        pw = new PrintWriter(sos, true);
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return sos;
    }

    public PrintWriter getWriter() throws IOException {
        return pw;
    }

    public void resetBuffer() {
    }

    public void setContentLength(int len) {
    }

    public void setContentType(String type) {
    }

    public String getCharacterEncoding() {
        return "UTF-8";
    }

    public void addCookie(Cookie cookie) {
    }

    public boolean containsHeader(String name) {
        return false;
    }

    public void setStatus(int sc) {
    }

    public void setHeader(String name, String value) {
    }

    public void setIntHeader(String name, int value) {
    }

    public void setDateHeader(String name, long date) {
    }

    public void sendError(int sc, String msg) throws IOException {
    }

    public void sendError(int sc) throws IOException {
    }

    public void sendRedirect(String location) throws IOException {
    }

    @Override
    public String encodeURL(String url) {
        return url;
    }

    public String encodeRedirectURL(String url) {
        return url;
    }

    public void setBufferSize(int size) {
    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() {
    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {
    }

    public void setLocale(Locale locale) {
    }

    public Locale getLocale() {
        return null;
    }

    public void addDateHeader(String name, long date) {
    }

    public void addHeader(String name, String value) {
    }

    public void addIntHeader(String name, int value) {
    }

    public void setStatus(int i, String s) {
        // Meh
    }

    public String encodeUrl(String url) {
        return url;
    }

    public String encodeRedirectUrl(String url) {
        return url;
    }

    public String getContentType() {
        return "text/html";
    }

    public String encodeURI(String uri) {
        return uri;
    }

    public String encodeNamespace(String ns) {
        return ns;
    }

    public String getCharacterSet() {
        throw new UnsupportedOperationException();
    }

    public void setCharacterEncoding(String arg0) {
        // Per Spec.
    }

    public PortletURL createActionURL() {
        throw new UnsupportedOperationException(
                "Chances are yu are trying to create an Action URL in an action and not a render. Don't do that.");
    }

    public PortletURL createRenderURL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Chances are yu are trying to create an Action URL in an action and not a render. Don't do that.");
    }

    @Override
    public String getNamespace() {
        // TODO Auto-generated method stub
        return null;
    }

    public OutputStream getPortletOutputStream() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Chances are yu are trying to create an Action URL in an action and not a render. Don't do that.");
    }

    public void setTitle(String title) {
        throw new UnsupportedOperationException(
                "Chances are yu are trying to create an Action URL in an action and not a render. Don't do that.");
    }

    @Override
    public void addProperty(String key, String value) {
        throw new UnsupportedOperationException(
                "Chances are yu are trying to create an Action URL in an action and not a render. Don't do that.");
    }

    @Override
    public void setProperty(String key, String value) {
        throw new UnsupportedOperationException(
                "Chances are yu are trying to create an Action URL in an action and not a render. Don't do that.");
    }

    public void setNextPossiblePortletModes(Collection<PortletMode> arg0) {
        // TODO Auto-generated method stub

    }

    public ResourceURL createResourceURL() {
        // TODO Auto-generated method stub
        return null;
    }

    public CacheControl getCacheControl() {
        // TODO Auto-generated method stub
        return null;
    }

}
