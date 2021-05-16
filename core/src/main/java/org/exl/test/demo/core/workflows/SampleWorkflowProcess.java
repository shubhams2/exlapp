package org.exl.test.demo.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
		service=WorkflowProcess.class,
		property = {"process.label=Sample Application - Sample Workflow Process Step"}
		)
public class SampleWorkflowProcess implements WorkflowProcess {
	private static final Logger log = LoggerFactory.getLogger(SampleWorkflowProcess.class);

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) throws WorkflowException {

		final WorkflowData workflowData = workItem.getWorkflowData();
		final String type = workflowData.getPayloadType();
		Date date = null;

		// Check if the payload is a path in the JCR; The other (less common) type is JCR_UUID
		if (!StringUtils.equals(type, "JCR_PATH")) {
			return;
		}
		// Get the path to the JCR resource from the payload
		final String path = workflowData.getPayload().toString();

		log.info("----Sample Application - Sample Workflow Process Step - payload: {}", path);

		Workflow workflow = workItem.getWorkflow();

		// Getting the workflow history
		List<HistoryItem> workflowHistory = workflowSession.getHistory(workflow);
		if (!workflowHistory.isEmpty()) {
			// Get activationDate property set by user in dialog participant step - which is the second node in workflow history
			String strDate= (String)workflowHistory.get(0).getNextHistryItem().getWorkItem().getMetaDataMap().get("activationDate");


			if(strDate == null || strDate.isEmpty()){
				log.info("---- activationDate is either null or empty - "+ strDate);
			}else {
				log.info("---- activationDate - "+ strDate);

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
					date = sdf.parse(strDate);
				}catch(ParseException e) {
					log.info("---- Unable to parse activationDate in SampleWorkflowProcess - "+ e.getMessage());
				}

				if(date != null) {
					// Getting the metadata map
					MetaDataMap map = workItem.getWorkflow().getWorkflowData().getMetaDataMap();
					// Set workflow absoluteTime to activationDate by user
					log.info("---- Set workflow absoluteTime - "+ date);
					map.put("absoluteTime", date);
				}

			}
		} else {
			return;	
		}

	}
}
