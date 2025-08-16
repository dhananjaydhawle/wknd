package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/createVarNodeRR")

public class NodeServlet extends SlingAllMethodsServlet {

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put(ResourceResolverFactory.SUBSERVICE,"wkndServiceUser");

        // DElete
        String nodePath = request.getParameter("path");

        try {
            ResourceResolver serviceResolver = resolverFactory.getServiceResourceResolver(params);
            Resource parentResource = serviceResolver.getResource("/var/myAppData");
            if (parentResource == null) {
                Resource varRoot = serviceResolver.getResource("/var");
                parentResource = serviceResolver.create(varRoot, "myAppData", new HashMap<>());
            }
/// ////////////////////////////////////////////////////////////////////////////////////////////////////////

            Resource nodeDelete = serviceResolver.getResource(nodePath);


            serviceResolver.delete(nodeDelete);
            serviceResolver.commit();

            response.getWriter().write("Deleted node: " + nodePath);
/// //////////////////////////////////////////////////////////////////////////////////////////////////
       /*     Map<String, Object> props = new HashMap<>();
            props.put("title", "Sample Node");
            props.put("createdBy", "Service Resource Resolver");
            props.put("status", "active");

            Resource newNode = serviceResolver.create(parentResource,"node-", props);

            serviceResolver.commit();

            response.getWriter().write("Node created at: " + newNode.getPath());

            */

        } catch (LoginException | PersistenceException e) {
            response.getWriter().write("Service login failed: " + e.getMessage());
        } catch (IOException e) {
            response.getWriter().write("Error saving node: " + e.getMessage());

        }


    }


}
