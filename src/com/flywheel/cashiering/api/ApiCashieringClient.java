package com.flywheel.cashiering.api;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.flywheel.cashiering.api.ApiCashiering;

public class ApiCashieringClient{

	public static void main(String[] args) throws Exception {

	URL url = new URL("http://localhost:8080/ApiCashiering/trips?wsdl");

        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://api.cashiering.flywheel.com/", "ApiCashieringImplService");

        Service service = Service.create(url, qname);
        ApiCashiering hello = service.getPort(ApiCashiering.class);
        System.out.println(hello.getFlywheelTrips("mkyong"));
    }
}
