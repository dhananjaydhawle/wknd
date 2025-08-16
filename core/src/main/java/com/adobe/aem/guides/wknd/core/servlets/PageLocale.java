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
@SlingServletPaths("/bin/getpagellocale")

public class PageLocale extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    private static final Gson gson = new Gson();




    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String projectPath = request.getParameter("projectPath");

        if(StringUtils.isEmpty(projectPath)) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"project parameter is required");
            return;
        }
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource projectResource = resourceResolver.getResource(projectPath);

        if(projectResource == null){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "project resource not found at:"+ projectPath);
            return;
        }
        Resource selectedPagesResource = resourceResolver.getResource(projectResource.getPath() + "/selectedpages");
        Resource selectedLocalesResource = resourceResolver.getResource(projectResource.getPath() + "/selectedlocales");

        if(selectedPagesResource == null || selectedLocalesResource == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Required resource not found under:project resource");
            return;
        }
        ValueMap vm  = selectedPagesResource.adaptTo(ValueMap.class);
        String[] selectedPages = vm.get("item", String[].class);
        ValueMap localesVm = selectedLocalesResource.adaptTo(ValueMap.class);
        String[] selectedlocales = localesVm.get("item", String[].class);

        PageLocales pageLocales = new PageLocales( selectedPages, selectedlocales);

        response.setContentType("application/json");
        response.setStatus(SlingHttpServletResponse.SC_OK);
        response.getWriter().write(gson.toJson(pageLocales));

    }
    static class PageLocales {
        String[] selectedPages;
        String[] selectedLocales;
         public PageLocales(String[] selectedPages, String[] selectedLocales) {
             this.selectedPages = selectedPages;
             this.selectedLocales = selectedLocales;
         }

        public String[] getSelectedPages() {
            return selectedPages;
        }

        public String[] getSelectedLocales() {
            return selectedLocales;
        }
    }
}
