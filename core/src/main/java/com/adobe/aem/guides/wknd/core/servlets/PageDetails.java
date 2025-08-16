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

@Component(service = Servlet.class)
@SlingServletPaths("/bin/getpagedetails")
public class PageDetails extends SlingSafeMethodsServlet {

    private static final Gson gson = new Gson();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String pagePath = request.getParameter("path");

        if(StringUtils.isEmpty(pagePath)) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"page path parameter is required");
            return;
        }
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource pageResource = resourceResolver.getResource(pagePath+ "/jcr:content/root/container/image");

        ValueMap vm = pageResource.adaptTo(ValueMap.class);
        String path = pageResource.getPath();
        String title = vm.get("jcr:title", String.class);
        String lastModified = vm.get("jcr:lastModifiedBy", String.class);
        String resourceType = vm.get("sling:resourceType", String.class);
        String selectimage = vm.get("selectimage", String.class);






        PageData pageData = new PageData(path, title, lastModified,resourceType,selectimage );
        response.getWriter().write(gson.toJson(pageData));

    }

    class PageData{

        String path;
        String title;
        String lastModified;
        String resourceType;
        String selectimage;




        public PageData(String path, String title, String lastModified, String resourceType, String selectimage) {
            this.path = path;
            this.title = title;
            this.lastModified = lastModified;
            this.resourceType = resourceType;
            this.selectimage = selectimage;
        }

       /* public PageData(String path, String title) {
            this.path = path;
            this.title = title;*/

        public String getPath() {
            return path;
        }

        public String getTitle() {
            return title;
        }

        public String getResourceType() {
            return resourceType;
        }

        public String getLastModified() {
            return lastModified;
        }

        public String getSelectimage() {
            return selectimage;
        }
    }

    }
