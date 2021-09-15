package edu.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Request wrapper.
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private final Map<String,String> params = new HashMap<>();

    /**
     * Instantiates a new Request wrapper.
     *
     * @param request the request
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name){
        if(params.get(name) != null) {
            return params.get(name);
        }
        return super.getParameter(name);
    }

    /**
     * Set parameter.
     *
     * @param name      the name
     * @param parameter the parameter
     */
    public void setParameter(String name, String parameter){
        if(super.getParameter(name) == null || !super.getParameter(name).equals(parameter)) {
            params.put(name, parameter);
        }
    }
}
