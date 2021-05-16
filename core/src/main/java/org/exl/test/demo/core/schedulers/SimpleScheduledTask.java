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
package org.exl.test.demo.core.schedulers;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;
import javax.jcr.Session;

/**
 * A simple demo for cron-job like tasks that get executed regularly.
 * It also demonstrates how property values can be set. Users can
 * set the property values in /system/console/configMgr
 */
@Designate(ocd=SimpleScheduledTask.Config.class)
@Component(service=Runnable.class)
public class SimpleScheduledTask implements Runnable {
	
	@Reference
	 public ResourceResolverFactory resolverFactory;

    @ObjectClassDefinition(name="A scheduled task",
                           description = "Simple demo for cron-job like task with properties")
    public static @interface Config {

        @AttributeDefinition(name = "Cron-job expression")
        String scheduler_expression() default "*/30 * * * * ?";

        @AttributeDefinition(name = "Concurrent task",
                             description = "Whether or not to schedule this task concurrently")
        boolean scheduler_concurrent() default false;

        @AttributeDefinition(name = "A parameter",
                             description = "Can be configured in /system/console/configMgr")
        String myParameter() default "";
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String myParameter;
    Session session;
    ResourceResolver resolver = null; 
    
    @Override
    public void run() {
        logger.debug("SimpleScheduledTask is now running, myParameter='{}'", myParameter);
        try {
        	   final String payloadPath = "/content/we-retail/language-masters/en/equipment";

        	   Map<String, Object> param = new HashMap<String, Object>();
        	   param.put(ResourceResolverFactory.SUBSERVICE, "writeService");
        	            
        	                           
        	               //Invoke the getServiceResourceResolver method to create a Session instance
        	               resolver = resolverFactory.getServiceResourceResolver(param);
        	               session = resolver.adaptTo(Session.class);
        	              
        	           
        	   // Getting the resource resolver
        	   //final ResourceResolver resolver = request.getResourceResolver();

        	   // Get the workflow session from the resource resolver
        	   final WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);

        	   // Workflow model path - This is the already created workflow
        	   final String model = "/var/workflow/models/request_for_activation";

        	   // Get the workflow model object
        	   final WorkflowModel workflowModel = workflowSession.getModel(model);

        	   // Create a workflow Data (or Payload) object pointing to a resource via JCR
        	   // Path (alternatively, a JCR_UUID can be used)
        	   final WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payloadPath);

        	   // Optionally pass in workflow metadata via a Map
        	   final Map<String, Object> workflowMetadata = new HashMap<>();
        	   workflowMetadata.put("anyData", "You Want");
        	   workflowMetadata.put("evenThingsLike", new Date());
        	   
        	   logger.info("Workflow: {} data = " + workflowModel + " ---- " + workflowData + "----" + workflowMetadata);
        	   

        	   // Start the workflow!
        	   workflowSession.startWorkflow(workflowModel, workflowData, workflowMetadata);

        	   logger.info("Workflow: {} started", model);
        	   
        	   //response.getWriter().println("Workflow Exxecuted");

        	  } catch (WorkflowException | LoginException e) {
        		  logger.error(e.getMessage(), e);
        	  }
    }

    @Activate
    protected void activate(final Config config) {
        myParameter = config.myParameter();
    }

}
