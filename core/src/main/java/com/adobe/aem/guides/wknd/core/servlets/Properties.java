package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component(service = Servlet.class)
@SlingServletPaths("/bin/wknd/readpage")
public class Properties extends SlingAllMethodsServlet {

    private static final String SERVICE_USER = "wkndServiceUser";

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject jsonResponse = new JsonObject();


        String pagePath = "/content/wknd/language-masters/en";

        ResourceResolver resourceResolver = request.getResourceResolver();
        // Get resource

        Resource pageResource = resourceResolver.getResource(pagePath);
        Resource pageResourceJCR = resourceResolver.getResource(pageResource.getPath()+"/jcr:content");

        if (pageResourceJCR != null) {
            ValueMap props = pageResource.getValueMap();
            String resourceType = props.get("sling:resourceType", String.class);
            // Example properties
            String title = props.get("jcr:title", String.class);


            jsonResponse.addProperty("resourceType", resourceType);
            jsonResponse.addProperty("title", title);

        } else {
            jsonResponse.addProperty("status", "error");
            jsonResponse.addProperty("message", "Resource not found at " + pagePath);
        }


        response.getWriter().write(jsonResponse.toString());
    }
  }
