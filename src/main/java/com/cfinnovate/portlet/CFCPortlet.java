
package com.cfinnovate.portlet;

//portlet APIs
import com.cfinnovate.interoperability.cfmlobject.CfmlObject;
import com.cfinnovate.interoperability.factory.CfmlObjectFactory;
import com.cfinnovate.interoperability.wrapper.FauxRenderRequest;
import com.cfinnovate.interoperability.wrapper.FauxRenderResponse;
import com.cfinnovate.interoperability.wrapper.HttpServletRequestWrapper;
import com.cfinnovate.interoperability.wrapper.HttpServletResponseWrapper;
import com.cfinnovate.interoperability.wrapper.ServletContextWrapper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

public class CFCPortlet extends GenericPortlet {

    private static final long serialVersionUID = 1L;
    public static String CFC_PATH_PARAM = "cfcName";
    private CfmlObjectFactory cfcFactory;

    @Override
    public void init() throws PortletException {
        cfcFactory = new CfmlObjectFactory(getPortletConfig().getInitParameter(
                "cfcName"));
        // System.out.println(getPortletConfig().getInitParameter(CFC_PATH_PARAM));
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {

        String mode = null;
        if (request.getPortletMode() == PortletMode.VIEW) {
            mode = "VIEW";
        } else if (request.getPortletMode() == PortletMode.EDIT) {
            mode = "EDIT";
        } else if (request.getPortletMode() == PortletMode.HELP) {
            mode = "HELP";
        }

        String windowState = getWindowStateAsString(request);

        Map portletScope = new HashMap();
        portletScope.put("MODE", mode);
        portletScope.put("WINDOW", windowState);
        portletScope.put("REQUEST", request);
        portletScope.put("RESPONSE", response);
        portletScope.put("CONFIG", getPortletConfig());
        portletScope.put("PARAMETERS", createParameterMap(request));
        request.setAttribute("PORTLET", portletScope);
        HttpServletRequestWrapper hreq = new HttpServletRequestWrapper(request, getPortletConfig());
        HttpServletResponseWrapper hres = new HttpServletResponseWrapper(response);
        ServletContextWrapper sctx = new ServletContextWrapper(getPortletContext());
        try {
            CfmlObject cfc = cfcFactory.getCfcInstance(hreq, hres, sctx);
            response.getWriter().append(cfc.invokeReturnAsString("doView"));
        } catch (Exception e) {
            throw new PortletException(e);
        }
        // PortletRequestDispatcher rd =
        // getPortletContext().getRequestDispatcher("/WEB-INF/portlets/hotswap2.cfm");
        // rd.include(request, response);
    }

    private String getWindowStateAsString(RenderRequest request) {
        String windowState = null;
        WindowState ws = request.getWindowState();
        if (ws == WindowState.NORMAL) {
            windowState = "NORMAL";
        } else if (ws == WindowState.MAXIMIZED) {
            windowState = "MAXIMIZED";
        } else if (ws == WindowState.MINIMIZED) {
            windowState = "MINIMIZED";
        } else { // allow for custom states
            windowState = ws.toString();
        }
        return windowState;
    }

    @Override
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException {
        try {
            // DefaultPortletAction action =
            // (DefaultPortletAction)event.getAction(););
            Map portletScope = new HashMap();
            portletScope.put("MODE", "ACTION");
            portletScope.put("REQUEST", request);
            portletScope.put("CONFIG", getPortletConfig());
            portletScope.put("PARAMETERS", createParameterMap(request));
            request.setAttribute("PORTLET", portletScope);

            // Include the view jsp
            // getPortletConfig().getContext().include("/WEB-INF/portlets/portlet.cfm",
            // request, new DummyHttpServletResponse());
            PortletRequestDispatcher rd = getPortletContext()
                    .getRequestDispatcher("/WEB-INF/portlets/action.cfm");
            rd.include(new FauxRenderRequest(request, getPortletConfig()), new FauxRenderResponse(
                    response));
        } catch (Exception e) {
            e.printStackTrace();
            throw new PortletException(e);
        }
    }

    @Override
    public String getTitle(RenderRequest request) {
        String title = getPortletConfig().getInitParameter("title");
        if (title == null) {
            title = "CF Portlet";
        }
        return title;

    }

    private Map createParameterMap(PortletRequest request) {
        Map map = new HashMap();
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            map.put(name.toUpperCase(), request.getParameter(name));
        }
        return map;
    }
}
