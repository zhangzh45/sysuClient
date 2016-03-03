package action;

import service.GetService;
import service.UserService;
import util.test;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport{
	
	private String userid;
	private String passwd;
	private UserService userService = new UserService();
	//private GetService getService=new GetService();

	public String signin() {	
	// test t=new test();
	// t.postForm();
  	//String ss=gs.getAvailableServiceList(1);
		if( userid.trim().equals("admin") && passwd.equals("YAWL")){
			
			return "admin";
		}
		
		else if( userService.login(userid.trim(), passwd))	{
			//getService.getAvailableServiceList(Integer.parseInt(userid.trim()));
			return "user";
		}
		
		else return "error"; 

	}

	/*************************************************/

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
