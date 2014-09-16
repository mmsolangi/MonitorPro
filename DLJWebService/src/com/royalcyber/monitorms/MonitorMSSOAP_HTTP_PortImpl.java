
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.royalcyber.monitorms;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jws.WebMethod;

import com.rc.json.dto.LogHeader;
import com.rc.json.dto.LogHeaderDetail;
import com.rc.service.ServiceManager;
import com.royalcyber.monitor.MonitorDebugDetailType;

/**
 * This class was generated by Apache CXF 3.0.1
 * 2014-09-09T22:05:42.946+10:00
 * Generated source version: 3.0.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "MonitorMSSOAP_HTTP_Service",
                      portName = "MonitorMSSOAP_HTTP_Port",
                      targetNamespace = "http://royalcyber.com/MonitorMS",
                      wsdlLocation = "file:/C:/Users/dvaddi/Workspace_DLUIMerge/DLJWebService/WebContent/WEB-INF/wsdl/MonitorMSSOAP_HTTP_Port.wsdl",
                      endpointInterface = "com.royalcyber.monitorms.MonitorMSPortType")
                      
public class MonitorMSSOAP_HTTP_PortImpl implements MonitorMSPortType {

    private static final Logger LOG = Logger.getLogger(MonitorMSSOAP_HTTP_PortImpl.class.getName());
    
    @WebMethod(operationName = "Loaddata", action ="Loaddata")
    public void loaddata(com.royalcyber.monitor.MonitorDebugPayloadType monitorDebugPayload) { 
        LOG.info("Executing operation loaddata");
        ServiceManager serviceManager = new ServiceManager();
        
        LogHeader aLogHeader = new LogHeader();
        LogHeaderDetail aLogHeaderDetail = null;
        ArrayList<LogHeaderDetail> logHeaderDetailList = new ArrayList<LogHeaderDetail>();
        
        try {
        	
			aLogHeader.setApplicationID(monitorDebugPayload.getCallingApplicationCode());
			aLogHeader.setClientReqDateTime(monitorDebugPayload.getClientRequestDateTime());
        	aLogHeader.setCorrelID(monitorDebugPayload.getCorrelationID());
        	aLogHeader.setOutcomeStatus(monitorDebugPayload.getOutcomeStatus());
        	aLogHeader.setParentCorrelID( monitorDebugPayload.getParentCorrelationID());
        	aLogHeader.setSlCode(monitorDebugPayload.getSLCode());
        	
        	aLogHeader.setLob(monitorDebugPayload.getLobCode());
        	//dummy data to avoid null pointer exception start
//        	aLogHeader.setOutcomeHdrID("1");
//        	aLogHeader.setClientUserID("1");
//        	aLogHeader.setTimeTaken("1");
//        	aLogHeader.setErrorCode("1");
//        	aLogHeader.setStoreName("1");
        	
        	//dummy data to avoid null pointer exception end
        	
			List<MonitorDebugDetailType> aMonitorDebugDetailList= monitorDebugPayload.getMonitorDebugDetail();
			
			String inputPayloadString ="";
			String outputPayloadString ="";
			
			for (MonitorDebugDetailType detailObjects : aMonitorDebugDetailList) {
				
				aLogHeaderDetail = new LogHeaderDetail();
				aLogHeaderDetail.setExecutionSeq(new Long(detailObjects.getExecutionSeq()).longValue());
				  LOG.info("Executing detailObjects.getInputPayload BEGIN : ");
				
				if(detailObjects.getInputPayload()!=null){
					inputPayloadString=detailObjects.getInputPayload();
				}
//				 LOG.info("Executing detailObjects.getInputPayload BEGIN : inputPayloadString :"+inputPayloadString);
				if(detailObjects.getOutputPayload()!=null){
					//outputPayloadString=serializeSoapObject((JAXBElement)detailObjects.getOutputPayload());
					outputPayloadString=detailObjects.getOutputPayload();
				}
//				LOG.info("Executing detailObjects.getInputPayload BEGIN : inputPayloadString :"+outputPayloadString);
				 LOG.info("Executing detailObjects.getInputPayload END");
				aLogHeaderDetail.setInputPayload(inputPayloadString);
				aLogHeaderDetail.setOutputPayload(outputPayloadString);
				aLogHeaderDetail.setSlStepName(detailObjects.getSLStepName());
				aLogHeaderDetail.setErrorType(detailObjects.getErrorType());
				aLogHeaderDetail.setSourceSystemErrorDesc(detailObjects.getErroredApplicationCode());
				
				
				//added dummy data to avoid null pointer exception start
//				aLogHeaderDetail.setOutcomeDTLID("1");
//				aLogHeaderDetail.setSourceSystemErrorCode("1");
//				aLogHeaderDetail.setLogLevel("1");
//				aLogHeaderDetail.setDebugEntry("1");
//				aLogHeaderDetail.setApplicationCode("1");
//				aLogHeaderDetail.setServer("1");
//				aLogHeaderDetail.setEntryTimeatstep(new Timestamp(4));
//				aLogHeaderDetail.setExitTimeatstep(new Timestamp(3));
//				aLogHeaderDetail.setDbupdCreatedttm(new Timestamp(3));
//				byte[] bytearray = new byte[2];
//				aLogHeaderDetail.setInputPayloaEenc(bytearray);
//				aLogHeaderDetail.setOutputPayloadEnc(bytearray);
//				aLogHeaderDetail.setDbupdCreateusr("1");
				//added dummy data to avoid null pointer exception end
				
				
				logHeaderDetailList.add(aLogHeaderDetail);
			}
				
				aLogHeader.setLogHeaderDetail(logHeaderDetailList);
				
				serviceManager.loaddata(aLogHeader);
				
//				System.out.println(aLogHeader);
				
			
		} catch (Exception e) {
			LOG.info("Exceptipn in Impl  ::::: Reason ::::::"+e.getMessage());
			e.printStackTrace();
			e.getCause();
			e.getStackTrace();
		}
    }
    private String serializeSoapObject(Object payload) throws Exception{
    	/*
    	OutputFormat format    = new OutputFormat ((com.sun.org.apache.xerces.internal.dom.ElementNSImpl )payload.getAsDocument()); 
        // as a String
        StringWriter stringOut = new StringWriter ();    
        XMLSerializer serial   = new XMLSerializer (stringOut, 
                                                    format);
        serial.serialize(payload);
        // Display the XML       
        String  xmlString= stringOut.toString();
        */
    	//String o =(String )payload;
    	//String xmlString = payload.getTextContent();
    	//String xmlString = (String)payload.getValue();
    	//System.out.println("serializeSoapObject:textcontent "+xmlString);
        //return xmlString;
    	com.sun.org.apache.xerces.internal.dom.ElementNSImpl ele =(com.sun.org.apache.xerces.internal.dom.ElementNSImpl)payload;
    	String  xmlString=ele.getNodeValue();
    	

    	return xmlString;
    }

}