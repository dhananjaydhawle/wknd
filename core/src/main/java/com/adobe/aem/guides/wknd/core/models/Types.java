package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.service.ListService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.mime.MimeTypeService;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.naming.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//*************************************properties read using resource resolver add hash map , use read for value map*********************************
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Types {

    @ValueMapValue
    private String type;

    @SlingObject
    ResourceResolver resourceResolver;

    @OSGiService
    private ListService listService;


    private Map<String, String> typeMap;

    @PostConstruct
    public void init() {

        // Resource resourcePage = resourceResolver.getResource(type);
        typeMap = new HashMap<>();

        typeMap = listService.getTypeMap(type);

    }

    public Map<String, String> getTypeMap() {
        return typeMap;
    }
}/* private Map<String, String>  gettypeMap (Resource page){
        Map<String, String> map = new HashMap<>();


        Iterator<Resource> itr = page.listChildren();
        while (itr.hasNext()){
            Resource newResource = itr.next();
           // String  path = newResource.getPath();

            ValueMap vm = newResource.adaptTo(ValueMap.class);
            String primaryType = vm.get("jcr:primaryType", String.class);
            if ("cq:Page".equals(primaryType)) {      // if condition use

                map.put(newResource.getPath(), primaryType);
            }
        }
        return map;


    }

    public Map<String, String> getTypeMap() {
        return typeMap;
    }
}*/

