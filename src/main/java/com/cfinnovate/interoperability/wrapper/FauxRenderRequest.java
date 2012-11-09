
package com.cfinnovate.interoperability.wrapper;

import java.util.Enumeration;

import javax.portlet.PortalContext;
import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.WindowState;

@SuppressWarnings("unchecked")
public class FauxRenderRequest extends HttpServletRequestWrapper implements
        RenderRequest {

    public FauxRenderRequest(PortletRequest request, PortletConfig cfg) {
        super(request, cfg);
    }

    @Override
    public PortalContext getPortalContext() {
        return request.getPortalContext();
    }

    @Override
    public PortletMode getPortletMode() {
        return request.getPortletMode();
    }

    @Override
    public PortletSession getPortletSession() {
        return request.getPortletSession();
    }

    @Override
    public PortletSession getPortletSession(boolean create) {
        return request.getPortletSession(create);
    }

    @Override
    public PortletPreferences getPreferences() {
        return request.getPreferences();
    }

    @Override
    public Enumeration getProperties(String name) {
        return request.getProperties(name);
    }

    @Override
    public String getProperty(String name) {
        return request.getProperty(name);
    }

    @Override
    public Enumeration getPropertyNames() {
        return request.getParameterNames();
    }

    @Override
    public String getResponseContentType() {
        return request.getResponseContentType();
    }

    @Override
    public Enumeration getResponseContentTypes() {
        return request.getResponseContentTypes();
    }

    @Override
    public WindowState getWindowState() {
        return request.getWindowState();
    }

    @Override
    public boolean isPortletModeAllowed(PortletMode mode) {
        return request.isPortletModeAllowed(mode);
    }

    @Override
    public boolean isWindowStateAllowed(WindowState state) {
        return request.isWindowStateAllowed(state);
    }

    public String getETag() {
        // TODO Auto-generated method stub
        return null;
    }

}
