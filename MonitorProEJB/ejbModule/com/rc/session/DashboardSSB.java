package com.rc.session;


import java.io.BufferedReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.apache.log4j.Logger;
import com.rc.exception.DashboardException;
import com.rc.jpa.Emailnotification;
import com.rc.jpa.Lob;
import com.rc.jpa.Login;
import com.rc.jpa.Messages;
import com.rc.jpa.Role;
import com.rc.jpa.Store;
import com.rc.jpa.User;
import com.rc.json.dto.ChartDetailDTO;
import com.rc.json.dto.ChartSummaryDTO;
import com.rc.json.dto.EmailDTO;
import com.rc.json.dto.GraphDetailDTO;
import com.rc.json.dto.LobDTO;
import com.rc.json.dto.LogHeader;
import com.rc.json.dto.LogHeaderDetail;
import com.rc.json.dto.MessageDTO;
import com.rc.json.dto.PageDTO;
import com.rc.json.dto.PerfGraphDTO;
import com.rc.json.dto.QueryDTO;
import com.rc.json.dto.RoleDTO;
import com.rc.json.dto.StoreDTO;
import com.rc.json.dto.TransactionDTO;
import com.rc.json.dto.UserDTO;

/**
 * Session Bean implementation class DashboardSSB
 */

@Stateless
public class DashboardSSB implements DashboardSSBRemote, DashboardSSBLocal {

    /**
     * Default constructor. 
     */
	 private static final Logger LOG = Logger.getLogger(DashboardSSB.class.getName());
	//Logger log = Logger.getLogger(DashboardSSB.class);
	
    public DashboardSSB() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext (unitName="MonitorProEJB")
    EntityManager em;
	
    @Override
	public ArrayList<TransactionDTO> getHourlyChart() throws DashboardException {
    	LOG.info("getHourlyChart<>");
    	ArrayList<TransactionDTO> transactions = null;
    	TransactionDTO aTransactionDTO=null;
		transactions = new ArrayList<TransactionDTO>();
    	
		Query query = em.createNativeQuery(buildHourlyChartQueryString());
		List<Object[]> hourlyChart=query.getResultList();     
		
	 	  for (Object[] objects : hourlyChart) {
	 		  
	 		aTransactionDTO = new TransactionDTO();
	 		java.math.BigInteger hour = (java.math.BigInteger) objects[0];
	 		java.math.BigInteger totalCount = (java.math.BigInteger) objects[1];
	 		 
	 		aTransactionDTO.setHour(hour.intValue());
			aTransactionDTO.setNoOfTransactions(totalCount.intValue());							 
			transactions.add(aTransactionDTO);
	 	   
	 		System.out.println("hourlyChart: hour-"+ hour + "totalCount-"+  totalCount);
	 	   
	 	  }
	 	 
	 	 
        return transactions;
	}
    private String buildHourlyChartQueryString(){		
		
		StringBuilder sb = new StringBuilder();		
//		this is for db2
//		sb.append("SELECT 6 as HOUR, count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < (CURRENT_TIMESTAMP - 5 HOURS) and CLIENTREQUESTDATETIME >(CURRENT_TIMESTAMP - 6 HOURS) "
//		+ " union " 
//		+ "SELECT 5 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < (CURRENT_TIMESTAMP - 4 HOURS) and CLIENTREQUESTDATETIME >(CURRENT_TIMESTAMP - 5 HOURS)  "
//		+ " union " 
//		+ "SELECT 4 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < (CURRENT_TIMESTAMP - 3 HOURS) and CLIENTREQUESTDATETIME >(CURRENT_TIMESTAMP - 4 HOURS)  "
//		+ " union " 
//		+ "SELECT 3 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < (CURRENT_TIMESTAMP - 2 HOURS) and CLIENTREQUESTDATETIME >(CURRENT_TIMESTAMP - 3 HOURS)  "
//		+ " union " 
//		+ "SELECT 2 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < (CURRENT_TIMESTAMP - 1 HOURS) and CLIENTREQUESTDATETIME >(CURRENT_TIMESTAMP - 2 HOURS)  "
//		+ " union " 
//		+ "SELECT 1 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < (CURRENT_TIMESTAMP	) and CLIENTREQUESTDATETIME >(CURRENT_TIMESTAMP - 1 HOURS) ORDER BY HOUR ASC  ");
		
		sb.append("SELECT 6 as HOUR, count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < DATE_SUB(NOW() , INTERVAL 5 HOUR) and CLIENTREQUESTDATETIME >DATE_SUB(NOW() , INTERVAL 6 HOUR) "
				+ " union " 
				+ "SELECT 5 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < DATE_SUB(NOW() , INTERVAL 4 HOUR) and CLIENTREQUESTDATETIME >DATE_SUB(NOW() , INTERVAL 5 HOUR)  "
				+ " union " 
				+ "SELECT 4 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < DATE_SUB(NOW(), INTERVAL 3 HOUR) and CLIENTREQUESTDATETIME >DATE_SUB(NOW() , INTERVAL  4 HOUR)  "
				+ " union " 
				+ "SELECT 3 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < DATE_SUB(NOW() , INTERVAL 2 HOUR) and CLIENTREQUESTDATETIME >DATE_SUB(NOW() , INTERVAL 3 HOUR)  "
				+ " union " 
				+ "SELECT 2 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < DATE_SUB(NOW(), INTERVAL 1 HOUR) and CLIENTREQUESTDATETIME >DATE_SUB(NOW() , INTERVAL  2 HOUR)  "
				+ " union " 
				+ "SELECT 1 as HOUR,count(*) as TOTAL_COUNT FROM OUTCOMELOGHEADER where CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP and CLIENTREQUESTDATETIME >DATE_SUB(NOW() , INTERVAL  1 HOUR) ORDER BY HOUR ASC  ");

		System.out.println("buildHourlyChartQueryString : "+sb.toString());
		return sb.toString();
	}
    

	private String buildGetUserIDQuery(String tokenID) {
		StringBuilder sb = new StringBuilder();		
		sb.append("select o.userid from Login o where tokenid= :tokenID");
		return sb.toString();
	}
//	private String isTokenValidQuery(String tokenID) {
//		StringBuilder sb = new StringBuilder();		
//		sb.append("select o.userid from login o where o.tokenid= :tokenID AND o.EXPIREON >  CURRENT_TIMESTAMP AND ISEXPIRED ='no' " );
//		return sb.toString();
//	}
//	private String buildLoginQueryString(String user, String password) {
//	StringBuilder sb = new StringBuilder();			
//	sb.append("select o from user o where o.username= :user and o.password = :password ");
//	return sb.toString();
//}
	@Override
	public void createLogin(int userID, String token) {
		// TODO Auto-generated method stub
		try{
			long halfHour = 30 * 60 * 1000;					
			java.sql.Timestamp expireTimestamp=getCurrentTimeStamp();
			expireTimestamp.setTime(getCurrentTimeStamp().getTime() + halfHour);
			Login login = new Login();
			User aUser = new User();
			aUser.setUserid(userID);
			login.setUserid(aUser);
			login.setTokenid(token);
			login.setLogindate(getCurrentTimeStamp());
			login.setExpireon(expireTimestamp);
			login.setIsexpired("no");
			em.persist( login );
		}catch(Exception e){
			System.out.println("DashBoardSSB.Exception createLogin Error" + e); // Console
			e.printStackTrace();
		}
		 
	}
	@Override
	public ArrayList<ChartSummaryDTO> getChartSummary(QueryDTO queryDTO) {
		LOG.info("Calling getChartSummary");
		
		ArrayList<ChartDetailDTO> successDetails = new ArrayList<ChartDetailDTO>();;
		ArrayList<ChartDetailDTO> errorsDetails = new ArrayList<ChartDetailDTO>();;
		ArrayList<ChartDetailDTO> warningDetails = new ArrayList<ChartDetailDTO>();;
		ArrayList<ChartDetailDTO> criticalDetails = new ArrayList<ChartDetailDTO>();;
		
		
		ChartSummaryDTO aSuccessSummaryDTO = new ChartSummaryDTO();
		ChartSummaryDTO aErrorsSummaryDTO = new ChartSummaryDTO();
		ChartSummaryDTO aWarningSummaryDTO = new ChartSummaryDTO();
		ChartSummaryDTO aCriticalSummaryDTO = new ChartSummaryDTO();
		
		ChartDetailDTO aSuccessChartDetailDTO  = null;
		ChartDetailDTO aErrorsChartDetailDTO  = null;
		ChartDetailDTO aWarningChartDetailDTO  = null;	
		ChartDetailDTO aCriticalChartDetailDTO  = null;
		
		ArrayList<ChartSummaryDTO> chartSummaryList = new ArrayList<ChartSummaryDTO>();

		try {
			String headerQueryString = buildChartQuery(queryDTO);				
			Query query = em.createNativeQuery(headerQueryString);
			System.out.println(query.toString());
			List<Object[]> chartSummary=query.getResultList();  
		 	  for (Object[] objects : chartSummary) {	
		 		String lobname = (String) objects[0];
		 		int success = ((Number) objects[1]).intValue();
		 		int errors = ((Number) objects[2]).intValue();
			 	int warnings = ((Number) objects[3]).intValue();
			 	int criticalErrors = ((Number) objects[4]).intValue();
			 	
			 	aSuccessChartDetailDTO  = new ChartDetailDTO();
			 	aSuccessChartDetailDTO.setName(lobname);
			 	aSuccessChartDetailDTO.setCount(success);
			 	successDetails.add(aSuccessChartDetailDTO);
			 	
				aErrorsChartDetailDTO  = new ChartDetailDTO();
				aErrorsChartDetailDTO.setName(lobname);
				aErrorsChartDetailDTO.setCount(errors);
				errorsDetails.add(aErrorsChartDetailDTO);
				
				aWarningChartDetailDTO  = new ChartDetailDTO();	
				aWarningChartDetailDTO.setName(lobname);
				aWarningChartDetailDTO.setCount(warnings);
				warningDetails.add(aWarningChartDetailDTO);
			 	
				aCriticalChartDetailDTO  = new ChartDetailDTO();
				aCriticalChartDetailDTO.setName(lobname);
				aCriticalChartDetailDTO.setCount(criticalErrors);
				criticalDetails.add(aCriticalChartDetailDTO);
				
			}
		 	 aSuccessSummaryDTO.setStatus("Success");
		 	 aSuccessSummaryDTO.setDetail(successDetails);
		 	 chartSummaryList.add(aSuccessSummaryDTO);
		 	 aErrorsSummaryDTO.setStatus("Errors");
		 	 aErrorsSummaryDTO.setDetail(errorsDetails);
		 	 chartSummaryList.add(aErrorsSummaryDTO);
		 	 aWarningSummaryDTO.setStatus("Warnings");
		 	 aWarningSummaryDTO.setDetail(warningDetails);
		 	 chartSummaryList.add(aWarningSummaryDTO);
		 	 aCriticalSummaryDTO.setStatus("Critical Errors");
		 	 aCriticalSummaryDTO.setDetail(criticalDetails);
		 	 chartSummaryList.add(aCriticalSummaryDTO);

		}catch (Exception e) {
			System.out.println("DashBoardSSB.getChartSummary Database Exception "+ e);
			//throw e;
			e.printStackTrace();
			
		}
		System.out.println(chartSummaryList.size());
		return chartSummaryList;
	}
	
