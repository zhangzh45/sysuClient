package server;

public class ServerInterface {
	
	server.GetInfo getInfo = new server.GetInfo();
	
	public String getAllRole(String user, String password) {
		return getInfo.getAllRole(user, password);
	}
}
