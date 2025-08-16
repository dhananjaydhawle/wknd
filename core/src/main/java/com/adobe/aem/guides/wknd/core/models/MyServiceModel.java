package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.service.MyService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class  MyServiceModel {

    @ScriptVariable
    Page currentPage;

    @OSGiService
    MyService myService;

    ArrayList<String> pageTitles;

    @PostConstruct
    public void init(){
        pageTitles = new ArrayList<>();

        if(myService != null){
            pageTitles = myService.getChildPageTitles(currentPage);
        }
    }

    public ArrayList<String> getPageTitles() {
        return pageTitles;
    }
}
