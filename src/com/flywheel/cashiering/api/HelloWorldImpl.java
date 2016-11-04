package com.flywheel.cashiering.api;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "com.flywheel.cashiering.api.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldAsString(String name) {
		return "Nomoshkar Prithibi JAX-WS " + name;
	}

}