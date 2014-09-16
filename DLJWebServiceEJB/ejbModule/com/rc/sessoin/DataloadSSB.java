package com.rc.sessoin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;
import javax.xml.soap.SOAPElement;

import com.rc.jpa.Lob;
import com.rc.jpa.Outcomelogdetail;
import com.rc.jpa.Outcomelogheader;
import com.rc.json.dto.LogHeader;
import com.rc.json.dto.LogHeaderDetail;

/**
 * Session Bean implementation class DataloadSSB
 */
@Stateless
@LocalBean
public class DataloadSSB implements DataloadSSBRemote, DataloadSSBLocal {

	private static final Logger LOG = Logger.getLogger(DataloadSSB.class.getName());	
	
    /**
     * Default constructor. 
     */
    public DataloadSSB() {
    }
    @PersistenceContext (unitName="DLJWebServiceEJB")
    EntityManager em;
    
	public void loaddata(LogHeader logheader)  {

    	LOG.info("DataloadSSB : loaddata invoked"+logheader);
    	Outcomelogheader outcomelogheader= null;
    	
    	try{
    		System.out.println("DataloadSSB : loaddata parent correlation id : " + logheader.getParentCorrelID());
    		System.out.println("DataloadSSB : loaddata LOB : " + logheader.getLob());
    		System.out.println("DataloadSSB : loaddata outcomestatus : " + logheader.getOutcomeStatus());
    		System.out.println("DataloadSSB : loaddata SLCode : " + logheader.getSlCode());
    		System.out.println("DataloadSSB : loaddata ApplicationCode : " +logheader.getApplicationID());
    		if(logheader.getApplicationID()!= null && !logheader.getApplicationID().equalsIgnoreCase("")){
    			outcomelogheader=findByParentCorrelationID(logheader.getParentCorrelID(),logheader.getApplicationID(),logheader.getLob());
    		}else{
    			outcomelogheader=findByParentCorrelationID(logheader.getParentCorrelID(),"",logheader.getLob());
    		}
	    	
    		
    		
    		Timestamp clientRequestDatetime =null;
    		String outcomeStatus = "";
	    	if(outcomelogheader ==null){
		    	outcomelogheader= new Outcomelogheader();

		    	if(logheader.getParentCorrelID()!=null &&  !logheader.getParentCorrelID().equalsIgnoreCase("")){
		    		outcomelogheader.setParentcorrelationid(logheader.getParentCorrelID());
		    	}else{
		    		System.out.println("DataloadSSB : loaddata outcomelogheader ParentCorrelationID is NULL");
		    		outcomelogheader.setParentcorrelationid(String.valueOf(UUID.randomUUID()));
		    	}
		    	
	    	}
	    	System.out.println("DataloadSSB : loaddata outcomelogheader begin new");
	    	outcomelogheader.setApplicationcode(logheader.getApplicationID());
	    	//outcomelogheader.setClientrequestdatetime(logheader.getClientRequestDateTime());
	    	outcomelogheader.setClientuserid(logheader.getClientUserID());
	    	outcomelogheader.setCorrelationid(logheader.getCorrelID());
	    	//outcomelogheader.setDbupdcreatedttm(logheader.get);
	    	outcomelogheader.setErrorcode(logheader.getErrorCode());
	    	outcomelogheader.setErrortype("");
	    	outcomelogheader.setLobcode(logheader.getLob());    	
	    	outcomelogheader.setSlcode(logheader.getSlCode());
	    	String clientReqTime=logheader.getClientReqDateTime();
	    	outcomeStatus = logheader.getOutcomeStatus();
	    	
	    	outcomelogheader.setOutcomestatus(outcomeStatus);
	    	if(clientReqTime!=null){
	    		Calendar cal = DatatypeConverter.parseDateTime(clientReqTime);
	    		clientRequestDatetime=new Timestamp(cal.getTimeInMillis());
	    		outcomelogheader.setClientrequestdatetime(clientRequestDatetime);
	    		System.out.println("DataloadSSB : setClientrequestdatetime outcomelogheader  " + new Timestamp(cal.getTimeInMillis()));
    				    		
	    	}	    	
	    	
	    	ArrayList<LogHeaderDetail> aLogHeaderDetailList=logheader.getLogHeaderDetail();
	    	   	 
	    	List<Outcomelogdetail> outcomelogdetails = new ArrayList<Outcomelogdetail>();;
	    	LogHeaderDetail aLogHeaderDetail=null;
	    	Outcomelogdetail outcomelogdetail= null;
	    	for (int i = 0; i < aLogHeaderDetailList.size(); i++) {
	    	    LOG.info("inside looop");
	    		System.out.println("DataloadSSB : Outcomelogdetail : ID : " +i);
	    	    	outcomelogdetail= new Outcomelogdetail();
	    	    	
	    	    	aLogHeaderDetail = aLogHeaderDetailList.get(i);
	    	    	
	    	    	outcomelogdetail.setApplicationcode(aLogHeaderDetail.getApplicationCode());
	    	    	
	    	    	
			    	if(clientRequestDatetime!=null){
			    		outcomelogdetail.setDbupdcreatedttm(clientRequestDatetime);
			    		System.out.println("DataloadSSB : setClientrequestdatetime outcomelogheader  " + clientRequestDatetime);
		    					    		
			    	}	
	    	    	//outcomelogdetail.setDbupdcreatedttm(aLogHeaderDetail.getdbupdcreatedttm);
	    	    	//outcomelogdetail.setDebugentry(debugentry);
	    	    	//outcomelogdetail.setEntrytimeatstep(entrytimeatstep);
	    	    	//outcomelogdetail.setErrortype(aLogHeaderDetail.getErrorType());
			    	System.out.println("DataloadSSB :  outcomelogdetail.setErrortype   " + outcomeStatus);
	    	    	outcomelogdetail.setErrortype(outcomeStatus);
	  /*  	    	if (aLogHeaderDetail.getExecutionSeq()!=null){
	    	    		outcomelogdetail.setExecutionseq(new Long(aLogHeaderDetail.getExecutionSeq()).longValue());
	    	    	}*/
	    	    	System.out.println("DataloadSSB :  aLogHeaderDetail.getExecutionSeq()  " + aLogHeaderDetail.getExecutionSeq());
	    	    	outcomelogdetail.setExecutionseq(aLogHeaderDetail.getExecutionSeq());
	    	    	//outcomelogdetail.setExittimeatstep(exittimeatstep);
	    	    	
	    	    	String inputPayload = aLogHeaderDetail.getInputPayload();
	    	    	String outputPayload= aLogHeaderDetail.getOutputPayload();
	    	    	if (inputPayload !=null){
	    	    		
	    	    		//String inputPayloadString=serializeSoapObject((SOAPElement)inputPayload);
	    	    		outcomelogdetail.setInputpayload(inputPayload);
	    	    		System.out.println("DataloadSSB : Outcomelogdetail : inputPayloadString " );
	    	    	}
	    	    	if (outputPayload !=null){
	    	    		//String outputPayloadString=serializeSoapObject((SOAPElement)outputPayload);
	    	    		outcomelogdetail.setOutputpayload(outputPayload);
	    	    	}
	    	    	
	    	    	outcomelogdetail.setSlstepname(aLogHeaderDetail.getSlStepName());
	    	    	System.out.println("DataloadSSB : Outcomelogdetail : Slstepname "+aLogHeaderDetail.getSlStepName());
	    	    	System.out.println("DataloadSSB : Outcomelogdetail : Sourcesystemerrordesc "+aLogHeaderDetail.getSourceSystemErrorDesc());
	    	    	outcomelogdetail.setSourcesystemerrordesc(aLogHeaderDetail.getSourceSystemErrorDesc());
	    	    	//outcomelogdetail.setSourcesystemerrordump(sourcesystemerrordump);
	    	    	outcomelogdetail.setOutcomelogheader(outcomelogheader);   	  
	    	    	outcomelogdetails.add(outcomelogdetail);
	    	    	
//	    	    	em.persist( outcomelogdetail );	 
	    	    	

	    	    
	    	}
	    	outcomelogheader.setOutcomelogdetails(outcomelogdetails);
	    	//em.getTransaction().begin();
	    	
	    	em.persist( outcomelogheader );
	    	LOG.info("After em.persists.:::::::::::");
	    	
	    	//em.getTransaction().commit();
	    	try{
	    		System.out.println("DataloadSSB : loaddata outcomelogheader id"+ outcomelogheader.getOutcomehdrid());
	    		// OfficeBrand NO MAIL
	    		//sendMail(outcomelogheader.getId(),outcomeStatus,outcomelogheader.getLobcode(),outcomelogheader.getParentcorrelationid());
	    		//mailFlag=false;
	    	}catch(Exception e){
	    		LOG.info("Exception in DDDDDD ::::: "+e.getMessage());
	    		e.printStackTrace();
	    	}
	    		    	
	    	System.out.println("DataloadSSB : loaddata outcomelogheader end ");
	    	
    	}catch (Exception t) {            
            System.out.println("DataloadSSB - caught unexpected exception1: " + t.getMessage());
            t.printStackTrace();
        } 
    }
	
