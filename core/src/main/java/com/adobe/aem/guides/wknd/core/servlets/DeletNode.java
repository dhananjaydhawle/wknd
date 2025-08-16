package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/deletNode")

public class DeletNode extends SlingAllMethodsServlet {

    @Reference
    private ResourceResolverFactory resolverFactory;
}
