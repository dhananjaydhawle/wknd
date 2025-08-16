package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MaxList {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String selectpage;

    @ValueMapValue
    private String selectimage;

    @SlingObject
    private ResourceResolver resourceResolver;

    private List<String> renditionsList = new ArrayList<>();

    @PostConstruct
    public void init() {
        getImagepagedeta();
        getImagerenditions();
    }

    private void getImagepagedeta() {
        if (selectpage != null) {
            Resource pageResource = resourceResolver.getResource(selectpage);
            if (pageResource != null) {
                String propertiesPath = pageResource.getPath() + "/jcr:content/root/container/image";
                Resource propertiesResource = resourceResolver.getResource(propertiesPath);

                if (propertiesResource != null) {
                    ValueMap vm = propertiesResource.adaptTo(ValueMap.class);
                    if (vm != null) {
                        String fileRef = vm.get("fileReference", String.class);
                        // Optional: store or log fileRef
                    }
                }
            }
        }
    }

    private void getImagerenditions() {
        if (selectimage != null) {
            Resource imageResource = resourceResolver.getResource(selectimage);
            if (imageResource != null) {
                Resource renditionsResource = imageResource.getChild("jcr:content/renditions");
                if (renditionsResource != null) {
                    Iterator<Resource> iterator = renditionsResource.listChildren();
                    while (iterator.hasNext()) {
                        Resource rendition = iterator.next();
                        renditionsList.add(rendition.getPath());
                    }
                }
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public List<String> getRenditionsList() {
        return renditionsList;
    }

    public String getSelectimage() {
        return selectimage;
    }

    public String getSelectpage() {
        return selectpage;
    }

}