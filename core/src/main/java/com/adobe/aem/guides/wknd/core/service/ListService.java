package com.adobe.aem.guides.wknd.core.service;

import com.day.cq.wcm.api.Page;

import java.util.Map;

public interface ListService {

    Map<String, String> getPageMap(Page currentPage);

    Map<String, String> getTypeMap(String path);



    //use template or path


}
