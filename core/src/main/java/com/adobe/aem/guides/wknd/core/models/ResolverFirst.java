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
public class ResolverFirst {

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String image;

    @SlingObject
    Resource resource;

    @SlingObject
    ResourceResolver resourceResolver;

    @ValueMapValue
    private String photo;

    String name;
    String path;
    ArrayList<String> imagelist;

    @PostConstruct
    public void init() {
        if (resource != null) {
            name = resource.getName();
            path = resource.getPath();
            Resource printResource = resourceResolver.resolve(photo);
            imagelist = new ArrayList<>();
            imagelist = getImagelist(printResource);

        }

    }

    private ArrayList<String> getImagelist(Resource print) {
        ArrayList<String> imageimagelist = new ArrayList<>();

        Iterator<Resource> itr = print.listChildren();

        while (itr.hasNext()) {
            Resource imagelistResource = itr.next();
            imagelist.add(imagelistResource.getPath());
        }
        return imagelist;
    }
            public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Resource getResource() {
        return resource;
    }

    public ResourceResolver getResourceResolver() {
        return resourceResolver;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<String> getImagelist() {
        return imagelist;
    }
}
