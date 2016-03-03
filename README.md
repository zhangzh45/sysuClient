sysuClient
==========
resourceService jar change log:
2013-07-13: Fix the bug within workqueue gateway client adapter, which miss the service password
1.--org.yawlfoundation.yawl.resourcing.rsInterface.WorkQueueGatewayClientAdapter#addRegisteredService:
		line 557: add an argument "String service.get_servicePassword()" to the method of WorkQueueGatewayClient.
	--org.yawlfoundation.yawl.resourcing.rsInterface.WorkQueueGatewayClient#addRegisteredService:
		line 600: change the method argument list, add "String pwd" as the third argument.
		line 605: add a parameter to the request named "pwd", valued pwd.
	--org.yawlfoundation.yawl.resourcing.rsInterface.WorkQueueGateway#doAction:
		line 413: get a parameter from the request named "pwd".
		line 418: set pwd as the service password of YAWLServiceReference ysr.
		
resourceService jar change log:
2013-07-24: Fix the bug within workqueue gateway client, which miss match the parameter name
1.--org.yawlfoundation.yawl.resourcing.rsInterface.WorkQueueGatewayClient#uploadSpecification
		line 516: change the parameter name to "fileContents".
		line 517: change the parameter name to "fileName".