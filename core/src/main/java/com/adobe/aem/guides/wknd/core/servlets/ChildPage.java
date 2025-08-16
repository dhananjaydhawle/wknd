package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.xfa.ut.StringUtils;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/getchildpage")

public class ChildPage extends SlingSafeMethodsServlet {

    private static final Gson gson = new Gson();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String childPagePath = request.getParameter("path");

        if(StringUtils.isEmpty(childPagePath)) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"page path parameter is required");
            return;
        }
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource rootPageResource = resourceResolver.getResource(childPagePath);

        Iterator<Resource> itr = rootPageResource.listChildren();

        ArrayList<PageJSON> resultDataList = new ArrayList<>();

        while (itr.hasNext()) {
            Resource childResource = itr.next();
            Resource childResourceJCR = resourceResolver.getResource(childResource.getPath());

            Resource jcrResource = resourceResolver.getResource(childResource.getPath() + "/jcr:content");

            if(childResourceJCR != null && !childResourceJCR.getPath().contains("jcr:content")) {
                ValueMap vm = childResourceJCR.adaptTo(ValueMap.class);

                String name = childResourceJCR.getName();
                String path = childResourceJCR.getPath();
                String title = null;

                if(jcrResource != null){
                    ValueMap childJCRVM = jcrResource.adaptTo(ValueMap.class);
                    title = childJCRVM.get("jcr:title", String.class);
                }

                PageJSON pageJSON = new PageJSON(title, path, name);
                resultDataList.add(pageJSON);
            }
        }

        response.getWriter().write(gson.toJson(resultDataList));
    }

    class PageJSON{
        String title;
        String path;
        String name;

        public PageJSON(String title, String path, String name) {
            this.title = title;
            this.path = path;
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public String getPath() {
            return path;
        }

        public String getName() {
            return name;
        }
    }

}
