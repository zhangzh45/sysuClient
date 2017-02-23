package server;

import java.util.HashMap;



import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import service.UserService;


public class GetInfo {
	private  static UserService userservice;
	
	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public static String getAllRole(String user, String password) {
		return userservice.getRole(user, password);
	}
}
