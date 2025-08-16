package com.adobe.aem.guides.wknd.core.service;

import com.day.cq.wcm.api.Page;

import java.util.ArrayList;
import java.util.Map;

public interface PageService {

    ArrayList<String> getchildPageName(Page page);

    //using hashmap
    Map<String, String> getChildPageMap(Page currentPage);


}
