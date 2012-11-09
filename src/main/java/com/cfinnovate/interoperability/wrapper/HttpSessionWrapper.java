
package com.cfinnovate.interoperability.wrapper;

import java.util.Enumeration;

import javax.portlet.PortletSession;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class HttpSessionWrapper implements HttpSession {

    protected PortletSession session;

    public HttpSessionWrapper(PortletSession session)
    {
        this.session = session;
    }

    public Object getAttribute(String name) {
        return session.getAttribute(name);
    }

    public Enumeration getAttributeNames() {
        return session.getAttributeNames();
    }

    public long getCreationTime() {
        return session.getCreationTime();
    }

    public String getId() {
        return session.getId();
    }

    public long getLastAccessedTime() {
        return session.getLastAccessedTime();
    }

    public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
    }

    public ServletContext getServletContext() {
        return new ServletContextWrapper(session.getPortletContext());
    }

    public HttpSessionContext getSessionContext() {
        // FIXME
        throw new UnsupportedOperationException();
    }

    public Object getValue(String arg0) {
        throw new UnsupportedOperationException();
    }

    public String[] getValueNames() {
        throw new UnsupportedOperationException();
    }

    public void invalidate() {
        session.invalidate();
    }

    public boolean isNew() {
        return session.isNew();
    }

    public void putValue(String arg0, Object arg1) {
        throw new UnsupportedOperationException();
    }

    public void removeAttribute(String name) {
        session.removeAttribute(name);
    }

    public void removeValue(String arg0) {
        throw new UnsupportedOperationException();
    }

    public void setAttribute(String name, Object value) {
        session.setAttribute(name, value);
    }

    public void setMaxInactiveInterval(int intervale) {
    }

}