	  private Outcomelogheader findByParentCorrelationID(String parentcorrelationid, String applicationcode,String lobcode) {
			
	    	Outcomelogheader  outcomeheader =null ;		
	    	Query query = null;
			try {			
									
				if (applicationcode.equalsIgnoreCase("")){
					query = em.createQuery("select o from Outcomelogheader o where o.parentcorrelationid= :parentcorrelationid  and lobcode=:lobcode" );
					query.setParameter("parentcorrelationid", parentcorrelationid);
					query.setParameter("lobcode", lobcode); 

				}else{
					query = em.createQuery("select o from Outcomelogheader o where o.parentcorrelationid= :parentcorrelationid and lobcode=:lobcode and o.applicationcode= :applicationcode" );
					query.setParameter("parentcorrelationid", parentcorrelationid); 
					query.setParameter("lobcode", lobcode); 
					query.setParameter("applicationcode", applicationcode); 
				}
				 
				List<Outcomelogheader> logheaderList=query.getResultList();
		       	 
			 	  for (Outcomelogheader objects : logheaderList) {
					
			 		 outcomeheader = objects;							
					
				}		 	  

			}catch (Exception e) {
				System.out.println("Database Exception "+ e);
				e.printStackTrace();
				
			}
			return outcomeheader;
		}


		private int getLobID(String lobname){
			Query query = em.createQuery("Select o from Lob o where o.name=:lobname");
			
			query.setParameter("lobname", lobname);
			Lob lob=(Lob)query.getSingleResult();
			int lobid=lob.getLobid();
			return lobid;
		}
		private String serializeSoapObject(SOAPElement payload) throws Exception{
	    	
	    	/*OutputFormat format    = new OutputFormat (payload.getAsDocument()); 
	        // as a String
	        StringWriter stringOut = new StringWriter ();    
	        XMLSerializer serial   = new XMLSerializer (stringOut, 
	                                                    format);
	        serial.serialize(payload);
	        // Display the XML       
	        String  xmlString= stringOut.toString();
	        */
	    	String xmlString = payload.getTextContent();
	    	//System.out.println("serializeSoapObject:textcontent "+xmlString);
	        return xmlString;
	    }

}
