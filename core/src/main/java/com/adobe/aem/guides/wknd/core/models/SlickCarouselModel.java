package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SlickCarouselModel {

    @ChildResource
    List<SlickItem> CarouselItems;
    //***************
    @ValueMapValue
    private String imageDamPath;

    @SlingObject
    ResourceResolver resourceResolver;

     //image path & image name
    private Map<String, String> imageListMap;


    @PostConstruct
    public void init() {
        if (resourceResolver != null) {
            Resource resourceImage = resourceResolver.getResource(imageDamPath);
            imageListMap = new HashMap<>();
            imageListMap = getImageList(resourceImage);


            //check resource not null
            //iterate child
            //read path & name and add in map

        }
    }
    private Map<String, String> getImageList (Resource image){
        Map<String, String> map = new HashMap<>();
        Iterator<Resource> itr =  image.listChildren();
        while (itr.hasNext()) {
            Resource childResource = itr.next();
             //ValueMapValue vm = childResource.adaptTo(ValueMapValue.class);
             map.put(childResource.getName(), childResource.getPath());
        }
        return map;
    }



    public List<SlickItem> getCarouselItems() {
        return CarouselItems;
    }

    public Map<String, String> getImageListMap() {
        return imageListMap;
    }




}
