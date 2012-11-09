
package com.cfinnovate.interoperability.factory;

import com.cfinnovate.interoperability.cfmlobject.CfmlObject;
import com.cfinnovate.interoperability.cfmlobject.OpenBdCFC;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CfmlObjectFactory {
    String cfcPath;

    public CfmlObjectFactory(String cfcPath) {
        this.cfcPath = cfcPath;
    }

    public CfmlObject getCfcInstance(HttpServletRequest request,
            HttpServletResponse response, ServletContext ctx) throws Exception {

        return new OpenBdCFC(cfcPath, request, response, ctx);
    }

}
