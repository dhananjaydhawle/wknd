package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Iterator;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageList {

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    private String selectpage;

    @SlingObject
    private ResourceResolver resourceResolver;



    String pageTitle;
    String pageDescription;

    ArrayList<String> titleList;
    ArrayList<String> descList;


    @PostConstruct
    public void init() throws RepositoryException {
        if (currentPage != null) {
            pageTitle = currentPage.getTitle();
            pageDescription = currentPage.getDescription();

            titleList = new ArrayList<>();
            descList = new ArrayList<>();

            titleList = getChildPageTitles();
            descList = getChildPageDesc();

            Resource pageResource = resourceResolver.getResource(selectpage);

            Node pageNode = pageResource.adaptTo(Node.class);
            String parentNodeName = pageNode.getParent().getName();//node api

            Iterator<Node> nodeIterator = pageNode.getNodes();




            //iterator child  ;;getNodes children mehod; get node





        }
    }

    public ArrayList<String> getChildPageTitles() {
        ArrayList<String> titles = new ArrayList<>();
        if (currentPage != null) {

            Iterator<Page> itr = currentPage.listChildren();
            while (itr.hasNext()) {
                Page child = itr.next();
                titles.add(child.getTitle());
            }
        }
        return titles;
    }
    public ArrayList<String>  getChildPageDesc() {
        ArrayList<String> desc = new ArrayList<>();
        if(currentPage != null) {

            Iterator<Page> itr = currentPage.listChildren();
            while (itr.hasNext()) {
                Page child = itr.next();
                desc.add(child.getDescription());
            }
        }
        return desc;
    }

}