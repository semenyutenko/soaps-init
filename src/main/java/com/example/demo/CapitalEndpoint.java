package com.example.demo;

import com.example.demo.domain.CapitalRepository;
import demo.example.com.CapitalCityRequest;
import demo.example.com.CapitalCityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.PayloadEndpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.websocket.MessageHandler;
import javax.xml.transform.Source;

@Endpoint
public class CapitalEndpoint implements MessageHandler {
    private static final String NAMESPACE_URI = "http://com.example.demo";

    private CapitalRepository capitalRepository;

    @Autowired
    public CapitalEndpoint(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CapitalCityRequest")
    @ResponsePayload
    public CapitalCityResponse capitalCity(@RequestPayload CapitalCityRequest request) {
        CapitalCityResponse response = new CapitalCityResponse();
        String capital = capitalRepository.findCapital(request.getSCountryISOCode()).orElse("empty");
        response.setCapitalCityResult(capital);

        return response;
    }
}
