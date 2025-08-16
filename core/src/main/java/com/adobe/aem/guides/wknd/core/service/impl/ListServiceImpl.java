package com.adobe.aem.guides.wknd.core.service.impl;

import com.adobe.aem.guides.wknd.core.service.ListService;
import com.adobe.aem.guides.wknd.core.service.MyService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = ListService.class, immediate = true)
public  class ListServiceImpl implements ListService  {

    @Override
    public Map<String, String> getPageMap(Page currentPage) {
        Map<String, String> pageMap = new HashMap<>();

        if (currentPage != null) {
            Iterator<Page> listChildren = currentPage.listChildren();
            while (listChildren.hasNext() ) {
                Page newPage = listChildren.next();
                String title = newPage.getTitle();
                String path = newPage.getPath() +   ".html";
                pageMap.put(title, path);

            }
        }
        return pageMap;

    }

    @Override
    public Map<String, String> getTypeMap(  String  pagepath) {
        Map<String, String> result = new HashMap<>();
        ResourceResolver resourceResolver = null;

            Resource resource = resourceResolver.getResource(pagepath);
            if (resource != null) {
                Iterator<Resource> children = resource.listChildren();
                while (children.hasNext()) {
                    Resource child = children.next();
                    ValueMap vm = child.adaptTo(ValueMap.class);
                    if (vm != null) {
                        String primaryType = vm.get("jcr:primaryType", String.class);
                        if ("cq:Page".equals(primaryType)) {
                            result.put(child.getPath(), primaryType);
                        }
                    }
                }
            }

        return result;
    }





    /// /click / more option / chose 2nd option

}
