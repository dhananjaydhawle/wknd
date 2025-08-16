package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/wknd/magazine")
public class ImageServlet extends SlingAllMethodsServlet {

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        //String pagePath = "/content/wknd/language-masters/en/magazine";

        String pagePath = request.getParameter("pagePath");
        JsonObject jsonResponse = new JsonObject();


        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource pageResource = resourceResolver.getResource(pagePath);
        Resource jcrResource = resourceResolver.getResource(pageResource.getPath()+"jcr:content");
        Resource rtResource = resourceResolver.getResource(jcrResource.getPath()+ "root");

        if(StringUtils.isNotEmpty(pagePath)) {

            ValueMap props = pageResource.getValueMap();
            String title = props.get("jcr:title", String.class);
            String template = props.get("cq:template", String.class);
            String type = props.get("sling:resourceType", String.class);


            jsonResponse.addProperty("title", title);
            jsonResponse.addProperty("template", template);
            jsonResponse.addProperty("type", type);

        } else {
            jsonResponse.addProperty("message", "invalid");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonResponse.toString());

    }
}
