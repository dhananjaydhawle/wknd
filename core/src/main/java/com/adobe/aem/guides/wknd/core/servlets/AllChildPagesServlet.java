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
import java.util.List;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/getallchildpages")

public class AllChildPagesServlet extends SlingSafeMethodsServlet {
    private static final Gson gson = new Gson();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String pagePath = request.getParameter("path");

        if (StringUtils.isEmpty(pagePath)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "page path parameter is required");
            return;
        }

        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource rootResource = resourceResolver.getResource(pagePath);
        if (rootResource == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Given path not found: " + pagePath);
            return;
        }

         List<PageJSON> resultList = new ArrayList<>();


        collectChildPages(rootResource, resourceResolver, resultList);

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(resultList));
    }


    private void collectChildPages(Resource parentResource, ResourceResolver resolver, List<PageJSON> resultList) {
        Iterator<Resource> children = parentResource.listChildren();
        while (children.hasNext()) {
            Resource child = children.next();
            if(!child.getPath().contains("jcr:content")) {
                String path = child.getPath();
                String name = child.getName();
                String title = null;
                String resourcetype = null;
                Resource jcrContent = resolver.getResource(path + "/jcr:content");
                if (jcrContent != null) {
                    ValueMap vm = jcrContent.adaptTo(ValueMap.class);
                    if (vm != null) {
                        title = vm.get("jcr:title", String.class);
                        resourcetype = vm.get("sling:resourceType", String.class);

                    }
                }

                ArrayList<PageJSON> childPagesArrayList = new ArrayList<>();
                Iterator<Resource> childPageIterator = child.listChildren();
                while (childPageIterator.hasNext())
                {
                 Resource childResource = childPageIterator.next();
                 String childPath = childResource.getPath();
                 ValueMap childVM = childResource.adaptTo(ValueMap.class);
                 String childTitle = childVM.get("jcr:title", String.class);
                 String childResourceType = childVM.get("sling:resourceType", String.class);

                 PageJSON childPageJSON = new PageJSON(childTitle, childPath, childResource.getName(), childResourceType, null);
                 childPagesArrayList.add(childPageJSON);
                }

                PageJSON pageJSON = new PageJSON(title, path, name, resourcetype, childPagesArrayList);
                resultList.add(pageJSON);
            }
        }
    }

    class PageJSON {
        String title;
        String path;
        String name;
        String  resourcetype;
        ArrayList<PageJSON> children;

        public PageJSON(String title, String path, String name,String resourcetype, ArrayList<PageJSON> children) {
            this.title = title;
            this.path = path;
            this.name = name;
            this.resourcetype = resourcetype;
            this.children = children;

        }

        public ArrayList<PageJSON> getChildren() {
            return children;
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

        public String getResourcetype() {
            return resourcetype;
        }
    }
}




