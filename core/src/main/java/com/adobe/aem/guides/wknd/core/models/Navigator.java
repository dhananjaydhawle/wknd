package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import jdk.nashorn.internal.ir.WhileNode;
import org.apache.commons.math.genetics.Population;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)public class Navigator {
    @ValueMapValue
    private String title;

    @ValueMapValue
    private String childpage;

    @ValueMapValue
    private String parentpage;

    Map<String, String> pageTitleMap;

    @SlingObject
    Resource currentResource;

    @SlingObject
    ResourceResolver resourceResolver;

    ArrayList<String> pageList;


    @ScriptVariable
    Page currentPage;
    ArrayList<String> templateLIst;
    ArrayList<String> LastModifiedList;
    ArrayList<String> parentList;
    ArrayList<String> PageList;


    @PostConstruct
    public void init() {
        if (resourceResolver != null) {
            Resource resourcepage = resourceResolver.getResource(childpage);
            pageList = new ArrayList<>();
            pageList = getPageList(resourcepage);
            Resource resourcenewpage = resourceResolver.getResource(parentpage);
            parentList = new ArrayList<>();
            parentList = getParentList(resourcenewpage);

            pageList = new ArrayList<>();

            // Resource resourcetemplate = resourceResolver.getResource(currentPage);
            templateLIst = new ArrayList<>();
            templateLIst = getTemplateliST(currentPage);
            LastModifiedList = getLastModifiedList(currentPage);
            LastModifiedList = getLastModifiedList(currentPage);
            pageList = getPageList(currentPage.getContentResource());


            //hashmap
            pageTitleMap = new HashMap<>();
            populatePageTitleMap(resourcepage);

            //ValueMap
            resourceValuemap(currentResource);

        }

    }

    //method for resourceValuemap
    private void resourceValuemap(Resource resource){
        if(resource != null){
            ValueMap vm = resource.adaptTo(ValueMap.class);

            String title = vm.get("title", String.class);
            String lastModified = vm.get("jcr:lastModifiedBy", String.class);
        }
    }


    private ArrayList<String> getParentList(Resource newPage) {
        ArrayList<String> parentList = new ArrayList<>();
        Iterator<Resource> itr = newPage.listChildren();

        while (itr.hasNext()) {
            Resource updatedResource = itr.next();
            parentList.add(updatedResource.getPath());
        }
        return parentList;

    }

    private ArrayList<String> getTemplateliST(Page currentPage) {
        ArrayList<String> templateList = new ArrayList<>();
        Iterator<Page> itr = currentPage.listChildren();

        while (itr.hasNext()) {
            Page page = itr.next();
            templateList.add(page.getTemplate().getPath());
        }
        return templateList;
    }

    private ArrayList<String> getLastModifiedList(Page CurretPage) {
        ArrayList<String> LastModifiedList = new ArrayList<>();
        Iterator<Page> itr = currentPage.listChildren();

        while (itr.hasNext()) {
            Page page = itr.next();
            LastModifiedList.add(page.getLastModifiedBy());
        }
        return LastModifiedList;

    }

    private ArrayList<String> getPageList(Resource page) {

        ArrayList<String> pageList = new ArrayList<>();
        Iterator<Resource> itr = page.listChildren();

        while (itr.hasNext()) {
            Resource listPageResource = itr.next();
            pageList.add(listPageResource.getPath());
        }
        return pageList;

    }

    private  Map<String, String> populatePageTitleMap (Resource PageResource) {
        Map<String, String> pageTitleMap = new HashMap<>();

            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Iterator<Resource> itr = PageResource.listChildren();
            while (itr.hasNext()) {
                Resource childRes = itr.next();
                Page childPage = pageManager.getContainingPage(childRes);
                pageTitleMap.put(childPage.getPath(), childPage.getTitle());
            }
            return pageTitleMap;
    }
    private Map<String, String> getPageList (Page currentPage) {
        Map<String, String> pageList = new HashMap<>();

        Iterator<Page> itr = currentPage.listChildren();
        while (itr.hasNext()) {
            Page page = itr.next();
            String title = page.getTitle();
            String path = page.getPath();
               pageList.put(title, path);
        }
        return pageList;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getPageList() {
        return pageList;
    }

    public Map<String, String> getPageTitleMap() {
        return pageTitleMap;
    }
}
