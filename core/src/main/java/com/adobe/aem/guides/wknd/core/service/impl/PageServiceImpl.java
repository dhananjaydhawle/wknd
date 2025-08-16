package com.adobe.aem.guides.wknd.core.service.impl;

import com.adobe.aem.guides.wknd.core.service.PageService;
import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = PageService.class, immediate = true)
public class PageServiceImpl implements PageService {

    @Override
    public ArrayList<String> getchildPageName(Page page) {
        ArrayList<String> pageName = new ArrayList<>();


        Iterator<Page> itr = page.listChildren();
        while (itr.hasNext()){
            Page childPage = itr.next();
            String name = childPage.getName();
            pageName.add(name);
        }
        return pageName;


    }


    @Override
    public Map<String, String> getChildPageMap(Page currentPage ) {
        Map<String, String> childPageMap = new HashMap<>();

        if (currentPage != null) {
            Iterator<Page> children = currentPage.listChildren();
            while (children.hasNext()) {
                Page child = children.next();
                String title = child.getTitle();
                String name = child.getName();
                childPageMap.put(title, name);
            }
        }

        return childPageMap;
    }

}
