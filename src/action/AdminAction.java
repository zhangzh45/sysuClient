package action;

import java.util.Map;
import java.util.Set;

import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.resourcing.QueueSet;
import org.yawlfoundation.yawl.resourcing.WorkQueue;

import service.WorkQueueService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminAction extends ActionSupport {
	
	private Set<WorkItemRecord> items;
	private WorkQueueService workQueueService = new WorkQueueService();
	
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	public String unoffered() {		
		QueueSet queueSet = workQueueService.getAdminQueue();
		items = queueSet.getQueuedWorkItems(WorkQueue.UNOFFERED);
		return "success";
		
	}
	
	public String worklisted() {		
		items = workQueueService.getWorkQueue((String) session.get("userid"), "worklisted");
		return "success";
		
	}
	

	/************************************************/
	public Set<WorkItemRecord> getItems() {
		return items;
	}

	public void setItems(Set<WorkItemRecord> items) {
		this.items = items;
	}
}