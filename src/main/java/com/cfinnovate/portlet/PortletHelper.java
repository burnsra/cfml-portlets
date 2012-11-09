
package com.cfinnovate.portlet;

import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

public class PortletHelper {
    private PortletRequest request;
    private PortletResponse response;

    public PortletHelper(PortletRequest req, PortletResponse res) {
        this.request = req;
        this.response = res;
    }

    /**
     * Convenience method to get a Raw PortletURL.
     * 
     * @exception PortletException if the mode is incorrect
     */
    private PortletURL createActionUrl() throws PortletException {
        PortletURL portletUrl;
        portletUrl = getRenderResponse().createActionURL();
        return portletUrl;
    }

    private RenderResponse getRenderResponse()
            throws PortletException {
        if (response instanceof RenderResponse) {
            return (RenderResponse) response;

        } else {
            throw new PortletException(
                    "Chances are you are seeing this error because you are trying to do something that is only allowed"
                            +
                            " in a render request. "
                            + "Don't do that, it is not allowed");
        }
    }

    public String getWindowStateAsString()
    {
        return request.getWindowState().toString();
    }

    public String getActionUrl(Map<Object, Object> parameters)
            throws PortletException {
        return getPortletUrl(createActionUrl(), parameters, null, null);
    }

    public String getActionUrl(Map<Object, Object> parameters, String windowState, Boolean ssl)
            throws PortletException {
        return getPortletUrl(createActionUrl(), parameters, windowState, ssl);
    }

    public void setTitle(String title) throws PortletException
    {
        getRenderResponse().setTitle(title);
    }

    /**
     * Convenience method to get a Raw PortletURL.
     * 
     * @exception PortletException if the mode is incorrect
     */
    private PortletURL createRenderUrl() throws PortletException {
        PortletURL portletUrl;
        if (response instanceof RenderResponse) {
            portletUrl = ((RenderResponse) response).createRenderURL();

        } else {
            throw new PortletException(
                    "Chances are you are seeing this error because you are trying to get a URL in an action request. Don't do that, it is not allowed");
        }
        return portletUrl;
    }

    public String getRenderUrl(Map<Object, Object> parameters) throws PortletException {
        return getRenderUrl(parameters, null, null);
    }

    public String getRenderUrl(Map<Object, Object> parameters, String windowState, Boolean ssl)
            throws PortletException {
        return getPortletUrl(createRenderUrl(), parameters, windowState, ssl);
    }

    private String getPortletUrl(PortletURL portletUrl, Map<Object, Object> parameters,
            String windowState, Boolean ssl) throws PortletException {
        for (Object key : parameters.keySet()) {
            portletUrl.setParameter(key.toString(), parameters.get(key)
                    .toString());
        }
        if (windowState != null) {

            portletUrl.setWindowState(new WindowState(windowState));
        }
        if (ssl != null) {
            portletUrl.setSecure(ssl);
        }

        return portletUrl.toString();
    }

}
