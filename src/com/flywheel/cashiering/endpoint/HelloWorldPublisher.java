package com.flywheel.cashiering.endpoint;

import javax.xml.ws.Endpoint;
import com.flywheel.cashiering.api.HelloWorldImpl;

//Endpoint publisher
public class HelloWorldPublisher{

	public static void main(String[] args) {
	   Endpoint.publish("http://0.0.0.0:2020/ws/hello", new HelloWorldImpl());
    }

}