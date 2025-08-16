package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = Resource.class,  defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyComponent {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy= InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @ValueMapValue
    private String firstName;

    @PostConstruct
    public void init(){

        String a = "My first component";

    }

    public String getFirstName() {
        return firstName;
    }

    public String getResourceType() {
        return resourceType;
    }
}
