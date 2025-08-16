package com.adobe.aem.guides.wknd.core;

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

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Else {


        @Inject
        private ResourceResolverFactory resourceResolverFactory;

        @SlingObject
        private Resource currentResource;

        @ValueMapValue
        private String selectpage;

        @ValueMapValue
        private String selectimage;

        @PostConstruct
        protected void init() throws LoginException, PersistenceException {

                ResourceResolver resolver = getMyResourceResolver();
                updateResourcePropertiesNew(resolver);
                updateResourceProperties(resolver);
        }

        private ResourceResolver getMyResourceResolver() throws LoginException {
            Map<String, Object> param = new HashMap<>();
            param.put(ResourceResolverFactory.SUBSERVICE, "wkndServiceUser");
            return resourceResolverFactory.getServiceResourceResolver(param);
        }

        // Method with if-else condition********************
        public void updateResourcePropertiesNew(ResourceResolver newResolver) throws PersistenceException {
            if (selectimage != null && newResolver != null) {
                Resource resourceNew = newResolver.getResource(selectimage);

                if (resourceNew != null) {
                    Iterator<Resource> itr = resourceNew.listChildren();
                    while (itr.hasNext()) {
                        Resource childResource = itr.next();
                        Resource childJCRResource = newResolver.getResource(childResource.getPath());

                        if (childJCRResource != null) {
                            ModifiableValueMap mf = childJCRResource.adaptTo(ModifiableValueMap.class);
                            if (mf != null) {
                                mf.put("mentor", "akash-kaka");

                                //  if-else condition based on name*********** b
                                if (childJCRResource.getName().contains("banner")) {
                                    mf.put("test", "pass");
                                } else {
                                    mf.put("test", "fail");
                                }
                            }
                        }
                    }
                    newResolver.commit();
                }
            }
        }

        // ✅ Existing logic for page resource update
        public void updateResourceProperties(ResourceResolver resolver) throws PersistenceException {
            if (selectpage != null && resolver != null) {
                Resource resource = resolver.getResource(selectpage);
                if (resource != null) {
                    Iterator<Resource> itr = resource.listChildren();
                    while (itr.hasNext()) {
                        Resource childResource = itr.next();
                        Resource childJCRResource = resolver.getResource(childResource.getPath() + "/jcr:content");
                        if (childJCRResource != null) {
                            ModifiableValueMap mf = childJCRResource.adaptTo(ModifiableValueMap.class);
                            if (mf != null) {
                                mf.put("myprop", "test");
                            }
                        }
                    }
                    resolver.commit();
                }
            }
        }
    }












