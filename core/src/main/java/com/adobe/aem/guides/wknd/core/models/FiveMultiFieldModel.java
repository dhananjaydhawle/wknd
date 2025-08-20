package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class FiveMultiFieldModel {

    @ValueMapValue
    String heading;

    @ChildResource
    List<FiveMultiFieldData> fivepageList;

    public String getHeading() {
        return heading;
    }

    public List<FiveMultiFieldData> getFivepageList() {
        return fivepageList;
    }
}
