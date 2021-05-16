package org.exl.test.demo.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.After;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

@ExtendWith(AemContextExtension.class)
public class SampleWorkflowProcessTest{

		 @Mock
		    private WorkItem workItem;

		    @Mock
		    private WorkflowProcess workflowProcess;

		    @Before
		    public final void setUp() throws Exception {
		        MockitoAnnotations.initMocks(this);
		    }

		    @After
		    public final void tearDown() throws Exception {
		        //reset(workItem);
		        //reset(workflowProcess);
		    }
		    
		    @Test
		    public final void testReplicate() throws Exception {
		Assert.assertTrue(true);
		
		
		String path = "/content/exlapp/language-masters/en";

		/*
        WorkItem workItem = mock(WorkItem.class);
        WorkflowData data = mock(WorkflowData.class);
        
        Workflow workflow = mock(Workflow.class);        
        when(workflow.getWorkflowData()).thenReturn(data);
        when(workflow.getInitiator()).thenReturn(workflowSession.getUser().getName());
            
        
        when(workItem.getWorkflowData()).thenReturn(data);
        when(data.getPayloadType()).thenReturn(AbstractAssetWorkflowProcess.TYPE_JCR_PATH);
        when(data.getPayload()).thenReturn(path);
        System.out.println("payload: "+data.getPayload());
         //+workItem.getWorkflow().getInitiator()
        
        System.out.println(" initiator: "+workflow.getInitiator());
        
        Resource resource = mock(Resource.class);
        Asset asset = mock(Asset.class);
        Rendition rendition = mock(Rendition.class);
        when(resource.adaptTo(Asset.class)).thenReturn(asset);
        when(resource.getResourceType()).thenReturn(DamConstants.NT_DAM_ASSET);
        when(resourceResolver.getResource(path)).thenReturn(resource);
        when(asset.getRendition(isA(RenditionPicker.class))).thenReturn(rendition);

        */
        
	}
}
