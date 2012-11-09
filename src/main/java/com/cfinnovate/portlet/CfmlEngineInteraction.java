
package com.cfinnovate.portlet;

import com.cfinnovate.interoperability.wrapper.FauxRenderRequest;
import com.cfinnovate.interoperability.wrapper.FauxRenderResponse;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

public class CfmlEngineInteraction {
    private PortletContext ctx;
    private PortletConfig cfg;

    public CfmlEngineInteraction(PortletContext portletContext,
            PortletConfig portletConfig) {
        this.ctx = portletContext;
        this.cfg = portletConfig;
    }

    public void installFile(String resourcePath) throws PortletException {

        installFile(resourcePath, "/WEB-INF/" + resourcePath);
    }

    public void installFile(String resourcePath, String destination)
            throws PortletException {
        URL thing = InstallCfmlFile.class.getClassLoader().getResource(
                "META-INF" + resourcePath);

        File dest = new File(ctx.getRealPath(destination));
        try {
            FileUtils.copyURLToFile(thing, dest);
        } catch (IOException e) {
            throw new PortletException(e);
        }
    }

    public void prepVariables(RenderRequest request, RenderResponse response)
            throws PortletException {
        Map<String, Object> scope = new HashMap<String, Object>();
        scope.put("windowstate", getWindowStateAsString(request));
        prepVariables(request, response, scope);

    }

    public void prepVariables(ActionRequest request, ActionResponse response)
            throws PortletException {
        Map<String, Object> scope = new HashMap<String, Object>();
        prepVariables(request, response, scope);
    }

    public void prepVariables(PortletRequest request, PortletResponse response,
            Map<String, Object> scope) throws PortletException {
        scope.put("mode", request.getPortletMode().toString());
        scope.put("PortletRequest", request);
        scope.put("PortletConfig", cfg);
        scope.put("parameters", request.getParameterMap());
        scope.put("PortletHelper", new PortletHelper(request, response));
        scope.put("PortletPreferences", request.getPreferences().getMap());
        request.setAttribute("CFML_Variables", scope);
    }

    public void callCfml(PortletRequest request, PortletResponse response,
            String path) throws PortletException {
        System.out.println("callCfml_1 (path): " + path);
        callCfml(new FauxRenderRequest(request, cfg), new FauxRenderResponse(
                response), path);
    }

    public void callCfml(RenderRequest request, RenderResponse response,
            String path) throws PortletException {
        PortletRequestDispatcher rd;
        System.out.println("callCfml_2 (path): " + path);
        try {
            if (request.getAttribute("CFML_Variables") != null) {
                String[] pathSplit = path.split("\\?");
                String qString = "";
                if (pathSplit.length == 2)
                {
                    qString = pathSplit[1];
                }
                String oldPath = pathSplit[0];
                request.setAttribute("cfmlportlet_path", oldPath);
                rd = ctx.getRequestDispatcher(cfg.getInitParameter("appRoot") + "cfmlRunner.cfm"
                        + "?" + qString);
            } else {
                rd = ctx.getRequestDispatcher(path);
            }
            rd.include(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PortletException(e);
        }
    }

    public void callCfml(RenderRequest request, RenderResponse response,
            String path, String appRoot) throws PortletException {
        PortletRequestDispatcher rd;
        System.out.println("callCfml_3 (request.getContextPath()): " + request.getContextPath());
        System.out.println("callCfml_3 (path): " + path);
        System.out.println("callCfml_3 (appRoot): " + appRoot);
        System.out.println("callCfml_3 (ctx.getPortletContextName()): "
                + ctx.getPortletContextName());
        System.out.println("callCfml_3 (ctx.getRealPath(path)): " + ctx.getRealPath(path));
        System.out.println("callCfml_3 (ctx.getRealPath(appRoot)): " + ctx.getRealPath(appRoot));
        System.out.println("callCfml_3 (ctx.getServerInfo()): " + ctx.getServerInfo());
        try {
            if (request.getAttribute("CFML_Variables") != null) {
                String[] pathSplit = path.split("\\?");
                String qString = "";
                if (pathSplit.length == 2)
                {
                    qString = pathSplit[1];
                }
                String oldPath = pathSplit[0];
                request.setAttribute("cfmlportlet_path", oldPath);
                System.out.println("callCfml_3 (oldPath): " + oldPath);
                //rd = ctx.getRequestDispatcher(ctx.getRealPath(appRoot));
                rd = ctx.getRequestDispatcher(appRoot + "cfmlRunner.cfm"); // +
                                                                           // "?"
                                                                           // +
                                                                           // qString);
                System.out.println("callCfml_3 (rd_1): " + appRoot + "cfmlRunner.cfm"); // +
                                                                                        // "?"
                                                                                        // +
                                                                                        // qString);
            } else {
                rd = ctx.getRequestDispatcher(path);
                System.out.println("callCfml_3 (rd_2): " + path);
            }
            rd.include(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PortletException(e);
        }
    }

    private String getWindowStateAsString(RenderRequest request) {
        WindowState ws = request.getWindowState();
        System.out.println("getWindowStateAsString (ws): " + ws.toString());
        if (ws == WindowState.NORMAL) {
            return "NORMAL";
        } else if (ws == WindowState.MAXIMIZED) {
            return "MAXIMIZED";
        } else if (ws == WindowState.MINIMIZED) {
            return "MINIMIZED";
        } else { // allow for custom states
            return ws.toString();
        }
    }

    public void prepareCfmlApp() {
        Enumeration<String> files = cfg.getInitParameterNames();
        while (files.hasMoreElements()) {
            // String file = cfg.getInitParameter(files.nextElement());
        }
    }
}
