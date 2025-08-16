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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DamFolder {
    @ValueMapValue
    private String damimage;

    @SlingObject
    ResourceResolver resourceResolver;

    ArrayList<ImageList> damImageList;

    @PostConstruct
    public void init() {
        if (resourceResolver != null) {
            Resource damResource = resourceResolver.getResource(damimage);

            //check resource not null
            Iterator<Resource> itr = damResource.listChildren();

            //initialoze list
            damImageList = new ArrayList<>();

            while (itr.hasNext()) {
                Resource childDResource = itr.next();
                Resource damChildImageResource = resourceResolver.getResource(childDResource.getPath());
                Resource damJcrResource = resourceResolver.getResource(childDResource.getPath() + "/jcr:content");

                if(damChildImageResource  != null  && !damChildImageResource .getPath().contains("jcr:content") ) {
                    ValueMap vm = damChildImageResource.adaptTo(ValueMap.class);
                    String name = damChildImageResource.getName();
                    String path = damChildImageResource.getPath();

                    String crete = null;
                    String asset = null;
                    String lastmodi = null;

                    if (damJcrResource != null) {
                        ValueMap damNewJcrResource = damJcrResource.adaptTo(ValueMap.class);
                        asset = damNewJcrResource.get("dam:assetState", String.class);
                        lastmodi = damNewJcrResource.get("jcr:lastModifiedBy", String.class);
                        Calendar createdDate = damNewJcrResource.get("jcr:created", Calendar.class);
                        if (createdDate != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            crete = sdf.format(createdDate.getTime());
                        }
                    }
                    ImageList imageList = new ImageList(name, path, asset, lastmodi, crete);
                    damImageList.add(imageList);
                }
            }
        }
    }

    public ArrayList<ImageList> getDamImageList() {
        return damImageList;
    }

     public class ImageList {
        String name;
        String path;
        String asset;
        String lastmodi;
        String crete;

        public ImageList(String name, String path, String asset, String lastmodi, String crete) {
            this.name = name;
            this.path = path;
            this.asset = asset;
            this.lastmodi = lastmodi;
            this.crete = crete;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }

        public String getAsset() {
            return asset;
        }

        public String getLastmodi() {
            return lastmodi;
        }

        public String getCrete() {
            return crete;
        }
    }
}
