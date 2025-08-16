package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.service.PageService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Map;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageServiceModel {

    @ScriptVariable
    Page currentPage;

    @OSGiService
    PageService pageService;

    ArrayList<String> pageName;
    //hashmap
     Map<String, String> childPageMap;



    @PostConstruct
    public void init() {
        pageName = new ArrayList<>();

        if(pageService != null) {
            pageName = pageService.getchildPageName(currentPage);

                 //hashmap
            childPageMap = pageService.getChildPageMap(currentPage);
        }

    }

    public Map<String, String> getChildPageMap() {
        return childPageMap;
    }

    public ArrayList<String> getPageName() {
        return pageName;
    }
}