	private String buildChartQuery(QueryDTO queryDTO){
		LOG.info("buildChartQuery called");
		ArrayList<LobDTO> aLobList= getLobList();
			
		StringBuilder sb = new StringBuilder();	
		int i=0;
		for (LobDTO aLobDTO : aLobList) {
			if (i>0){
				sb.append( " union " );
			}
			 if (queryDTO.getFromdate()!=null && !queryDTO.getFromdate().equalsIgnoreCase("") && queryDTO.getTodate()!=null && !queryDTO.getTodate().equalsIgnoreCase("")){
				
				sb.append(" select '"+aLobDTO.getName()+"' as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='"+aLobDTO.getName()+"'   " );
				if(queryDTO.getApplicationcode()!=null && !queryDTO.getApplicationcode().equalsIgnoreCase("")){
					sb.append(" AND  APPLICATIONCODE ='"+queryDTO.getApplicationcode().trim()+"' ");
				}
				//sb.append(" AND  CLIENTREQUESTDATETIME > ( TIMESTAMP_FORMAT('"+queryDTO.getFromdate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS')) AND CLIENTREQUESTDATETIME < (TIMESTAMP_FORMAT('"+queryDTO.getTodate() +" 23:59:59','MM/DD/YYYY HH24:MI:SS'))");
				sb.append(" AND  CLIENTREQUESTDATETIME > ( STR_TO_DATE('"+queryDTO.getFromdate() +":00','%m/%d/%Y %H:%i:%s')) AND CLIENTREQUESTDATETIME < ( STR_TO_DATE('"+queryDTO.getTodate() +":00','%m/%d/%Y %H:%i:%s'))");
			/*	sb.append( " union " );
				sb.append(" select '"+aLobDTO.getName()+"' as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='"+aLobDTO.getName()+"'   " );
				if(queryDTO.getApplicationcode()!=null && !queryDTO.getApplicationcode().equalsIgnoreCase("")){
					sb.append(" AND  APPLICATIONCODE ='"+queryDTO.getApplicationcode().trim()+"' ");
				}
				//sb.append(" AND  CLIENTREQUESTDATETIME > ( TIMESTAMP_FORMAT('"+queryDTO.getFromdate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS')) AND CLIENTREQUESTDATETIME < (TIMESTAMP_FORMAT('"+queryDTO.getTodate() +" 23:59:59','MM/DD/YYYY HH24:MI:SS'))");
				sb.append(" AND  CLIENTREQUESTDATETIME > ( STR_TO_DATE('"+queryDTO.getFromdate() +":00','%m/%d/%Y %H:%i:%s')) AND CLIENTREQUESTDATETIME < ( STR_TO_DATE('"+queryDTO.getTodate() +":00','%m/%d/%Y %H:%i:%s'))");
				sb.append( " union " );
				sb.append(" select '"+aLobDTO.getName()+"'  as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='"+aLobDTO.getName()+"'    " );
				if(queryDTO.getApplicationcode()!=null && !queryDTO.getApplicationcode().equalsIgnoreCase("")){
					sb.append(" AND  APPLICATIONCODE ='"+queryDTO.getApplicationcode().trim()+"' ");
				}
				//sb.append(" AND  CLIENTREQUESTDATETIME > ( TIMESTAMP_FORMAT('"+queryDTO.getFromdate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS')) AND CLIENTREQUESTDATETIME < (TIMESTAMP_FORMAT('"+queryDTO.getTodate() +" 23:59:59','MM/DD/YYYY HH24:MI:SS'))");
				sb.append(" AND  CLIENTREQUESTDATETIME > ( STR_TO_DATE('"+queryDTO.getFromdate() +":00','%m/%d/%Y %H:%i:%s')) AND CLIENTREQUESTDATETIME < ( STR_TO_DATE('"+queryDTO.getTodate() +":00','%m/%d/%Y %H:%i:%s'))");
			 */
			 }else{
				 
				sb.append(" select '"+aLobDTO.getName()+"' as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='"+aLobDTO.getName()+"'   " );
				 if (queryDTO.getApplicationcode()!=null && !queryDTO.getApplicationcode().equalsIgnoreCase(" ") && !queryDTO.getApplicationcode().equalsIgnoreCase("") && !queryDTO.getApplicationcode().contains("All")){
					sb.append(" AND  APPLICATIONCODE ='"+queryDTO.getApplicationcode().trim()+"' ");
				}
				// for db2 -->sb.append(" AND  CLIENTREQUESTDATETIME > (CURRENT_TIMESTAMP - 1 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
				 sb.append(" AND  CLIENTREQUESTDATETIME > DATE_SUB(NOW(), INTERVAL 1 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
			/*	 sb.append( " union " );
				sb.append(" select '"+aLobDTO.getName()+"' as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='"+aLobDTO.getName()+"'   " );
				 if (queryDTO.getApplicationcode()!=null && !queryDTO.getApplicationcode().equalsIgnoreCase("") && !queryDTO.getApplicationcode().contains("All")){
					sb.append(" AND  APPLICATIONCODE ='"+queryDTO.getApplicationcode().trim()+"' ");
				}
				//for db2-->sb.append(" AND  CLIENTREQUESTDATETIME > (CURRENT_TIMESTAMP - 1 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
				 sb.append(" AND  CLIENTREQUESTDATETIME > DATE_SUB(NOW(), INTERVAL 1 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
				 sb.append( " union " );
				sb.append(" select '"+aLobDTO.getName()+"'  as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='"+aLobDTO.getName()+"'    " );
				if(queryDTO.getApplicationcode()!=null && !queryDTO.getApplicationcode().equalsIgnoreCase("")){
					sb.append(" AND  APPLICATIONCODE ='"+queryDTO.getApplicationcode().trim()+"' ");
				}
				//for db2 -->sb.append(" AND  CLIENTREQUESTDATETIME > (CURRENT_TIMESTAMP - 1 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
				sb.append(" AND  CLIENTREQUESTDATETIME > DATE_SUB(NOW(), INTERVAL 1 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
*/
			}
			i++;
		}
		LOG.info("DashBoardSSB.getChartSummary buildChartQuery : "+sb.toString());
		return sb.toString();
	}
	
	@Override
	public ArrayList<LobDTO> getLobList() {
		// TODO Auto-generated method stub
    	ArrayList<LobDTO> lobList = null;
    	LobDTO aLobDTO=null;
    	lobList = new ArrayList<LobDTO>();
    	
		Query query = em.createQuery("Select o from Lob o ");
		List<Lob> lobNameList = query.getResultList();
       	 
	 	  for (Lob objects : lobNameList) {
	 		  
	 		aLobDTO = new LobDTO();
	 		String  lobName =  objects.getName();
	 		aLobDTO.setName(lobName.trim());
	 		aLobDTO.setStatus(objects.getIsactive());
	 		aLobDTO.setId(""+objects.getLobid());
	 		lobList.add(aLobDTO);
	 		//Test
	 		//System.out.println("LOB Name : "+ lobName );
	 	  }
        return lobList;
	}
	
	@Override
	public ArrayList<LobDTO> getActiveLobList() {
		ArrayList<LobDTO> lobList = null;
		LobDTO aLobDTO = null;
		lobList = new ArrayList<LobDTO>();
		Query query = em
				.createQuery("Select o from Lob o where o.isactive='Active'");
		List<Lob> lobNameList = query.getResultList();
      	 
	 	  for (Lob objects : lobNameList) {
	 		 aLobDTO = new LobDTO();
			String lobName = objects.getName();
			aLobDTO.setName(lobName.trim());
			aLobDTO.setStatus(objects.getIsactive());
			aLobDTO.setId((new StringBuilder()).append(objects.getLobid())
					.toString());
			lobList.add(aLobDTO);
		}

		return lobList;
	}
	
	@Override
	public PageDTO getLogHeaders(QueryDTO queryDTO) {
		ArrayList<LogHeader> logHeaders = new ArrayList<LogHeader>();
		PageDTO pageDTO = new PageDTO();
		try {
			
			// executing query to get total count for pagination
			String countQueryString = buildHeaderQueryString(queryDTO,true);
			//System.out.println("DashBoardSSB.getLogHeader countQueryString "+ countQueryString);
			Query queryCount = em.createNativeQuery(countQueryString);
			List<Object> countObj=queryCount.getResultList(); 

			int numberOfColumns =0;
			for (Object countObjects : countObj) {
				numberOfColumns = ((Number)countObjects).intValue();
			}
			//int numberOfColumns =query.getResultList().size();
			System.out.println("DashboardSSB.getLogHeaders numberOfColumns : "+numberOfColumns);
			
			// executing query for retrieval or records
			String headerQueryString = buildHeaderQueryString(queryDTO,false);
			System.out.println("DashBoardSSB.getLogHeader headerQueryString "+ headerQueryString);
			
			//Query query = em.createQuery(headerQueryString);
			Query query = em.createNativeQuery(headerQueryString);
			
			String queryDTOCurrPage = queryDTO.getCurrpage();
			String queryDTOPageSize = queryDTO.getPagesize();
			int currPage = 1;
			if (!queryDTOCurrPage.equalsIgnoreCase("0") && !queryDTOCurrPage.equalsIgnoreCase("") && queryDTOCurrPage!=null)	{				
				currPage = new Integer(queryDTOCurrPage).intValue();
			}
			int pageSize=5;
			if (!queryDTOPageSize.equalsIgnoreCase("0") && !queryDTOPageSize.equalsIgnoreCase("") && queryDTOPageSize!=null)	{				
				pageSize = new Integer(queryDTOPageSize).intValue();
			}
			
//			int startIndex=((currPage-1)* pageSize);
//			int lastIndex = (currPage * pageSize) - 1;
//			System.out.println("DashboardSSB.getLogHeaders pageSize : "+pageSize +" currPage : "+currPage+" , startIndex : "+startIndex );
//			query.setFirstResult(startIndex);			
//			query.setMaxResults(pageSize);
			
			//List<Outcomelogheader> outcomeHeaderList=query.setFlushMode(FlushModeType.COMMIT).setMaxResults(pageSize).setFirstResult(startIndex).getResultList();
			List<Object[]> outcomeHeaderList=query.getResultList();
			System.out.println("DashboardSSB.getLogHeaders Query Executed : ");
			LogHeader logHeader = null;
			//for db2
			//ArrayList<LogHeaderDetail> outcomeLogHeaderDetails = null;
			ArrayList<LogHeaderDetail> outcomeLogHeaderDetails = null;
			LogHeaderDetail logHeaderDetail= null;	
			long outcomeHeaderId = 0;

			List<Object[]> outcomelogdetailList=null;
			for (Object[] objects : outcomeHeaderList) {		
				logHeader = new LogHeader();
				logHeader.setApplicationID((String) objects[0]);
				logHeader.setClientReqDateTime(""+(Timestamp)objects[1]);
				logHeader.setClientUserID((String)objects[2]);
				logHeader.setCorrelID((String)objects[3]);
				logHeader.setErrorCode((String)objects[4]);
				logHeader.setOutcomeStatus((String)objects[5]);
				logHeader.setSlCode((String)objects[6]);
				logHeader.setTimeTaken(""+(BigInteger)objects[7]);
				outcomeHeaderId=((BigInteger)objects[8]).longValue();
				logHeader.setOutcomeHdrID(""+outcomeHeaderId);
				logHeader.setLob((String)objects[9]);
//				Character match = (Character) importQuery.getSingleResult();
				logHeader.setStoreName((String)objects[10]);
				//List<Outcomelogdetail> outcomelogdetailList=objects.getOutcomelogdetails();
				
	
				
				Query queryDetails = em.createQuery("select d.applicationcode,d.inputpayload,d.outcomedtlid,d.outputpayload,d.slstepname,d.sourcesystemerrorcode,d.sourcesystemerrordesc from Outcomelogdetail d where d.outcomehdrid.outcomehdrid=:outcomehdrid");
				queryDetails.setParameter("outcomehdrid", outcomeHeaderId);
				System.out.println("DashboardSSB getlogheaders outcomehdrid -- "+ outcomeHeaderId);
				outcomelogdetailList=queryDetails.getResultList();
				outcomeLogHeaderDetails = new ArrayList<LogHeaderDetail>();
				int i =1;
				System.out.println("DashboardSSB getlogheaders detailObjects "+ outcomelogdetailList.size());
				//if(outcomeHeaderId!=1225){ //uncomment for stg
				if(outcomeHeaderId!=1030 &&  !logHeader.getCorrelID().equalsIgnoreCase("")){ //uncomment for prd
					for (Object[]  detailObjects : outcomelogdetailList) {					
						//System.out.println("detailObjects "+ i++);
						
						//System.out.println("detailObjects "+ detailObjects.getId());
						logHeaderDetail = new LogHeaderDetail();
					/*	logHeaderDetail.setApplicationCode(detailObjects.getApplicationcode());
						logHeaderDetail.setInputPayload(detailObjects.getInputpayload());
						logHeaderDetail.setOutcomeDTLID(""+detailObjects.getOutcomedtlid());
						logHeaderDetail.setOutputPayload(""+detailObjects.getOutputpayload());
						logHeaderDetail.setSlStepName(""+ detailObjects.getSlstepname());
						logHeaderDetail.setSourceSystemErrorCode(""+ detailObjects.getSourcesystemerrorcode());
						logHeaderDetail.setSourceSystemErrorDesc(""+ detailObjects.getSourcesystemerrordesc());
						logHeaderDetail.setLogLevel("");
						*/
						logHeaderDetail.setApplicationCode((String)detailObjects[0]);
						logHeaderDetail.setInputPayload((String)detailObjects[1]);
						logHeaderDetail.setOutcomeDTLID(""+(Long)detailObjects[2]);
						logHeaderDetail.setOutputPayload(""+(String)detailObjects[3]);
						logHeaderDetail.setSlStepName(""+ (String)detailObjects[4]);
						logHeaderDetail.setSourceSystemErrorCode(""+ (String)detailObjects[5]);
						logHeaderDetail.setSourceSystemErrorDesc(""+ (String)detailObjects[6]);
						logHeaderDetail.setLogLevel("");
						
						outcomeLogHeaderDetails.add(logHeaderDetail);					
											
					}
				}
				logHeader.setLogHeaderDetail(outcomeLogHeaderDetails);				
				logHeaders.add(logHeader);
			}
			
			
			pageDTO.setLogHeader(logHeaders);
			if (!queryDTOCurrPage.equalsIgnoreCase("0"))	{				
				pageDTO.setPageNumber(queryDTO.getCurrpage());
			}else{
				pageDTO.setPageNumber("1");
			}
			
			pageDTO.setTotalNoOfRecords(""+numberOfColumns);
			
		} catch (Exception e) {
			System.out.println("DashBoardSSB.getLogHeader Database Exception "+ e);
			e.printStackTrace();
			//throw e;
			
		}
		
		return pageDTO;
	}
	private String buildHeaderQueryString( QueryDTO queryDTO,boolean isCount){
		
		StringBuilder sb = new StringBuilder();
//		 if (queryDTO.getPagesize()!=null && !queryDTO.getPagesize().equalsIgnoreCase("") && queryDTO.getCurrpage()!=null && !queryDTO.getCurrpage().equalsIgnoreCase("")){
//			 sb.append("SELECT * FROM ( ");
//		 }
			if (isCount){
				sb.append("SELECT count(*)  FROM Outcomelogheader  o , Store s");
			}else{
				//this is for db2 BEGIN
				//sb.append("SELECT * FROM ( ");
				 
				//sb.append("SELECT o.applicationcode,o.clientrequestdatetime,o.clientuserid,o.parentcorrelationid,o.errorcode,o.outcomestatus,o.slcode,o.timetaken,o.outcomehdrid,o.lobcode,s.storename,rownumber() OVER (ORDER BY CLIENTREQUESTDATETIME) AS ROW_NEXT  FROM Outcomelogheader  o , Store s ");
				//this is for db2 END
				//this is for MySql
				sb.append("SELECT o.applicationcode,o.clientrequestdatetime,o.clientuserid,o.parentcorrelationid,o.errorcode,o.outcomestatus,o.slcode,o.timetaken,o.outcomehdrid,o.lobcode,s.storename  FROM Outcomelogheader  o , Store s ");
				//sb.append("SELECT o  FROM Outcomelogheader  o");
			}
			boolean andFlag = false;
			 
			 sb.append(" where ");
			 sb.append(" o.applicationcode = s.storeid ");
			 andFlag=true;
			 
			  if (queryDTO.getLob()!=null && queryDTO.getLob()!=""){
				  if (andFlag){
						sb.append(" AND ");
					  } 
				  sb.append(" o.lobcode = '"+queryDTO.getLob()+"'");
				  andFlag=true;
			  }
			  
			  if (queryDTO.getOrderid()!=null && !queryDTO.getOrderid().equalsIgnoreCase("")){
				  if (andFlag){
					sb.append(" AND ");
				  } 
				 
				  sb.append(" LOWER( o.parentcorrelationid) LIKE LOWER( '%"+queryDTO.getOrderid().trim()+"%') ");//officebrand
				   andFlag=true;
				  
			  }
			  
			  //STORE in OFFICEBRAND
			  if (queryDTO.getApplicationcode()!=null && !queryDTO.getApplicationcode().equalsIgnoreCase("") && !queryDTO.getApplicationcode().contains("All")){
				 if (andFlag){
				 	sb.append(" AND ");
				  } 
					
				  sb.append("  o.applicationcode = '"+queryDTO.getApplicationcode().trim()+"' ");//officebrand
				   andFlag=true;
					  
			  }			 
			  
			  if (queryDTO.getStatus()!=null && !queryDTO.getStatus().equalsIgnoreCase("") && !queryDTO.getStatus().equalsIgnoreCase("ALL")){
				  if (andFlag){
				  	sb.append(" AND ");
				  } 
				  
				  sb.append(" o.outcomestatus = '"+queryDTO.getStatus()+"'");
				   andFlag=true;
			  }
			  
			  try{
				  if (queryDTO.getFromdate()!=null && !queryDTO.getFromdate().equalsIgnoreCase("") && queryDTO.getTodate()!=null && !queryDTO.getTodate().equalsIgnoreCase("") ){
	
					  if (andFlag){
					  		sb.append(" AND ");
				  	} 
					  //sb.append("  o.clientrequestdatetime > ( TIMESTAMP_FORMAT('"+queryDTO.getFromdate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS')) AND o.clientrequestdatetime < (TIMESTAMP_FORMAT('"+queryDTO.getTodate() +" 23:59:59','MM/DD/YYYY HH24:MI:SS'))");
					  sb.append("  o.clientrequestdatetime >  (STR_TO_DATE('"+queryDTO.getFromdate() +":00','%m/%d/%Y %H:%i:%s')) AND o.clientrequestdatetime < (STR_TO_DATE('"+queryDTO.getTodate() +":00','%m/%d/%Y %H:%i:%s'))");
					  
				  }else{
				  	if (andFlag){
				  			sb.append(" AND ");
				  	} 
					// for mysql 
				  	
				  	sb.append("   o.clientrequestdatetime > DATE_SUB(NOW(), INTERVAL 1 DAY)   AND  o.clientrequestdatetime < CURRENT_TIMESTAMP");
				  	//for db2
				  	//sb.append("   o.clientrequestdatetime > (CURRENT_TIMESTAMP - 2 DAY)   AND  o.clientrequestdatetime < CURRENT_TIMESTAMP");
				  }
			  }catch( Exception e){
				  System.out.println("DashBoardSSB.buildHeaderQuery "+e);
			  }			  			  
			  
		 
		  // pagination
		  if (isCount){
			  //do nothing
		  }		  else{
			  //pagination
			 String queryDTOCurrPage = queryDTO.getCurrpage();
			 System.out.println("DashBoardSSB.buildHeaderQuery queryDTOCurrPage: "+queryDTOCurrPage);
				String queryDTOPageSize = queryDTO.getPagesize();
				int currPage = 1;
				if (!queryDTOCurrPage.equalsIgnoreCase("0") && !queryDTOCurrPage.equalsIgnoreCase("") && queryDTOCurrPage!=null)	{				
					currPage = new Integer(queryDTOCurrPage).intValue();
				}
				int pageSize=5;
				if (!queryDTOPageSize.equalsIgnoreCase("0") && !queryDTOPageSize.equalsIgnoreCase("") && queryDTOPageSize!=null)	{				
					pageSize = new Integer(queryDTOPageSize).intValue();
				}
					//db2 begin		
				//int startIndex=((currPage-1)* pageSize)+1;
				//int lastIndex = (currPage * pageSize) ;
				//db2 end
				int mySQLOffset = (currPage - 1)*pageSize ;
				int mySQLrow_count = pageSize;
				
			  //sb.append(" AND ROWNUM BETWEEN "+startIndex+" AND "+lastIndex+" ORDER BY o.clientrequestdatetime  DESC"); //uncomment it for staging
			//for db2 begin
				//sb.append(" ) AS Outcomelogheader_temp WHERE ROW_NEXT BETWEEN "+startIndex+" AND "+lastIndex+" ORDER BY clientrequestdatetime  DESC"); //uncomment it for staging
			//db2 end	
				sb.append(" ORDER BY o.clientrequestdatetime  DESC LIMIT "+mySQLOffset+" , "+mySQLrow_count+" ");
				
						
			  //sb.append(" ORDER BY o.clientrequestdatetime  DESC");//comment this line for staging
		  }
				  
		  System.out.println("DashboardSSB.buildHeaderQueryString : query : "+sb.toString()); 
		  return sb.toString();
	
	}
	/*@Override
	public PageDTO getLogHeaders(QueryDTO queryDTO) {
		ArrayList<LogHeader> logHeaders = new ArrayList<LogHeader>();
		PageDTO pageDTO = new PageDTO();
		try {
			
			// executing query to get total count for pagination
			String countQueryString = buildHeaderQueryString(queryDTO,true);
			System.out.println("DashBoardSSB.getLogHeader countQueryString "+ countQueryString);
			Query queryCount = em.createNativeQuery(countQueryString);
			List<Object> countObj=queryCount.getResultList(); 

			int numberOfColumns =0;
			for (Object countObjects : countObj) {
			 numberOfColumns = ((Integer)countObjects).intValue();
			}
			//int numberOfColumns =query.getResultList().size();
			System.out.println("DashboardSSB.getLogHeaders numberOfColumns : "+numberOfColumns);
			
			// executing query for retrieval or records
			String headerQueryString = buildHeaderQueryString(queryDTO,false);
			System.out.println("DashBoardSSB.getLogHeader headerQueryString "+ headerQueryString);
			//Query query = em.createQuery(headerQueryString);
			Query query = em.createNativeQuery(headerQueryString);
			
			
			
			String queryDTOCurrPage = queryDTO.getCurrpage();
			String queryDTOPageSize = queryDTO.getPagesize();
			int currPage = 1;
			if (!queryDTOCurrPage.equalsIgnoreCase("0") && !queryDTOCurrPage.equalsIgnoreCase("") && queryDTOCurrPage!=null)	{				
				currPage = new Integer(queryDTOCurrPage).intValue();
			}
			int pageSize=5;
			if (!queryDTOPageSize.equalsIgnoreCase("0") && !queryDTOPageSize.equalsIgnoreCase("") && queryDTOPageSize!=null)	{				
				pageSize = new Integer(queryDTOPageSize).intValue();
			}
						
//			int startIndex=((currPage-1)* pageSize);
//			int lastIndex = (currPage * pageSize) - 1;
//			System.out.println("DashboardSSB.getLogHeaders pageSize : "+pageSize +" currPage : "+currPage+" , startIndex : "+startIndex );
//			query.setFirstResult(startIndex);			
//			query.setMaxResults(pageSize);
			
			//List<Outcomelogheader> outcomeHeaderList=query.setFlushMode(FlushModeType.COMMIT).setMaxResults(pageSize).setFirstResult(startIndex).getResultList();
			List<Object[]> outcomeHeaderList=query.getResultList();
			System.out.println("DashboardSSB.getLogHeaders Query Executed : ");
			LogHeader logHeader = null;
			ArrayList<LogHeaderDetail> outcomeLogHeaderDetails = null;
			LogHeaderDetail logHeaderDetail= null;	
			long outcomeHeaderId = 0;

			//List<Object[]> outcomelogdetailList=null;
			List<Outcomelogdetail> outcomelogdetailList=null;
			for (Object[] objects : outcomeHeaderList) {		
				
				logHeader = new LogHeader();
				logHeader.setApplicationID((String) objects[0]);
				
				logHeader.setClientReqDateTime(((Timestamp)objects[1]).toString());
				logHeader.setClientUserID((String)objects[2]);
				logHeader.setCorrelID((String)objects[3]);
				logHeader.setErrorCode((String)objects[4]);
				logHeader.setOutcomeStatus((String)objects[5]);
				logHeader.setSlCode((String)objects[6]);
				logHeader.setTimeTaken(""+(Long)objects[7]);
				outcomeHeaderId=((Long)objects[8]).longValue();
				logHeader.setOutcomeHdrID(""+outcomeHeaderId);
				logHeader.setLob((String)objects[9]);
				
				//List<Outcomelogdetail> outcomelogdetailList=objects.getOutcomelogdetails();
				
				//Query queryDetails = em.createNativeQuery("select d.APPLICATIONCODE,d.INPUTPAYLOAD,d.OUTCOMEDTLID ,d.OUTPUTPAYLOAD,d.SLSTEPNAME,d.SOURCESYSTEMERRORCODE,d.SOURCESYSTEMERRORDESC from Outcomelogdetail d where d.OUTCOMEHDRID=?");
				Query queryDetails = em.createQuery("select d from Outcomelogdetail d where d.outcomehdrid.outcomehdrid=:outcomehdrid");
				queryDetails.setParameter("outcomehdrid", outcomeHeaderId);
				outcomelogdetailList=queryDetails.getResultList();
				outcomeLogHeaderDetails = new ArrayList<LogHeaderDetail>();
				for (Outcomelogdetail detailObjects : outcomelogdetailList) {					
					
					logHeaderDetail = new LogHeaderDetail();
					logHeaderDetail.setApplicationCode(detailObjects.getApplicationcode());
					logHeaderDetail.setInputPayload(detailObjects.getInputpayload());
					logHeaderDetail.setOutcomeDTLID(""+detailObjects.getOutcomedtlid());
					logHeaderDetail.setOutputPayload(""+detailObjects.getOutputpayload());
					logHeaderDetail.setSlStepName(""+ detailObjects.getSlstepname());
					logHeaderDetail.setSourceSystemErrorCode(""+ detailObjects.getSourcesystemerrorcode());
					logHeaderDetail.setSourceSystemErrorDesc(""+ detailObjects.getSourcesystemerrordesc());
					logHeaderDetail.setLogLevel("");
					
					outcomeLogHeaderDetails.add(logHeaderDetail);					
										
				}
				logHeader.setLogHeaderDetail(outcomeLogHeaderDetails);				
				logHeaders.add(logHeader);
			}
			
			
			pageDTO.setLogHeader(logHeaders);
			if (!queryDTOCurrPage.equalsIgnoreCase("0"))	{				
				pageDTO.setPageNumber(queryDTO.getCurrpage());
			}else{
				pageDTO.setPageNumber("1");
			}
			
			pageDTO.setTotalNoOfRecords(""+numberOfColumns);
			
		} catch (Exception e) {
			System.out.println("DashBoardSSB.getLogHeader Database Exception "+ e);
			e.printStackTrace();
			//throw e;
			
		}
		
		return pageDTO;
	}
	
	
	//This method is not used as SetMaxResults is not filtering the data as expected
	//@Override
	public PageDTO getLogHeadersNotUsed(QueryDTO queryDTO) {
		ArrayList<LogHeader> logHeaders = new ArrayList<LogHeader>();
		PageDTO pageDTO = new PageDTO();
		try {
			
			// executing query to get total count for pagination
			String countQueryString = buildHeaderQueryString(queryDTO,true);
			System.out.println("DashBoardSSB.getLogHeader countQueryString "+ countQueryString);
			Query queryCount = em.createNativeQuery(countQueryString);
			List<Object> countObj=queryCount.getResultList(); 

			int numberOfColumns =0;
			for (Object countObjects : countObj) {
			 numberOfColumns = ((Integer)countObjects).intValue();
			}
			//int numberOfColumns =query.getResultList().size();
			System.out.println("DashboardSSB.getLogHeaders numberOfColumns : "+numberOfColumns);
			
			// executing query for retrieval or records
			String headerQueryString = buildHeaderQueryString(queryDTO,false);
			System.out.println("DashBoardSSB.getLogHeader headerQueryString "+ headerQueryString);
			//Query query = em.createQuery(headerQueryString);
			Query query = em.createNativeQuery(headerQueryString);
			
			
			
			String queryDTOCurrPage = queryDTO.getCurrpage();
			String queryDTOPageSize = queryDTO.getPagesize();
			int currPage = 1;
			if (!queryDTOCurrPage.equalsIgnoreCase("0") && !queryDTOCurrPage.equalsIgnoreCase("") && queryDTOCurrPage!=null)	{				
				currPage = new Integer(queryDTOCurrPage).intValue();
			}
			int pageSize=5;
			if (!queryDTOPageSize.equalsIgnoreCase("0") && !queryDTOPageSize.equalsIgnoreCase("") && queryDTOPageSize!=null)	{				
				pageSize = new Integer(queryDTOPageSize).intValue();
			}
						
			int startIndex=((currPage-1)* pageSize);
			int lastIndex = (currPage * pageSize) - 1;
			System.out.println("DashboardSSB.getLogHeaders pageSize : "+pageSize +" currPage : "+currPage+" , startIndex : "+startIndex );
			query.setFirstResult(startIndex);			
			query.setMaxResults(pageSize);
			
			//List<Outcomelogheader> outcomeHeaderList=query.setFlushMode(FlushModeType.COMMIT).setMaxResults(pageSize).setFirstResult(startIndex).getResultList();
			List<Outcomelogheader> outcomeHeaderList=query.getResultList();
			System.out.println("DashboardSSB.getLogHeaders Query Executed : ");
			LogHeader logHeader = null;
			ArrayList<LogHeaderDetail> outcomeLogHeaderDetails = null;
			LogHeaderDetail logHeaderDetail= null;
			

			Integer outcomeHeaderId = 0;
	
			for (Outcomelogheader objects : outcomeHeaderList) {		
				
				logHeader = new LogHeader();
				logHeader.setApplicationID(objects.getApplicationcode());
				logHeader.setClientReqDateTime(""+objects.getClientrequestdatetime());
				logHeader.setClientUserID(objects.getClientuserid());
				logHeader.setCorrelID(objects.getCorrelationid());
				logHeader.setErrorCode(objects.getErrorcode());
				logHeader.setOutcomeStatus(objects.getOutcomestatus());
				logHeader.setSlCode(objects.getSlcode());
				logHeader.setTimeTaken(""+objects.getTimetaken());
	
				logHeader.setOutcomeHdrID(""+objects.getOutcomehdrid());
				logHeader.setLob(objects.getLobcode());
				
				//List<Outcomelogdetail> outcomelogdetailList=objects.getOutcomelogdetails();
				Set<Outcomelogdetail> outcomelogdetailList=objects.getOutcomelogdetailCollection();
				outcomeLogHeaderDetails = new ArrayList<LogHeaderDetail>();
				for (Outcomelogdetail detailObjects : outcomelogdetailList) {					
					
					logHeaderDetail = new LogHeaderDetail();
					logHeaderDetail.setApplicationCode(detailObjects.getApplicationcode());
					logHeaderDetail.setInputPayload(""+detailObjects.getInputpayload());
					logHeaderDetail.setOutcomeDTLID(""+detailObjects.getOutcomedtlid());
					logHeaderDetail.setOutputPayload(""+detailObjects.getOutputpayload());
					logHeaderDetail.setSlStepName(detailObjects.getSlstepname());
					logHeaderDetail.setSourceSystemErrorCode(detailObjects.getSourcesystemerrorcode());
					logHeaderDetail.setSourceSystemErrorDesc(detailObjects.getSourcesystemerrordesc());
					logHeaderDetail.setLogLevel("");
					outcomeLogHeaderDetails.add(logHeaderDetail);					
										
				}
				logHeader.setLogHeaderDetail(outcomeLogHeaderDetails);				
				logHeaders.add(logHeader);
			}
			
			
			pageDTO.setLogHeader(logHeaders);
			if (!queryDTOCurrPage.equalsIgnoreCase("0"))	{				
				pageDTO.setPageNumber(queryDTO.getCurrpage());
			}else{
				pageDTO.setPageNumber("1");
			}
			
			pageDTO.setTotalNoOfRecords(""+numberOfColumns);
			
		} catch (Exception e) {
			System.out.println("DashBoardSSB.getLogHeader Database Exception "+ e);
			e.printStackTrace();
			//throw e;
			
		}
		return pageDTO;
	}
	private String buildHeaderQueryString( QueryDTO queryDTO,boolean isCount){
		
		StringBuilder sb = new StringBuilder();
//		 if (queryDTO.getPagesize()!=null && !queryDTO.getPagesize().equalsIgnoreCase("") && queryDTO.getCurrpage()!=null && !queryDTO.getCurrpage().equalsIgnoreCase("")){
//			 sb.append("SELECT * FROM ( ");
//		 }
			if (isCount){
				sb.append("SELECT count(*)  FROM Outcomelogheader  o");
			}else{
				sb.append("SELECT APPLICATIONCODE,CLIENTREQUESTDATETIME,CLIENTUSERID,CORRELATIONID,ERRORCODE,OUTCOMESTATUS,SLCODE,TIMETAKEN,OUTCOMEHDRID,LOBCODE  FROM Outcomelogheader  o");
			}

		  //filter 1
		 // lob is selected  
		  if(queryDTO.getLob()!= null ){
			  sb.append(" where ");
			  if (queryDTO.getLob()!=null && queryDTO.getLob()!=""){
				  sb.append(" o.lobcode = '"+queryDTO.getLob()+"'");
			  }
			  
			  if (queryDTO.getOrderid()!=null && !queryDTO.getOrderid().equalsIgnoreCase("")){
				  sb.append(" AND o.outcomehdrid = "+queryDTO.getOrderid());
			  }
			  
			  if (queryDTO.getStatus()!=null && !queryDTO.getStatus().equalsIgnoreCase("") && !queryDTO.getStatus().equalsIgnoreCase("ALL")){
				  sb.append(" AND o.outcomestatus = '"+queryDTO.getStatus()+"'");
			  }
			  
			  try{
				  if (queryDTO.getFromdate()!=null && !queryDTO.getFromdate().equalsIgnoreCase("") && queryDTO.getTodate()!=null && !queryDTO.getTodate().equalsIgnoreCase("") ){
	
					  sb.append(" AND o.clientrequestdatetime > ( TIMESTAMP_FORMAT('"+queryDTO.getFromdate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS')) AND o.clientrequestdatetime < (TIMESTAMP_FORMAT('"+queryDTO.getTodate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS'))");
				  }else{
					  //sb.append(" AND o.clientrequestdatetime > (CURRENT_TIMESTAMP - 2 DAY)  AND o.clientrequestdatetime < CURRENT_TIMESTAMP");
				  }
			  }catch( Exception e){
				  System.out.println("DashBoardSSB.buildHeaderQuery "+e);
			  }			  			  
			  
		  }
		  //filter 2
		  // no LOB selected only outcomestatus is selected
		  if (queryDTO.getLob() == null && queryDTO.getStatus()!=null && !queryDTO.getStatus().equalsIgnoreCase("") && !queryDTO.getStatus().equalsIgnoreCase("ALL")){
			  sb.append(" WHERE o.outcomestatus = '"+queryDTO.getStatus()+"'");

			  try{
				  if (queryDTO.getFromdate()!=null && !queryDTO.getFromdate().equalsIgnoreCase("") && queryDTO.getTodate()!=null && !queryDTO.getTodate().equalsIgnoreCase("") ){
					  sb.append(" AND o.clientrequestdatetime > ( TIMESTAMP_FORMAT('"+queryDTO.getFromdate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS')) AND o.clientrequestdatetime < (TIMESTAMP_FORMAT('"+queryDTO.getTodate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS'))");
				  }else{
					 // sb.append(" AND o.clientrequestdatetime > (CURRENT_TIMESTAMP - 2 DAY)  AND o.clientrequestdatetime < CURRENT_TIMESTAMP");
				  }
			  }catch( Exception e){
				  System.out.println("DashBoardSSB.buildHeaderQuery "+e);
			  }
		  
		  }
		  //filter 3
		// no LOB selected, no outcomestatus is selected only date filter is selected
		  if (queryDTO.getLob() == null && queryDTO.getStatus()==null){			  

			  try{
				  if (queryDTO.getFromdate()!=null && !queryDTO.getFromdate().equalsIgnoreCase("") && queryDTO.getTodate()!=null && !queryDTO.getTodate().equalsIgnoreCase("") ){
					  //System.out.println("queryDTO.getFromdate() "+queryDTO.getFromdate());
					  
					  sb.append(" WHERE o.clientrequestdatetime > ( TIMESTAMP_FORMAT('"+queryDTO.getFromdate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS')) AND o.clientrequestdatetime < (TIMESTAMP_FORMAT('"+queryDTO.getTodate() +" 00:00:00','MM/DD/YYYY HH24:MI:SS'))");
				  
				  }else{
					  
					  //sb.append(" WHERE o.clientrequestdatetime > (CURRENT_TIMESTAMP - 2 DAY)  AND o.clientrequestdatetime < CURRENT_TIMESTAMP");
				  }
			  }catch( Exception e){
				  System.out.println("DashBoardSSB.buildHeaderQuery "+e);
			  }
		  
		  }
		  // pagination
		  if (isCount){
			  //do nothing
		  }		  else{
			  //pagination
			 String queryDTOCurrPage = queryDTO.getCurrpage();
			 System.out.println("DashBoardSSB.buildHeaderQuery queryDTOCurrPage: "+queryDTOCurrPage);
				String queryDTOPageSize = queryDTO.getPagesize();
				int currPage = 1;
				if (!queryDTOCurrPage.equalsIgnoreCase("0") && !queryDTOCurrPage.equalsIgnoreCase("") && queryDTOCurrPage!=null)	{				
					currPage = new Integer(queryDTOCurrPage).intValue();
				}
				int pageSize=5;
				if (!queryDTOPageSize.equalsIgnoreCase("0") && !queryDTOPageSize.equalsIgnoreCase("") && queryDTOPageSize!=null)	{				
					pageSize = new Integer(queryDTOPageSize).intValue();
				}
							
				int startIndex=((currPage-1)* pageSize)+1;
				int lastIndex = (currPage * pageSize) ;
			  sb.append(" AND ROWNUM BETWEEN "+startIndex+" AND "+lastIndex+" ORDER BY o.clientrequestdatetime  DESC");
				//sb.append(" AND RN BETWEEN "+startIndex+" AND "+lastIndex+" ORDER BY RN  ");
				
			  //sb.append(" ORDER BY o.clientrequestdatetime  DESC");
		  }
				  
		  //System.out.println("DashboardSSB.buildHeaderQueryString : query : "+sb.toString()); 
		  return sb.toString();
	
	}
	*/
	//Message board messages
	private String buildMbMessagesQueryString() {
	
	
	StringBuilder sb = new StringBuilder();		
	//sb.append("select a.messagedesc, a.messagedate, b.username from messages a, user b "
			//+ "where a.userid=b.userid and a.messagedate > (CURRENT_TIMESTAMP - 2 DAY)   AND  a.messagedate < CURRENT_TIMESTAMP");
	sb.append("select a from messages a where a.messagedate > :twoDaysAgo   AND  a.messagedate < :today");
	return sb.toString();
}
	@Override
	public ArrayList<MessageDTO> getMBMessages() {
		ArrayList<MessageDTO> mbMessages = null;
		
		try {
			Date now = new Date();
			Timestamp twoDaysAgo = new Timestamp(now.getTime() - 86400000*2);
			Timestamp today = new Timestamp(now.getTime());
			String mbMessagesQueryString = buildMbMessagesQueryString();
			Query query = em.createQuery("select a from Messages a where a.messagedate > :twoDaysAgo   AND  a.messagedate < :today");
			//System.out.println("mbMessagesQueryString "+ mbMessagesQueryString);
			query.setParameter("twoDaysAgo", twoDaysAgo); 
			query.setParameter("today", today); 
			
			List<Messages> messageList=query.getResultList();
			
			MessageDTO aMessageDTO=null;
			mbMessages = new ArrayList<MessageDTO>();
			
			 for (Messages objects : messageList) {			
				
				aMessageDTO = new MessageDTO();
				aMessageDTO.setMessage(objects.getMessagedesc());
				aMessageDTO.setMsgCreatedDate(objects.getMessagedate().toString());
				aMessageDTO.setUserId(objects.getUserid().getUsername());				
				mbMessages.add(aMessageDTO);
				
			}

		}catch (Exception e) {
			System.out.println("DashBoardSSB.getMbMessages Database Exception "+ e);
			//throw e;
			e.printStackTrace();
			
		}
		return mbMessages;

	
	}
	@Override
	public boolean postMessage(int userID, String tokenId, String messageDesc) {
		// TODO Auto-generated method stub
		Messages aMessage = new Messages();
		aMessage.setMessagedate(getCurrentTimeStamp());
		aMessage.setMessagedesc(messageDesc);
		User aUser = new User();
		aUser.setUserid(userID);
		aMessage.setUserid(aUser);
		em.persist( aMessage );
		return true;
	}
	private static java.sql.Timestamp getCurrentTimeStamp() {
		 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
	
	@Override
	public UserDTO validateLogin(String user, String password){
		
		boolean isValid = false;
		UserDTO aUserDTO = null;

		try {			
			aUserDTO = new UserDTO();
			Query query = em.createQuery("select o from User o where o.username= :user and o.password = :password");
			query.setParameter("user", user); 
			query.setParameter("password", password); 
			List<User> userList = query.getResultList();
	       	 
		 	  for (User objects : userList) {
				aUserDTO.setUserID(objects.getUserid());
				aUserDTO.setUserName(objects.getUsername());
				aUserDTO.setStatus(objects.getIsactive());	
				aUserDTO.setIsAdmin(objects.getIsadmin());
				
			}

		}catch (Exception e) {
			System.out.println("DashBoardSSB.validateLogin Database Exception "+ e);
			//throw e;
			e.printStackTrace();
		}
		return aUserDTO;
	}
	@Override
	public UserDTO validateSession(String tokenID) {
		
		boolean isValid = false;
		UserDTO aUserDTO = null;

		try {
			
			aUserDTO = new UserDTO();
			Query query = em.createQuery("select o from Login o where o.tokenid= :tokenID AND o.EXPIREON >  CURRENT_TIMESTAMP AND ISEXPIRED ='no' " );
			query.setParameter("tokenID", tokenID); 
			 
			List<Login> loginList=query.getResultList();
	       	 
		 	  for (Login objects : loginList) {
				
				aUserDTO.setUserID(objects.getUserid().getUserid());
				//aUserDTO.setUserName(objects.getUserid());
				aUserDTO.setStatus("valid");				
				
			}

		}catch (Exception e) {
			System.out.println("DashBoardSSB.validateSession Database Exception "+ e);
			//throw e;
			e.printStackTrace();
		}
		return aUserDTO;
	}
	@Override
	public boolean logoutUser(String token) {
		// TODO Auto-generated method stub		
		Login aLogin = new Login();
		
		Query query = em.createQuery("select o from Login o where o.tokenid= :tokenID " );
		query.setParameter("tokenID", token); 
		 
		Object aLoginObj=query.getSingleResult();
		if(aLoginObj!=null){
			aLogin = (Login)aLoginObj;
			aLogin.setIsexpired("yes");
			em.persist( aLogin );
		}
		
		return true;
		
	}
	@Override
	public ArrayList<EmailDTO> getNFEMails() {
		ArrayList<EmailDTO> nfeMails = null;
		
		try {			
			Query query = em.createQuery("select a from Emailnotification a");			
			List<Emailnotification> emailList=query.getResultList();		
			EmailDTO aEmailDTO=null;
			nfeMails = new ArrayList<EmailDTO>();			
			 for (Emailnotification objects : emailList) {	
				
				 aEmailDTO = new EmailDTO();
				 //need to fix this
				 aEmailDTO.setEmails(objects.getEmails());
				 aEmailDTO.setEvent(objects.getEvent());
				 aEmailDTO.setId(""+objects.getEnid());
				 aEmailDTO.setLobid(""+objects.getLobid().getName());				
				 nfeMails.add(aEmailDTO);				
			}

		}catch (Exception e) {
			System.out.println("DashBoardSSB.getMbMessages Database Exception "+ e);
			//throw e;
			e.printStackTrace();
			
		}
		return nfeMails;
	
	}
	
	@Override
	public ArrayList<UserDTO> getUsers() {
		ArrayList<UserDTO> users = null;
		ArrayList<RoleDTO> roleList = null;
		
		try {			
			Query query = em.createQuery("select a from User a");	
			LOG.info(query.toString());
			List<User> userList = query.getResultList();
			System.out.println(userList.size());
			LOG.info(userList.toString());
			UserDTO aUserDTO=null;
			users = new ArrayList<UserDTO>();
			Set<Role> roles=null;
			RoleDTO aRoleDTO = null;
			 for (User objects : userList) {	
				
				 
				 aUserDTO = new UserDTO();
				 System.out.println(objects.getUserid());
				 aUserDTO.setIsAdmin(objects.getIsadmin());
				 
				 aUserDTO.setStatus(objects.getIsactive());	
				 aUserDTO.setEmail(objects.getEmailid());		
				 roles=(Set<Role>) objects.getRoleCollection();
				 roleList = new ArrayList<RoleDTO>();
				 for (Role roleObjects : roles) {
					 aRoleDTO = new RoleDTO();
					 aRoleDTO.setLobid(""+roleObjects.getLobid());					 
					 roleList.add(aRoleDTO);
				 }
				 aUserDTO.setRoles(roleList);
				 aUserDTO.setUserID(objects.getUserid());
				 aUserDTO.setUserName(objects.getUsername());			
				 users.add(aUserDTO);				
			}

		}catch (Exception e) {
			System.out.println("DashBoardSSB.getMbMessages Database Exception "+ e);
			//throw e;
			e.printStackTrace();
			
		}
		return users;
	}
	

	@Override 
	public boolean saveEmailNotification(int lobId, String eventStr, String emailId) 
	{
//		String event ="";
//		if(eventStr.equalsIgnoreCase("on critical error")){
//			event = "Critical Errors";
//		}else if(eventStr.equalsIgnoreCase("on success")){
//			event = "Success";
//		}else if(eventStr.equalsIgnoreCase("on error")){
//			event = "Errors";
//		}else if(eventStr.equalsIgnoreCase("on warning")){
//			event = "Warnings";
//		}
		
			System.out.println("SAVING TO DB -->> "+eventStr);
			Emailnotification en = new Emailnotification();		
			
			//Lob lob = new Lob();
			//lob.setLobid(new Integer(lobId).intValue());
			
			Query query = em.createQuery("select en from Emailnotification en where en.lobid = :lob and en.event = :evn");
			Lob lob = new Lob();
			lob.setLobid(new Integer(lobId).intValue());
			query.setParameter("lob", lob);
			query.setParameter("evn", eventStr);
			boolean emptyChk = query.getResultList().isEmpty();
			
			if (emptyChk){
				Lob lobObj = (Lob) em.find(Lob.class, lobId);
				en.setLobid(lobObj);				
				en.setEvent(eventStr);				
				en.setEmails(emailId);		
				em.persist(en);
				return true;
				
			}else{
				List<Emailnotification> resultSet =  query.getResultList();
				
				//Check if provided email is already present in the DB.
				
				List<String> newEmailList = Arrays.asList(emailId.split(","));
				Emailnotification enRecord = resultSet.get(0);
				int notificationID = enRecord.getEnid();
				String dbEmails = enRecord.getEmails();
				List<String> dbEmailList = Arrays.asList( dbEmails.split(",") );
				StringBuilder emailStringBuilder = new StringBuilder();
				emailStringBuilder.append(dbEmails);
				
				System.out.println("Email already in DB ----->>>>>> "+ dbEmailList);
				
				for (String newEmail : newEmailList){
					boolean bval = false;
					System.out.println("----------------Inner Loop-------------------");
					System.out.println("- New Email ---"+ newEmail);
					
					for (String dbEmail : dbEmailList){
						if (!newEmail.trim().equalsIgnoreCase(dbEmail.trim()))	{
							//System.out.println("New Email ----->>>>>> "+newEmail);
							//System.out.println("DB Email ----->>>>>> "+dbEmail);
							bval = true;
						}else{
							bval = false;
							//System.out.println("Email Found in DB ----->>>>>> "+newEmail);
							//System.out.println("DB Found. Break the loop");
							break;
						}
					}
					
					if (bval){
						
						emailStringBuilder.append(","+newEmail);
						
						System.out.println("Email Not Found in DB ----->>>>>> "+newEmail);
						bval = false;
					}
				}
				
				//System.out.println("Updated Email List ----->>>>>> " + emailStringBuilder.toString());
				
				if (!emailStringBuilder.toString().isEmpty()){
					System.out.println("Saving Email to DB ----->>>>>> "+emailStringBuilder.toString().trim());
					Emailnotification enUpdate = em.find(Emailnotification.class, notificationID);
					enUpdate.setEmails(emailStringBuilder.toString().trim());
					em.merge(enUpdate);				
				}
			}
			
			return true;

		}
	 	
		@Override
		public boolean updateEmailNotification(int enid, int lobId, String event, String emails)
		{
			Emailnotification en = em.find(Emailnotification.class, Integer.valueOf(enid));
			
			Lob lobObj = (Lob) em.find(Lob.class, lobId);
			en.setLobid(lobObj);				
			en.setEvent(event);				
			en.setEmails(emails);		
			em.merge(en);
			return true;
			
		}
		
		@Override
		public boolean deleteEmailNotification(String notificationId) {
		
		Emailnotification notification = em.find(Emailnotification.class, Integer.valueOf(notificationId));
		em.remove(notification);
		
		//System.out.println("************ DashBoardSSB Delete User, Data Deleted **********" );
		//log.info("DashBoardSSB -->> EmailNotification Deleted-"+ notificationId);
		return true;
	}
	
	/*public boolean saveEmailNotification(int lobId, String event, String emailId) {
	
		Emailnotification en = new Emailnotification();		
		Lob lob = new Lob();
		lob.setLobid(new Integer(lobId).intValue());
		en.setLobid(lob);
		if(event.equalsIgnoreCase("on critical error")){
			event = "Critical Errors";
		}else if(event.equalsIgnoreCase("on success")){
			event = "Success";
		}else if(event.equalsIgnoreCase("on error")){
			event = "Errors";
		}else if(event.equalsIgnoreCase("on warning")){
			event = "Warnings";
		}
		en.setEvent(event);
		en.setEmails(emailId);		
		em.persist(en);		
		return true;
	}*/

	@Override
	public boolean saveUser(String userName , String pwd , String isActive , String isAdmin, String lobid,String emailid)
	{
		
		ArrayList<String> items = new ArrayList<String>();
		Collections.addAll(items, lobid.split("\\s*,\\s*"));
		
		User user = new User();
		Role role = null;
		Lob lob = null;
		
		Set<Role> roleCollection = new HashSet<Role>();
		for (String lobObjects : items) {
			role = new Role();
			lob = new Lob();
			lob.setLobid(new Integer(lobObjects).intValue());
			role.setLobid(lob);		
			role.setUserid(user);
			roleCollection.add(role);
			
		}
		user.setRoleCollection(roleCollection);
		user.setUsername(userName);
		user.setPassword(pwd);
		user.setIsactive(isActive);
		user.setIsadmin(isAdmin);
		user.setEmailid(emailid);
		em.merge(user);
//		em.persist(user);
		
		return true;
		
	}

	@Override
	public boolean updateUser(String userId, String userName , String pwd , String isActive , String isAdmin, String lobid,String emailid)
	{
		User user = em.find(User.class, Integer.valueOf(userId));
		Query query = em.createQuery("select role from Role role where role.userid = :user");
		query.setParameter("user", user);
		List<Role> lst = (List<Role>) query.getResultList();
		
		for (Role role : lst)
		{
			System.out.println(role.getRoleid() + " _ " + role.getLobid().getName()+ " _ " +role.getUserid().getUsername());
			em.remove(role);
		}
		
		//userId = "183";
		//userName = "Happy ji";
		//pwd = "hpy";
		//isActive = "false";
		//isAdmin = "false";
		//lobid="2150,2170";
		//emailid="email@email.com";
		
		ArrayList<String> items = new ArrayList<String>();
		Collections.addAll(items, lobid.split("\\s*,\\s*"));
		
//		User user = new User();
		Role role = null;
		Lob lob = null;
		
		Set<Role> roleCollection = new HashSet<Role>();
		for (String lobObjects : items) {
			role = new Role();
			lob = new Lob();
			lob.setLobid(new Integer(lobObjects).intValue());
			role.setLobid(lob);		
			role.setUserid(user);
			roleCollection.add(role);
			
		 }
		user.setRoleCollection(roleCollection);
		user.setUsername(userName);
		user.setPassword(pwd);
		user.setIsactive(isActive);
		user.setIsadmin(isAdmin);
		user.setEmailid(emailid);
//		em.persist(user);
		em.merge(user);
		
		return true;
		
	}
	
	@Override
	public boolean deleteUser(String userId) {
		
		User user = em.find(User.class, Integer.valueOf(userId));
		
//		Query query = em.createQuery("select rol from Role rol where rol.userid = :user");
//		query.setParameter("user", user);
//		List<Role> roleList = query.getResultList();
//		for (Role rol : roleList)
//		{
//			System.out.println(" Deleting ROLE dependency. RoleID-> "+ rol.getRoleid() );
//			em.remove(rol);
//		}

		System.out.println("User to DELETE __ >>"+ user.getUserid());
		em.remove(user);
		
		System.out.println("************ DashBoardSSB Delete User, Data Deleted **********" );
		
		return true;
		
		//To delete ROLE, Cascade option has been placed on DB Table.
	}
	
	@Override
	public boolean saveTab(String tabName, String isActive) {
				
		//Check if the Tab Name is already present in DB
		Query query = em.createQuery("select lb from Lob lb where lb.name = :lobname");
		query.setParameter("lobname", tabName);
		System.out.println(tabName);
		if ( query.getResultList().isEmpty() ){
			
			Lob lob = new Lob();
			lob.setName(tabName);
			lob.setIsactive(isActive);
			System.out.println("DashBoardSSB.saveTab tabName "+tabName +" isActive "+isActive );
			em.persist(lob);
			
		}else{
			// Tab name alreasy exists
			return false;
		}
			
		
		return true;
	}
	
	@Override
	public boolean updateTab(String tabId, String tabName, String tabStatus) {
		
		System.out.println("DashBoardSSB Update Tab" );
		
		Lob tab = em.find(Lob.class, Integer.valueOf(tabId));
		tab.setName(tabName);
		tab.setIsactive(tabStatus);
		em.merge(tab);
		
		System.out.println("************ DashBoardSSB Update Tab, Data Inserted **********" );
		//System.out.println("DashBoardSSB.saveTab tabName "+tabName +" isActive "+isActive );		
		//em.persist(lob);
		return true;
	}
	
	@Override
	public boolean deleteTab(String tabId) {
		
		Lob tab = em.find(Lob.class, Integer.valueOf(tabId));
		em.remove(tab);
		
		System.out.println("************ DashBoardSSB Delete Tab, Data Deleted **********" );

		return true;
	}
	
	private String convertClobString(Clob payload){
		
		 StringBuffer strOut = new StringBuffer();
		 String clobStr ="";
		   String aux;
		   try {
		    BufferedReader br = new BufferedReader(payload.getCharacterStream());
		    while ((aux=br.readLine())!=null) {
		     strOut.append(aux);
		     strOut.append(System.getProperty("line.separator"));
		    }
		   }catch (Exception e) {
		    e.printStackTrace();
		   }
		   clobStr = strOut.toString();
		   System.out.println(clobStr);
		  
		  return clobStr;
	}
	//select avg(timestamp(b.dbupdcreatedttm) - timestamp(a.dbupdcreatedttm)) from DURGESH.OUTCOMELOGDETAIL a, DURGESH.OUTCOMELOGDETAIL b,durgesh.outcomelogheader c  where a.outcomehdrid=c.outcomehdrid and a.executionseq=1 and b.outcomehdrid =a.outcomehdrid  and b.executionseq=2 and c.CLIENTREQUESTDATETIME > (CURRENT_TIMESTAMP - 1 DAY)   AND  c.CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP
	@Override	
	public PerfGraphDTO getPerfGraph(String lobID, String days) {
		PerfGraphDTO perfGraphDTO = new PerfGraphDTO();		
    	ArrayList<GraphDetailDTO> series = new ArrayList<GraphDetailDTO>();
    	ArrayList<String> categoryList= new ArrayList<String>();
		ArrayList<Double> dataList1= new ArrayList<Double>();
		ArrayList<Double> dataList2= new ArrayList<Double>();
		GraphDetailDTO graphDetailDTOesb = null;
	 	GraphDetailDTO graphDetailDTOsap = null;
	 	double unit1 =0;
	 	double unit2 =0;
	 	
	 	String tabName=getLobName(lobID);
		Query query = em.createNativeQuery(buildPerfGraphQueryString(tabName.trim(),days));
		List<Object[]> perfChart=query.getResultList();		
		
	 	  for (Object[] objects : perfChart) {	 	
	 		  
	 		graphDetailDTOesb = new GraphDetailDTO();
	 	 	graphDetailDTOsap = new GraphDetailDTO();
	 		
	 		Timestamp day = (Timestamp) objects[0];
	 		if(objects[1] !=null){
	 			unit1=((BigDecimal) objects[1]).doubleValue();
	 		}else{
	 			unit1=0;
	 		}
	 		if(objects[2] !=null){
	 			unit2=((BigDecimal) objects[2]).doubleValue();
	 		} else{
	 			unit2=0;
	 		}		
	 		String dateValue= formatDate(day.getTime());
	 		categoryList.add(dateValue);
	 		dataList1.add(new Double(roundTwoDecimals(unit1)));
	 		dataList2.add(new Double(roundTwoDecimals(unit2)));	 	
	 		
	 	  }
	 	
	 	graphDetailDTOesb.setName("esbreqtime");
	 	graphDetailDTOesb.setData(dataList1);						 
		series.add(graphDetailDTOesb);
		graphDetailDTOsap.setName("sapreqtime");
		graphDetailDTOsap.setData(dataList2);						 
		series.add(graphDetailDTOsap);
	 	perfGraphDTO.setSeries(series);
	 	perfGraphDTO.setCategories(categoryList);	 	  
	 		
		return perfGraphDTO;
	}
	
	private String buildPerfGraphQueryString(String lobName, String days ){		
		LOG.info("buildPerfGraphQueryString::: called"+lobName+"<>"+days);
		int daysInt = 5;
		if(!days.equalsIgnoreCase("")){
			daysInt = new Integer(days).intValue();
		}
		
		StringBuilder sb = new StringBuilder();		
		int i =0;
		int j=0;
		while(daysInt>i){
			j=i+1;
			if(i==0){
				LOG.info("INISDE IF"+lobName);
				sb.append( " select "
						+ " as DAY,avg( timestamp(b.dbupdcreatedttm) - timestamp(a.dbupdcreatedttm))AS DATA1,avg( timestamp(c.dbupdcreatedttm) - timestamp(b.dbupdcreatedttm) ) "
						+ "AS DATA2 from OUTCOMELOGDETAIL a, OUTCOMELOGDETAIL b, OUTCOMELOGDETAIL c,outcomelogheader d  where "); 
				sb.append( " d.lobcode= '"+lobName+"'");
				sb.append( " and a.outcomehdrid=c.outcomehdrid and c.outcomehdrid =b.outcomehdrid and a.outcomehdrid =b.outcomehdrid ");
				sb.append( " and a.outcomehdrid=d.outcomehdrid and c.outcomehdrid=d.outcomehdrid and b.outcomehdrid=d.outcomehdrid ");
				sb.append( " and a.executionseq=1 and b.executionseq=2 and c.executionseq=3  ");
				sb.append( " and c.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+ j +" DAY)   AND  c.dbupdcreatedttm < CURRENT_TIMESTAMP ");
				sb.append( " and b.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+ j +" DAY)   AND  b.dbupdcreatedttm < CURRENT_TIMESTAMP ");
				sb.append( " and a.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+ j +" DAY)   AND  a.dbupdcreatedttm < CURRENT_TIMESTAMP ");
			}else{
				LOG.info("INISDE ELESE");
				sb.append( " union " );
				sb.append( " select (CURRENT_TIMESTAMP - "+ i +" DAY) as DAY,avg( timestamp(b.dbupdcreatedttm) "
						+ "- timestamp(a.dbupdcreatedttm))AS DATA1,avg( timestamp(c.dbupdcreatedttm) - timestamp(b.dbupdcreatedttm) ) AS DATA2 from OUTCOMELOGDETAIL a, OUTCOMELOGDETAIL b, OUTCOMELOGDETAIL c,outcomelogheader d  where "); 
				sb.append( " d.lobcode= '"+lobName+"'");
				sb.append( " and a.outcomehdrid=c.outcomehdrid and c.outcomehdrid =b.outcomehdrid and a.outcomehdrid =b.outcomehdrid ");
				sb.append( " and a.outcomehdrid=d.outcomehdrid and c.outcomehdrid=d.outcomehdrid and b.outcomehdrid=d.outcomehdrid ");
				sb.append( " and a.executionseq=1 and b.executionseq=2 and c.executionseq=3  ");
				sb.append( " and c.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+ j +" DAY)   AND  c.dbupdcreatedttm < (CURRENT_TIMESTAMP - "+ i+" DAY) ");
				sb.append( " and b.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+ j +" DAY)   AND  b.dbupdcreatedttm < (CURRENT_TIMESTAMP - "+ i+" DAY) ");
				sb.append( " and a.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+ j +" DAY)   AND  a.dbupdcreatedttm < (CURRENT_TIMESTAMP - "+ i+" DAY) ");
			}

			i++;
		}
		sb.append(" Order by DAY ASC");
		System.out.println("buildPerfGraphQueryString : "+sb.toString());
		return sb.toString();
		/*


		*/
	}
	private String formatDate(long input){
	    Date date = new Date(input);
	    Calendar cal = new GregorianCalendar();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM");
	    sdf.setCalendar(cal);
	    cal.setTime(date);
	    return sdf.format(date);
	
	}

	private String getLobName(String lobID){
		Query query = em.createQuery("Select o from Lob o where o.lobid=:lobid");
		int lobInt = new Integer(lobID).intValue();
		query.setParameter("lobid", lobInt);
		Lob lob=(Lob)query.getSingleResult();
		String lobName=lob.getName();
		return lobName;
	}
	private double roundTwoDecimals(double d) { 
	      DecimalFormat twoDForm = new DecimalFormat("#.##"); 
	      return Double.valueOf(twoDForm.format(d));
	}
	
	@Override
	public boolean changePassword(String userName, String currentPwd, String newPwd, String retype) 
	{
		
		//User user = em.find(User.class, Integer.parseInt(userId));
		Query query = em.createQuery("select usr from User usr where usr.username = :username");
		query.setParameter("username", userName);
		User userObject = (User)query.getSingleResult();
		
		System.out.println("User ID - >"+userObject.getUserid() +" User Name - >"+ userObject.getUsername());
		
		String pwdInDB= userObject.getPassword();
		
		//Current pwd is matching with pwd in DB 
		if (pwdInDB.equalsIgnoreCase(currentPwd))
		{
			userObject.setPassword(newPwd);
			em.merge(userObject);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean updateProfile(String userName, String emailId, String phone, String address) {
		
		//User user = em.find(User.class, Integer.parseInt(userId));
		
		User user = (User) em.createNamedQuery("User.findByUserName").setParameter("username", userName).getSingleResult();
		
		//user.setUsername(userName);
		user.setEmailid(emailId);
		user.setPhone(phone);
		user.setAddress(address);
		
		//System.out.println(" -->> "+ emailId +" "+ phone +" "+ address );
		em.merge(user);
		return true;
	}
	
	@Override
	public UserDTO getProfile(String userName) {

		// User user = em.find(User.class, Integer.parseInt(userId));
		
		User user = (User) em.createNamedQuery("User.findByUserName").setParameter("username", userName).getSingleResult();
		
		UserDTO userDTO = new UserDTO();
		
		try {			
						
			userDTO.setUserName(user.getUsername());
			userDTO.setEmail(user.getEmailid());
			if (user.getAddress() == null)
				userDTO.setAddress("");
			else
				userDTO.setAddress(user.getAddress());
			
			if (user.getPhone() == null)
				userDTO.setPhone("");
			else
				userDTO.setPhone(user.getPhone());
			

		}catch (Exception e) {
			System.out.println("DashBoardSSB.getProfile Database Exception "+ e);
			//throw e;
			e.printStackTrace();
		}
		
		return userDTO;
	}
	@Override
	public ArrayList<StoreDTO> getStoreList() {
		// TODO Auto-generated method stub
    	ArrayList<StoreDTO> storeList = null;
    	StoreDTO aStoreDTO=null;
    	storeList = new ArrayList<StoreDTO>();
    	
		Query query = em.createQuery("Select o from Store o ORDER BY o.storename");
		List<Store> storeDBList = query.getResultList();
       	 
	 	  for (Store objects : storeDBList) {
	 		  
	 		aStoreDTO = new StoreDTO();
	 		String  storeName =  ""+objects.getStorename();
	 		aStoreDTO.setText(storeName.trim());	 		
	 		aStoreDTO.setId(""+objects.getStoreid());
	 		storeList.add(aStoreDTO);
	 		//Test
	 		//System.out.println("Store Name : "+ storeName );
	 	  }
        return storeList;
	} 
	
}
