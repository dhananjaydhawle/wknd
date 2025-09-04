package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselModel {

    @ValueMapValue
    String heading;

    @ValueMapValue
    String prev;

    @ValueMapValue
    String next;

    @ChildResource
    List<MultiCarousel> pagemultiList;

    public String getHeading() {
        return heading;
    }

    public String getPrev() {
        return prev;
    }

    public String getNext() {
        return next;
    }

    public List<MultiCarousel> getPagemultiList() {
        return pagemultiList;
    }
}
