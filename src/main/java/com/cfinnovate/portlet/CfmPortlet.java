
package com.cfinnovate.portlet;

//portlet APIs
import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

public class CfmPortlet extends GenericPortlet {

    private static final long serialVersionUID = 1L;
    private String cfmlPortletRoot;

    @Override
    public void init() throws PortletException {
        // System.out.println(getPortletConfig().getInitParameterNames());

        CfmlEngineInteraction cei = getCfmlEngineInteraction();
        installPortletHelperFile();
        cfmlPortletRoot = getPortletConfig().getInitParameter("appRoot");
        if (cfmlPortletRoot != null) {
            System.out.println("cfmlPortletRoot: " + cfmlPortletRoot);
            cei.installFile("/cfmlRunner.cfm", cfmlPortletRoot
                    + "cfmlRunner.cfm");
        }
    }

    private void installPortletHelperFile() throws PortletException {
        CfmlEngineInteraction cei = getCfmlEngineInteraction();
        cei.installFile("/support/RunPortlet.cfm");
    }

    @Override
    protected void doDispatch(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        WindowState state = request.getWindowState();
        String mode = request.getPortletMode().toString();
        System.out.println("doDispatch (mode): " + mode);
        if (!state.equals(WindowState.MINIMIZED)) {
            String cfmlFile = resolveCfmPath(mode)
                    + getParametersAsQueryString(request);
            System.out.println("doDispatch (cfmlFile): " + cfmlFile);
            System.out.println("doDispatch (cfmlPortletRoot): " + cfmlPortletRoot);
            CfmlEngineInteraction cei = getCfmlEngineInteraction();
            cei.prepVariables(request, response);
            cei.callCfml(request, response, cfmlFile, cfmlPortletRoot);
        }

    }
    
    @Override
    protected void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        WindowState state = request.getWindowState();
        String mode = request.getPortletMode().toString();
        System.out.println("doDispatch (mode): " + mode);
        if (!state.equals(WindowState.MINIMIZED)) {
            String cfmlFile = resolveCfmPath(mode)
                    + getParametersAsQueryString(request);
            System.out.println("doDispatch (cfmlFile): " + cfmlFile);
            System.out.println("doDispatch (cfmlPortletRoot): " + cfmlPortletRoot);
            CfmlEngineInteraction cei = getCfmlEngineInteraction();
            cei.prepVariables(request, response);
            cei.callCfml(request, response, cfmlFile, cfmlPortletRoot);
        }

    }

    @Override
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException {
        String path = resolveCfmPath("action")
                + getParametersAsQueryString(request);
        System.out.println("processAction (path): " + path);
        CfmlEngineInteraction cei = getCfmlEngineInteraction();
        cei.prepVariables(request, response);
        cei.callCfml(request, response, path);
    }

    private String cleanPath(String path) {
        System.out.println("cleanPath (path): " + path);
        if ("/".equals(path.substring(0, 1))) {
            return path;
        } else {
            return "/" + path;
        }
    }

    private String getParametersAsQueryString(PortletRequest request) {
        String qString = "?";
        Map<String, String[]> parameters = request.getParameterMap();
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            String value = "";
            for (int i = 0; i < values.length; i++) {
                value += values[i] + ",";
            }
            value = value.substring(0, value.length() - 1);
            qString += key + "=" + value + "&";
        }
        return qString;
    }

    private CfmlEngineInteraction getCfmlEngineInteraction() {
        Object cei = getPortletContext().getAttribute("CfmlEngineInteraction");
        if (!(cei instanceof CfmlEngineInteraction)) {
            cei = new CfmlEngineInteraction(getPortletContext(),
                    getPortletConfig());
            getPortletContext().setAttribute("CfmlEngineInteraction", cei);
        }
        return (CfmlEngineInteraction) cei;
    }

    private String resolveCfmPath(String portletMode) {

        String path = getInitParameter(portletMode);
        if (path == null || "".equals(path)) {
            path = cfmlPortletRoot + portletMode + ".cfm";
        }
        return cleanPath(path);
    }
}
