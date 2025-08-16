package com.adobe.aem.guides.wknd.core.service;

import com.day.cq.wcm.api.Page;

import java.util.ArrayList;

public interface MyService {

    ArrayList<String> getChildPageTitles(Page page);    //method declaration
}
