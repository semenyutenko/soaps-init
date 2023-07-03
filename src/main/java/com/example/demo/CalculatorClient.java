package com.example.demo;

import com.example.demo.schema.Add;
import com.example.demo.schema.AddResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CalculatorClient extends WebServiceGatewaySupport {

    public int calculateAdd(int a, int b) {
        Add request = new Add();
        request.setIntA(a);
        request.setIntB(b);
        return ((AddResponse) getWebServiceTemplate().marshalSendAndReceive(getDefaultUri(),
                request,
                new SoapActionCallback("http://tempuri.org/Add"))).getAddResult();
    }
}
