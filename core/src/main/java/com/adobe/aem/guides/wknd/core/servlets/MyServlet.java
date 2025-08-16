package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

/*@Component(service = {Servlet.class},
properties = {
        "sling.servlet.paths=/bin/first",
        "sling.servlet.methods=GET",
        "sling.servlet.extensions=json"
        })*/
@Component(service = Servlet.class)
@SlingServletPaths("/bin/first")
public class MyServlet extends SlingAllMethodsServlet {

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String message="Hello! My First Servlet";

        String id = request.getParameter("id");
        JsonObject jsonObject = new JsonObject();

        if(StringUtils.isNotEmpty(id) && id.equals("1")) {
            jsonObject.addProperty("name", "dhananjay");
            jsonObject.addProperty("age", "20");
            jsonObject.addProperty("gender", "male");

        }else{
            jsonObject.addProperty("message", "invalid id!");
        }

        response.setContentType("application/json");   ///setting the response ty[e pf the data]
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonObject.toString());


    }

}
