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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Dhananjay {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String selectpage;

    @ValueMapValue
    private String selectimage;

    @SlingObject
    Resource resource;

    @SlingObject
    ResourceResolver resourceResolver;

    ArrayList<String> pageList;
    ArrayList<String> imageList;

    @PostConstruct
    public void init() {
        pageList = new ArrayList<>();
        imageList = new ArrayList<>();

        /*if (selectpage != null) {
            Resource resourcePage = resourceResolver.getResource(selectpage);
            if (resourcePage != null) {
                pageList = getPageList(resourcePage);
            }
        }

        if (selectimage != null) {
            Resource resourceImage = resourceResolver.getResource(selectimage);
            if (resourceImage != null) {
                imageList = getImageList(resourceImage);
            }
        }*/
        getPagedata();
    }


    private void getImageMetdata(){

        Resource imgResource = resourceResolver.getResource(selectimage);
         String imgResourcePath = imgResource.getPath();
        String metadataPath = imgResourcePath + "/jcr:content/metadata";

        Resource metadataResource = resourceResolver.getResource(metadataPath);
        ValueMap vm = metadataResource.adaptTo(ValueMap.class);

        String damFormat = vm.get("dam:Fileformat", String.class);
        String mimeType = vm.get("dam:MIMEtype", String.class);

        Long noImages = vm.get("dam:Numberofimages", Long.class);
        int noImagesInt = noImages != null ? noImages.intValue() : 0;     //ternary operator

//get page jcr properties
    }
     private void getPagedata() {
         Resource pageResource = resourceResolver.getResource(selectpage);
         String pageResourcePath = pageResource.getPath();
         String propertiesPath = pageResourcePath + "/jcr:content";

         Resource propertiesResource = resourceResolver.getResource(propertiesPath);
         ValueMap vm = propertiesResource.adaptTo(ValueMap.class);

         String lastModi = vm.get("cq:lastModifiedBy", String.class);
         String template = vm.get("cq:template", String.class);


         Calendar lastMod = vm.get("cq:lastModified", Calendar.class);
         String lastModifiedDate;
         if (lastMod != null) {
             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // 👈 Your required format
             lastModifiedDate = sdf.format(lastMod.getTime());
         }

     }

    private ArrayList<String> getPageList(Resource pageResource) {
        ArrayList<String> pageList = new ArrayList<>();
        Iterator<Resource> itr = pageResource.listChildren();

        while (itr.hasNext()) {
            Resource child = itr.next();
            ValueMap vm = child.adaptTo(ValueMap.class);

            if (vm != null) {
                String pageTitle = vm.get("jcr:title", String.class);
                pageList.add((pageTitle != null ? pageTitle : "Untitled") + " - " + child.getPath());
            }
        }
        return pageList;
    }

    private ArrayList<String> getImageList(Resource imageResource) {
        ArrayList<String> imageList = new ArrayList<>();
        Iterator<Resource> itr = imageResource.listChildren();

        while (itr.hasNext()) {
            Resource child = itr.next();
            ValueMap vm = child.adaptTo(ValueMap.class);

            if (vm != null) {
                String imageTitle = vm.get("jcr:title", String.class);
                imageList.add((imageTitle != null ? imageTitle : "No Title") + " - " + child.getPath());

            }
        }
        return imageList;


    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getPageList() {
        return pageList;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }
}
