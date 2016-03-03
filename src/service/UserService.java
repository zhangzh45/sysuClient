package service;

import java.io.IOException;
import java.util.Map;

import org.yawlfoundation.yawl.resourcing.rsInterface.WorkQueueGatewayClientAdapter;
import com.opensymphony.xwork2.ActionContext;

public class UserService{
	String _handle;
	String _userName = "admin";
	String _password = "YAWL";
	String _defURI = "http://localhost:8080/resourceService/workqueuegateway";
	
	WorkQueueGatewayClientAdapter wqAdapter = new WorkQueueGatewayClientAdapter(_defURI);
	Map<String, Object> session = ActionContext.getContext().getSession();

    private boolean connected() {
        if (!wqAdapter.checkConnection(_handle)) {
            _handle = wqAdapter.connect(_userName, _password) ;

            return wqAdapter.successful(_handle) ;
        }
        else return true ;
    }
	
	public boolean login(String userid, String password) {
		String userhandle = wqAdapter.userlogin(userid, password);
		
		try {
			if(this.connected() && wqAdapter.isValidUserSession(userhandle)) {
				session.put("userhandle", userhandle);
				session.put("userid", userid);

				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;

	}

	public void logout() {
		String userhandle = (String) session.get("userhandle");
		
		wqAdapter.userlogout(userhandle);
		
		session.remove("userhandle");
		session.remove("userid");
	}

}