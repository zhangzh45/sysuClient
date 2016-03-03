package action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class WebserviceAction extends ActionSupport{
	
	private String endpoint;
	private String namespace;
	private String operationName;
	private String soapActionURI;
	
	private Map<String,String> callMap;
	private String resultJson;

	public String call() {
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("result", "广东省广州市");
		
		resultJson = new JSONObject(m).toString();
		
		return SUCCESS;
	}
	
	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
}
