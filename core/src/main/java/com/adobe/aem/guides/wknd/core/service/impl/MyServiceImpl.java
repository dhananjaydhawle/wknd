package com.adobe.aem.guides.wknd.core.service.impl;

import com.adobe.aem.guides.wknd.core.service.MyService;
import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Iterator;

@Component(service = MyService.class, immediate = true)
public class MyServiceImpl implements MyService{

    @Override
    public ArrayList<String> getChildPageTitles(Page page) {
         ArrayList<String> pageTitles = new ArrayList<>();

        Iterator<Page> itr = page.listChildren();
        while (itr.hasNext()){
         Page childPage = itr.next();
         String title = childPage.getTitle();
         pageTitles.add(title);
        }
        return pageTitles;
    }
}
