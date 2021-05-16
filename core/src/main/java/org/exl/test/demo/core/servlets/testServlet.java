/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.exl.test.demo.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.jcr.Session;
import javax.jcr.Node; 
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.NonExistingResource;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
/**@Component(service = { Servlet.class },
property= ("sling.servlet.paths="+"/exlapp/test/page"),
configurationPid = "org.exl.test.demo.core.servlets.testServlet"
		)
*/

@Component(service=Servlet.class,

property={
		
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.resourceTypes=weretail/components/structure/page",
        "sling.servlet.extensions=html",
        "sling.servlet.selectors=sample",
        //"sling.servlet.paths="+ "/bin/myDataSourcePoolServlet" <working>

   })
	
/**@SlingServlet(paths="/bin/exlapp/test/page1", methods = "GET", metatype=true)
 * till AEM 6.2
@SlingServlet(
  resourceTypes="my-aem-project/components/page/page", 
  selectors="training",
  paths="/bin/trainingservlet",
  extensions="html",
  methods="GET",
  label="Training Servlet Code Snippet",
  description="Training Servlet Code Snippet for Medium Blog"
)
*/

/**@SlingServletResourceTypes(
        resourceTypes="exlapp/components/page",
        methods=HttpConstants.METHOD_GET,
        extensions="txt")*/

@ServiceDescription("test 2 Servlet")


public class testServlet extends SlingSafeMethodsServlet {
	
	Logger logger = LoggerFactory.getLogger(testServlet.class);

    private static final long serialVersionUID = 1L;

    @Reference
    ResourceResolverFactory resourceResolverFactory ;
    
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        resp.setContentType("text/plain");
       // resp.getWriter().write("Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
        resp.getWriter().write("Path = " + resource.getPath());
        
        PrintWriter out = null;
		ResourceResolver resolver = req.getResourceResolver();
		Session session = resolver.adaptTo(Session.class);
		try {
			out = resp.getWriter();
			resp.setContentType("text/html");
			String path = req.getParameter("path");
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>> path = " + path);
			Resource res = resolver.getResource(path);
			if (StringUtils.isBlank(path) || null == res || res instanceof NonExistingResource) {
				out.write("<b style=\"color:red;\">" + "Please provide valid path against request param 'path'" + "</b>");
				return;
			}
			Page myPage = res.adaptTo(Page.class);
			out.write("Resource Exist --> "+(session.nodeExists(myPage.getPath()))+"<br>");
			out.write("Page Path "+myPage.getPath() +" -- Title : "+myPage.getTitle() + "<br>");
			
			Resource jcrContPageRes = myPage.getContentResource();
			ModifiableValueMap myMap = jcrContPageRes.adaptTo(ModifiableValueMap.class);
			out.write("Page properties "+ myMap.get("jcr:title") + "<br>");
			out.write("Page properties "+ myMap.get("cq:conf") + "<br>");
			
			myMap.put("jcr:title", "New Title");
			
			resolver.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
		}
    }
}
