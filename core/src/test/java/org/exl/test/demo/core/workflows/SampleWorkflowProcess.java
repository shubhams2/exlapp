package org.exl.test.demo.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleWorkflowProcess implements WorkflowProcess {
	private static final Logger log = LoggerFactory.getLogger(SampleWorkflowProcess.class);

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) throws WorkflowException {

		Assert.assertTrue(true);
		log.info("---- Testing process.label=Sample Application - Sample Workflow Process Step - ");
		
	}
}
