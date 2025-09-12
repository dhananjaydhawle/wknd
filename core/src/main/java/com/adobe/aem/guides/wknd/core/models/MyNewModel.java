package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.service.TestService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyNewModel {
    @ScriptVariable
    Page currentPage;

    @ValueMapValue
    private String title;

    @SlingObject
    private ResourceResolver resourceResolver;

    @OSGiService
    TestService testService;

    ArrayList<String> pageTitles;

    @PostConstruct
    public void init() {
        pageTitles = new ArrayList<>();
        if (title != null && resourceResolver != null && testService != null) {
            Resource pageRes = resourceResolver.getResource(title);
            if (pageRes != null) {
                Page selectedPage = pageRes.adaptTo(Page.class);
                if (selectedPage != null) {

                    pageTitles = testService.getTestPageTitles(selectedPage);
                }
            }
        }
    }

    public ArrayList<String> getPageTitles() {
        return pageTitles;
    }
}

