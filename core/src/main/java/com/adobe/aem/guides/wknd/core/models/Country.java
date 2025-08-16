package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.graalvm.compiler.lir.LIRInstruction;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Iterator;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Country {

    @ValueMapValue(name = PROPERTY_RESOURCE_TYPE, injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "No resourceType")
    protected String resourceType;

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

    @ScriptVariable
    private Page currentPage;       //Injecting  current page

    String pageTitle;
    String pageDescription;
    String templatePath;
    String designPath;
    ArrayList<String> titleList;
    ArrayList<String> descList;
    ArrayList<String> pathList;

    @PostConstruct
    public void init() {
        if(currentPage != null) {           //fetching page data/page properties

            pageTitle = currentPage.getTitle();
            pageDescription = currentPage.getDescription();
            templatePath = currentPage.getTemplate().getPath();
            designPath = currentPage.getPath();

            titleList = new ArrayList<>();
            descList =     new ArrayList<>();
            pathList= new ArrayList<>();

            titleList = getChildPageTitles();
            descList = getChildPageDesc();
            pathList = getChildPagepathList();
        }
    }

    public ArrayList<String> getChildPageTitles() {         //Method
        ArrayList<String> titles = new ArrayList<>();   //Empty ArrayList
        if (currentPage != null) {

            Iterator<Page> itr = currentPage.listChildren();    //listing children
            while (itr.hasNext()) {
                Page child = itr.next();
                titles.add(child.getTitle()   ); // Use getName() or getPath() as needed
            }
        }
        return titles;
    }

    public ArrayList<String> getChildPageDesc() {         //Method
        ArrayList<String> desc = new ArrayList<>();   //Empty ArrayList
        if (currentPage != null) {

            Iterator<Page> itr = currentPage.listChildren();    //listing children
            while (itr.hasNext()) {
                Page child = itr.next();
                desc.add(child.getDescription()); // Use getName() or getPath() as needed
            }
        }
        return desc;
    }
    public ArrayList<String> getChildPagepathList() {         //Method
        ArrayList<String> path = new ArrayList<>();   //Empty ArrayList
        if (currentPage != null) {

            Iterator<Page> itr = currentPage.listChildren();    //listing children
            while (itr.hasNext()) {
                Page child = itr.next();
                pathList.add(child.getPath()); // Use getName() or getPath() as needed
            }
        }
        return path;
    }


    public String getPageTitle() {
        return pageTitle;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public String getDesignPath() {
        return designPath;
    }

    public String getResourceType() {
        return resourceType;
    }

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

    public ArrayList<String> getDescList() {
        return descList;
    }

    public ArrayList<String> getPathList() {
        return pathList;
    }

    public ArrayList<String> getTitleList() {
        return titleList;
    }
    public ArrayList<String> getdescList() { return descList; }
    public ArrayList < String> getpathlist() {return pathList;}
}
