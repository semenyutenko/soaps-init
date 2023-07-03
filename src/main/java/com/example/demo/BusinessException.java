package com.example.demo;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.RECEIVER)
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
