package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AccordianMultiData {

    @ValueMapValue
    String question;

    @ValueMapValue
    String answer;


    @ValueMapValue
    String dhananjay;

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
