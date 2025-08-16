package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.*;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListServiceModel {

    @Inject
    private ResourceResolverFactory resourceResolverFactory;

    @SlingObject
    private Resource currentResource;

    @ValueMapValue
    private String selectpage;
    //*******************************
    @ValueMapValue
    private String selectimage;

    @PostConstruct
    protected void init() throws LoginException, PersistenceException {

        ResourceResolver resolver = getMyResourceResolver();

        updateResourcePropertiesNew(resolver);
        updateResourceProperties(resolver);
        //**************************
    }


    private ResourceResolver getMyResourceResolver() throws LoginException {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "wkndServiceUser");
        ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(param);
        return resolver;
    }

    public void updateResourcePropertiesNew(ResourceResolver newResolver) throws PersistenceException {
        if(selectimage != null && newResolver != null) {

            Resource resourceNew = newResolver.getResource(selectimage);

            if(resourceNew != null) {
                Iterator<Resource> itr = resourceNew.listChildren();
                while (itr.hasNext()){
                    Resource childResouce = itr.next();
                    Resource childCJRResource = newResolver.getResource(childResouce.getPath());//check path if path null check why
                    if(childCJRResource != null) {
                        ModifiableValueMap mf = childCJRResource.adaptTo(ModifiableValueMap.class);
                        mf.put("mentor", "akash-kaka");
                    }
                }
                newResolver.commit();
            }

        }
    }

    public void updateResourceProperties(ResourceResolver resolver) throws PersistenceException {
        if(selectpage != null && resolver != null)
        {
            Resource resource =resolver.getResource(selectpage);
            if(resource != null)
            {
                Iterator<Resource> itr = resource.listChildren();
                while(itr.hasNext())
                {
                    Resource childResource = itr.next();
                    Resource childJCRResource = resolver.getResource(childResource.getPath() + "/jcr:content");
                    if(childJCRResource != null)
                    {
                        ModifiableValueMap mf = childJCRResource.adaptTo(ModifiableValueMap.class);
                        mf.put("myprop", "test");
                    }

                }
                resolver.commit();
            }
        }


    }
}
