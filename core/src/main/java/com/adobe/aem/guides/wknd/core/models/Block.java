package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Block {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String selectImageFolder;

    @SlingObject
    Resource resource;

    @SlingObject
    ResourceResolver resourceResolver;

    ArrayList<String> imageList;

    @PostConstruct
    public void init() {
        if (resource != null) {
            Resource resourceimage = resourceResolver.getResource(selectImageFolder);
            imageList = new ArrayList<>();
            imageList = getImageList(resourceimage);
        }
    }

    private ArrayList<String> getImageList(Resource image) {
        ArrayList<String> imageList = new ArrayList<>();

        Iterator<Resource> itr = image.listChildren();

        while (itr.hasNext()) {
            Resource photoResource = itr.next();
            imageList.add(photoResource.getPath());
        }
        return imageList;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }
}
