
package com.cfinnovate.interoperability.cfmlobject;

import com.bluedragon.plugin.ObjectCFC;
import com.bluedragon.plugin.PluginManager;
import com.naryx.tagfusion.cfm.application.cfApplicationData;
import com.naryx.tagfusion.cfm.engine.cfSession;
import com.naryx.tagfusion.cfm.engine.variableStore;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenBdCFC implements CfmlObject {

    private String cfcName;
    private ObjectCFC cfc;
    private cfSession session;

    public OpenBdCFC(String path, HttpServletRequest request, HttpServletResponse response,
            ServletContext ctx) throws Exception
    {
        super();
        cfcName = path;
        // System.out.println(path);
        session = new cfSession(request, response, ctx);
        cfApplicationData appData = (cfApplicationData) ctx.getAttribute("application_" + path);
        if (appData == null)
        {
            appData = new cfApplicationData(path);
            appData.init(session, true, 1L, 1L, false, false, true, false, "", "", "", "", false,
                    "");
            ctx.setAttribute("application_" + path, appData);
        }

        session.setQualifiedData(variableStore.APPLICATION_SCOPE, appData);
        PluginManager pm = new PluginManager(null);
        // System.out.println(cfcName);
        cfc = pm.createCFC(session, cfcName);

    }

    public Object invoke(String method, Map<String, ? extends Object> args) {
        // TODO Auto-generated method stub
        return null;
    }

    public String invokeReturnAsString(String methodName) throws Exception {
        // TODO Auto-generated method stub
        return cfc.runMethodReturnString(session, methodName);
    }

}
