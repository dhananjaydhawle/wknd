package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FiveMultiFieldData {

    @ValueMapValue
    String title;

    @ValueMapValue
    String path;

    @ValueMapValue
    String multilist;

    @ValueMapValue
    String multiForm;

    @ValueMapValue
    String linkItems;

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public String getMultilist() {
        return multilist;
    }

    public String getMultiForm() {
        return multiForm;
    }

    public String getLinkItems() {
        return linkItems;
    }
}
