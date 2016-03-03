package action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.SpecificationData;

import service.SpecService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SpecAction extends ActionSupport{
	
	private String specsJson;
	private String selectedSpec;
	private File ywl;
	private String ywlFileName;
	
	private String identifier;
	private String version;
	private String uri;
	
	private SpecService specServ = new SpecService();
	
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	public String execute(){ return SUCCESS; }
	
/*	public String upload() {
		String result = specServ.upload(ywl, ywlFileName);
		if (result.startsWith("<failure>")) {
			System.out.println(result);
		}
		
		return SUCCESS;
	}*/
	
	public String allSpecs() {
		Set<SpecificationData> specs = specServ.getLoadedSpecList();
		
		JSONArray ja = new JSONArray();
				
		for(SpecificationData spec: specs) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("name", spec.getName());
			m.put("uri", spec.getSpecURI());
			m.put("version", spec.getSpecVersion());
			m.put("status", spec.getStatus());
			m.put("documentation", spec.getDocumentation());
			m.put("identifier", spec.getSpecIdentifier());
			
			ja.put(m);
		}

		specsJson = ja.toString();
		
		return SUCCESS;
	}
	
	public String allSpecsMonitor() {
		Set<SpecificationData> specs = specServ.getLoadedSpecList();
		
		JSONArray followed = new JSONArray();
		JSONArray unfollowed = new JSONArray();
		
		for(SpecificationData spec: specs) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("name", spec.getName());
			m.put("uri", spec.getSpecURI());
			m.put("version", spec.getSpecVersion());
			m.put("status", spec.getStatus());
			m.put("documentation", spec.getDocumentation());
			m.put("identifier", spec.getSpecIdentifier());
			
			if(specServ.isFollowed(spec.getSpecIdentifier(), (String) session.get("userid")) == true) {
				
				followed.put(m);
				
			} else { unfollowed.put(m); }
	
		}
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("followed", followed.toString());
		m.put("unfollowed", unfollowed.toString());
		
		specsJson = new JSONObject(m).toString();
		
		return SUCCESS;
	}
	
	public String follow() {
		
		specServ.addFollow(selectedSpec, (String) session.get("userid"));
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("identifier", selectedSpec);
		
		return SUCCESS;
	}
	
	public String unloadSpec() {
		YSpecificationID yid = new YSpecificationID(identifier, version, uri);
		String result = specServ.unload(yid);
		if (result.startsWith("<failure>")) {
			System.out.println(result);
		}
		
		Set<SpecificationData> specs = specServ.getLoadedSpecList();
		
		JSONArray ja = new JSONArray();
				
		for(SpecificationData spec: specs) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("name", spec.getName());
			m.put("uri", spec.getSpecURI());
			m.put("version", spec.getSpecVersion());
			m.put("status", spec.getStatus());
			m.put("documentation", spec.getDocumentation());
			m.put("identifier", spec.getSpecIdentifier());
			
			ja.put(m);
		}

		specsJson = ja.toString();
		
		return SUCCESS;
	}
	
	/***************************/

	public String getSpecsJson() {
		return specsJson;
	}

	public void setSpecsJson(String specsJson) {
		this.specsJson = specsJson;
	}

	public String getSelectedSpec() {
		return selectedSpec;
	}

	public void setSelectedSpec(String selectedSpec) {
		this.selectedSpec = selectedSpec;
	}

	public File getYwl() {
		return ywl;
	}

	public void setYwl(File ywl) {
		this.ywl = ywl;
	}

	public String getYwlFileName() {
		return ywlFileName;
	}

	public void setYwlFileName(String ywlFileName) {
		this.ywlFileName = ywlFileName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}


}