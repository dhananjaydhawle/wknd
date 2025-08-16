package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.xfa.ut.StringUtils;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/getimagechild")

public class ImageChild extends SlingSafeMethodsServlet {

    private static final Gson gson = new Gson();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String childImagepath = request.getParameter("imagePath");

        if(StringUtils.isEmpty(childImagepath)) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"image path parameter is required");
            return;
        }
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource listImageResource = resourceResolver.getResource(childImagepath);

        Iterator<Resource> itr = listImageResource.listChildren();
        ArrayList<ImageJson> resultImageList = new ArrayList<>();


        while (itr.hasNext()) {
            Resource childResource = itr.next();
            Resource childResourceImage = resourceResolver.getResource(childResource.getPath());
           /* Resource imageJcrResource = resourceResolver.getResource(childResource.getPath() +"/jcr:content");*/
           Resource remdResource = resourceResolver.getResource(childResource.getPath() +"/jcr:content/renditions");

            if(childResourceImage != null /*&& !childResourceImage.getPath().contains("jcr:content")*/ && !childResourceImage.getPath().contains("jcr:content,renditions") ) {
                ValueMap vm = childResourceImage.adaptTo(ValueMap.class);
                String name = childResourceImage.getName();
                String path = childResourceImage.getPath();
                String asset = null;
                String lastmodi = null;
                String crete  = null;



               /* if(imageJcrResource != null) {
                    ValueMap jcrNewResource = imageJcrResource.adaptTo(ValueMap.class);
                    asset =  jcrNewResource.get("dam:assetState", String.class);
                    lastmodi = jcrNewResource.get("jcr:lastModifiedBy", String.class);


                }*/
                if( remdResource != null){
                    ValueMap remdiNewResource = remdResource.adaptTo(ValueMap.class);
                    Calendar createdDate = remdiNewResource.get("jcr:created", Calendar.class);
                    if (createdDate != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        crete = sdf.format(createdDate.getTime());
                    }
                }

               ImageJson imageJson = new ImageJson(name,path,asset,lastmodi,crete);
                resultImageList.add(imageJson);
            }

        }
        response.getWriter().write(gson.toJson(resultImageList));

    }
    class ImageJson{
        String name;
        String path;
        String asset;
        String lastmodi;
        String crete;




        public ImageJson(String name, String path, String asset, String lastmodi, String crete) {
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
