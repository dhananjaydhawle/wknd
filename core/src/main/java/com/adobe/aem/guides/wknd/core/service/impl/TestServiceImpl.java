package com.adobe.aem.guides.wknd.core.service.impl;

import com.adobe.aem.guides.wknd.core.service.TestService;
import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Iterator;

@Component(service = TestService.class, immediate = true)
public class TestServiceImpl implements TestService {

    @Override
    public ArrayList<String> getTestPageTitles(Page page) {
        ArrayList<String> pageTitles = new ArrayList<>();


            Iterator<Page> itr = page.listChildren();
            while (itr.hasNext()) {
                Page childPage = itr.next();
                String title = childPage.getPageTitle() != null
                        ? childPage.getPageTitle()
                        : childPage.getTitle();

                if (title != null) {
                    pageTitles.add(title);
                }

            }
        return pageTitles;
    }
}
