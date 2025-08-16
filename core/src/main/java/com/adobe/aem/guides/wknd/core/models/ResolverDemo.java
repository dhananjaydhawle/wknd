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

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResolverDemo {

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String image;

    @SlingObject
    Resource resource;

    @SlingObject
    ResourceResolver resourceResolver;

    @ValueMapValue
    private String print;


    String name;
    String path;

    ArrayList<String> damImageList;

    @PostConstruct
    public void init() {


        if (resource != null) {
            name = resource.getName();
            path = resource.getPath();

            Resource pageResource = resourceResolver.getResource("/content/wknd/language-masters/en/adventures");
            String resourceName = pageResource.getName();
            String resourcePath = pageResource.getPath();

            Resource imageResource = resourceResolver.getResource(image);
            String imageName = imageResource.getName();
            String imagePath = imageResource.getPath();


            String metadataPath = imagePath + "/jcr:content/metadata";
            Resource metadataResource = resourceResolver.getResource(metadataPath);
            String name = metadataResource.getName();
            String path = metadataResource.getPath();



            Resource country = resourceResolver.getResource(print);
            damImageList = new ArrayList<>();
            damImageList = getDAMFolderImages(country);
            //ValueMap vm =

        }
    }

    private ArrayList<String> getDAMFolderImages(Resource country) {
        ArrayList<String> imageList = new ArrayList<>();

        Iterator<Resource> itr = country.listChildren();

        while (itr.hasNext()) {
            Resource imageResource = itr.next();
            imageList.add(imageResource.getPath());
        }
        return imageList;
    }

    public ArrayList<String> getDamImageList() {
        return damImageList;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
