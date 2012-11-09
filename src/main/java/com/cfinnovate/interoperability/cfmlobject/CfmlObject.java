
package com.cfinnovate.interoperability.cfmlobject;

import java.util.Map;

public interface CfmlObject {

    public Object invoke(String method, Map<String, ? extends Object> args);

    public String invokeReturnAsString(String string) throws Exception;
}
