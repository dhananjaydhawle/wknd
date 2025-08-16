package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.Template;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.graalvm.compiler.nodes.NodeView;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Iterator;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Refresh {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String state;

    @ValueMapValue
    private String develop;

    @ValueMapValue
    private String print;

    @ValueMapValue
    private String totalStates;

    @ValueMapValue
    private String colour;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String photos;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public String getDevelop() {
        return develop;
    }

    public String getPrint() {
        return print;
    }

    public String getTotalStates() {
        return totalStates;
    }

    public String getColour() {
        return colour;
    }

    public String getImage() {
        return image;
    }

    public String getPhotos() {
        return photos;
    }

    @ScriptVariable
    private Page currentPage;

    String pageName;
    String pageDescription;
    String templatePath;
    String designPath;
    String lastModified;
    String  navigationTitle ;
    ArrayList<String> namelist;


    @PostConstruct

    public void init() {
        if (currentPage != null) {
            pageName = currentPage.getName();
            pageDescription = currentPage.getDescription();
            templatePath = currentPage.getTemplate().getPath();
            designPath = currentPage.getPath();
            lastModified = currentPage.getLastModified().toString();
            navigationTitle = currentPage.getNavigationTitle();  //getNavigationTitle();

            currentPage.getTemplate().getPath();
            Template template = currentPage.getTemplate();

            Resource resource = currentPage.getContentResource();
            String name = resource.getName();
            String path = resource.getPath();

            namelist = new ArrayList<>();
            namelist = getChildPageName();

        }

    }

    public ArrayList<String> getChildPageName() {
        ArrayList<String> nameList = new ArrayList<>();
        Iterator<Page> itr = currentPage.listChildren();
        if(currentPage != null) {
            while (itr.hasNext()) {
                Page child = itr.next();
                nameList.add(child.getName());
            }
        }
        return nameList;
    }

}