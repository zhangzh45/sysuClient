package server;

@javax.jws.WebService(targetNamespace = "http://server/", serviceName = "ServerInterfaceService", portName = "ServerInterfacePort", wsdlLocation = "WEB-INF/wsdl/ServerInterfaceService.wsdl")
public class ServerInterfaceDelegate {

	server.ServerInterface serverInterface = new server.ServerInterface();

	public String getAllRole(String user, String password) {
		return serverInterface.getAllRole(user, password);
	}

}