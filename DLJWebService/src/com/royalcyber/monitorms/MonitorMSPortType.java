package com.royalcyber.monitorms;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.1
 * 2014-09-09T22:57:43.120+10:00
 * Generated source version: 3.0.1
 * 
 */
@WebService(targetNamespace = "http://royalcyber.com/MonitorMS", name = "MonitorMSPortType")
@XmlSeeAlso({com.royalcyber.monitor.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MonitorMSPortType {

    @WebMethod(operationName = "Loaddata", action = "Loaddata")
    @Oneway
    public void loaddata(
        @WebParam(partName = "MonitorDebugPayload", name = "MonitorDebugPayload", targetNamespace = "http://www.royalcyber.com/monitor")
        com.royalcyber.monitor.MonitorDebugPayloadType monitorDebugPayload
    );
}
