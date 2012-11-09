
package com.cfinnovate.interoperability.wrapper;

import javax.portlet.PortletResponse;
import javax.portlet.RenderResponse;

public class FauxRenderResponse extends HttpServletResponseWrapper implements
        RenderResponse {

    public FauxRenderResponse(PortletResponse reponse) {
        super(reponse);
    }

}
