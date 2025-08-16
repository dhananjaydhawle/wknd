package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyLaptop {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy= InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @ValueMapValue
    private String laptopram;

    @ValueMapValue
    private String maxItems;

    @ValueMapValue
    private String listFrom;

    @ValueMapValue
    private String linkItems;

    @ValueMapValue(name = "jcr:createdBy")
    private String createdBy;

    @ValueMapValue(name= "jcr:lastModifiedBy")
    private String lastmodified;




    public String getListFrom() {
        return listFrom;
    }

    public String getLinkItems() {
        return linkItems;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getLaptopram() {
        return laptopram;
    }

    public String getMaxItems() {
        return maxItems;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastmodified() {
        return lastmodified;
    }
}
