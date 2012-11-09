
package com.cfinnovate.portlet;

import com.cfinnovate.interoperability.factory.CfmlObjectFactory;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class InstallCfmlFile {
    public CfmlObjectFactory cfcFactory;

    public static void execute(PortletContext ctx, PortletConfig cfg, RenderRequest request,
            RenderResponse response) throws PortletException, IOException {

        URL thing = InstallCfmlFile.class.getClassLoader()
                .getResource("META-INF/support/hello.cfm");

        File dest = new File(ctx.getRealPath("/WEB-INF/portlets/hello2.cfm"));
        FileUtils.copyURLToFile(thing, dest);
    }
}
