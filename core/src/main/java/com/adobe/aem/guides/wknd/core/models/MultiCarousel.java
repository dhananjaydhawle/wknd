package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiCarousel {

    @ValueMapValue
    String imagepath;

    @ValueMapValue
    String caption;

    @ValueMapValue
    String altimage;

    public String getImagepath() {
        return imagepath;
    }

    public String getCaption() {
        return caption;
    }

    public String getAltimage() {
        return altimage;
    }
}
