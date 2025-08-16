package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/getCountryStateLanguage")
public class ListServlet extends SlingAllMethodsServlet {

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String message = "Hello! ListServlet";
        String id = request.getParameter("id");

        JsonObject jsonResponse = new JsonObject();

        if(StringUtils.isNotEmpty(id) && id.equals("1")) {
            jsonResponse.addProperty("country", "India");
            jsonResponse.addProperty("state", "Maharashtra");
            jsonResponse.addProperty("language", "Marathi");
        }else{
            jsonResponse.addProperty("message", "invalid id!");
        }


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(jsonResponse.toString());

        }


    }
