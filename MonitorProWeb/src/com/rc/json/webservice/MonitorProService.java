package com.rc.json.webservice;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.rc.json.transformer.MonitorProTransformer;
import com.rc.json.dto.ChartSummaryDTO;
import com.rc.json.dto.EmailDTO;
import com.rc.json.dto.LobDTO;
import com.rc.json.dto.LoginTokenDTO;
import com.rc.json.dto.MessageDTO;
import com.rc.json.dto.PageDTO;
import com.rc.json.dto.QueryDTO;
import com.rc.json.dto.StatusDTO;
import com.rc.json.dto.StoreDTO;
import com.rc.json.dto.TransactionDTO;
import com.rc.json.dto.UserDTO;
import com.rc.service.ServiceManager;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;


@Path("/WebService")
public class MonitorProService {
	

	Logger log = Logger.getLogger(MonitorProService.class);
	
	@GET
	@Path("/GetLogHeaders")
	@Produces("application/json")
	public String logHeaderFeed(@Context UriInfo info) {
		
		String logHeaderFeeds = null;
		PageDTO logHeaders = null;
		QueryDTO queryDTO = null;
		StatusDTO statusDTO = null;

		try {

			ServiceManager serviceManager = new ServiceManager();
			queryDTO = new QueryDTO();
			queryDTO.setLob(info.getQueryParameters().getFirst("lob"));
			queryDTO.setCurrpage(info.getQueryParameters().getFirst("currPage"));
			queryDTO.setPagesize(info.getQueryParameters().getFirst("pageSize"));
			queryDTO.setOrderid(info.getQueryParameters().getFirst("orderid"));
			queryDTO.setStatus(info.getQueryParameters().getFirst("status"));
			queryDTO.setFromdate(info.getQueryParameters().getFirst("fromDate"));
			queryDTO.setTodate(info.getQueryParameters().getFirst("toDate"));		
			queryDTO.setSortdir(info.getQueryParameters().getFirst("sortDir"));
			queryDTO.setSortby(info.getQueryParameters().getFirst("sortBy"));
			queryDTO.setApplicationcode(info.getQueryParameters().getFirst("store"));
			System.out.println("MonitorProService logHeaderFeed status : " + info.getQueryParameters().getFirst("status"));
			System.out.println("MonitorProService logHeaderFeed store : " + info.getQueryParameters().getFirst("store"));			
			System.out.println("MonitorProService.logHeaderFeed queryDTO - Input Data" + queryDTO.toString()); // Console
			logHeaders = serviceManager.getLogHeaders(queryDTO);
			statusDTO = new StatusDTO();
			statusDTO.setStatus("success");
			statusDTO.setLob(logHeaders);
			statusDTO.setMessage("success");
			statusDTO.setSummary(null);
			statusDTO.setTransactions(null);
			statusDTO.setMessages(null);
			
			logHeaderFeeds = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("MonitorProService.logHeaderFeeds : " + logHeaderFeeds); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService.logHeaderFeed Exception Error " + e); // Console
		}
		return logHeaderFeeds;
	}

	@GET
	@Path("/GetOverallStatus")
	@Produces("application/json")
	public String chartsFeed(@Context UriInfo info) {

		QueryDTO queryDTO = null;
		ArrayList <ChartSummaryDTO> summary=null;
		//ChartStatusDTO aChartStatusDTO = null;
		StatusDTO statusDTO = null;
		String statusChartsFeeds = null;
		String store ="";

		try {

			ServiceManager serviceManager = new ServiceManager();
			
			queryDTO = new QueryDTO();
			
			queryDTO.setFromdate(info.getQueryParameters().getFirst("fromDate"));
			queryDTO.setTodate(info.getQueryParameters().getFirst("toDate"));
			
			System.out.println("MonitorProService GetOverallStatus: STORE: " +info.getQueryParameters().getFirst("store"));
			store =info.getQueryParameters().getFirst("store");
			if( store !=null){
				store = store.trim();
			}else{
				store ="";
			}
			queryDTO.setApplicationcode(store);
			System.out.println("queryDTO" + queryDTO.toString()); // Console
			summary = serviceManager.getChartsStatus(queryDTO);
			
			statusDTO = new StatusDTO();
			statusDTO.setStatus("success");
			statusDTO.setLob(null);
			statusDTO.setMessages(null);
			statusDTO.setTransactions(null);
			statusDTO.setMessage("success");
			statusDTO.setSummary(summary);

			statusChartsFeeds = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("MonitorProService GetOverallStatus:  " + statusChartsFeeds); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService GetOverallStatus Exception Error" + e); // Console
		}

		return statusChartsFeeds;
	}

	@GET
	@Path("/GetHourlyChart")
	@Produces("application/json")
	public String hourlyChartsFeed(@Context UriInfo info) {
		ArrayList <TransactionDTO> transactions=null;		
		StatusDTO statusDTO = null;
		String hourlyChartsString = null;
		
		try {

			ServiceManager serviceManager = new ServiceManager();
			transactions = serviceManager.getHourlyChart();	
//			dashboardMgr =(DashboardSSBRemote)new InitialContext().lookup("ejb/DashboardSSB");
//			transactions = dashboardMgr.getHourlyChart();
			statusDTO = new StatusDTO();
			statusDTO.setStatus("success");
			statusDTO.setLob(null);			
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);
			statusDTO.setTransactions(transactions);
			statusDTO.setMessage("success");

			hourlyChartsString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print Hourly ChartsFeeds " + hourlyChartsString); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService GetHourlychart Exception Error" + e); // Console
		}
		
		return hourlyChartsString;
	}
	
	
	@GET
	@Path("/GetLOB")
	@Produces("application/json")
	public String getLobTabs(@Context UriInfo info) {
		ArrayList <LobDTO> lobList=null;		
		StatusDTO statusDTO = null;
		String lobTabString = null;
		
		try {

			ServiceManager serviceManager = new ServiceManager();
			lobList = serviceManager.getLobList();			
			statusDTO = new StatusDTO();
			statusDTO.setStatus("success");
			statusDTO.setLob(null);			
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);
			statusDTO.setLobTabs(lobList);
			statusDTO.setMessage("success");

			lobTabString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print lob " + lobTabString); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService GetLob Exception Error " + e); // Console
		}
		
		return lobTabString;
	
	}

	
	@GET
	@Path("/GetMessages")
	@Produces("application/json")
	public String getMessages(@Context UriInfo info) {
		ArrayList <MessageDTO> mbMessages=null;		
		StatusDTO statusDTO = null;
		String messageBoardMsgString = null;
		
		try {

			ServiceManager serviceManager = new ServiceManager();
			mbMessages = serviceManager.getMBMessages();			
			statusDTO = new StatusDTO();
			statusDTO.setStatus("success");
			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessage("success");		
			statusDTO.setMessages(mbMessages);

			messageBoardMsgString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print message board " + messageBoardMsgString); // Console
		} catch (Exception e) {
						
			System.out.println("MonitorProService GetMessages Exception Error" + e); // Console
		}
		return messageBoardMsgString;
	}
	
	@GET
	@Path("/PostMessage")
	@Produces("application/json")
	public String postMessage(@Context UriInfo info) {
		
		StatusDTO statusDTO = null;
		String postMsgString = null;
		
		UserDTO aUserDTO = null;
		
		try {

			ServiceManager serviceManager = new ServiceManager();
			
			String tokenID = info.getQueryParameters().getFirst("tokenId");
			String message=info.getQueryParameters().getFirst("message");
			
			aUserDTO = serviceManager.validateSession(tokenID);
			statusDTO = new StatusDTO();
			
//			if (aUserDTO.getStatus() != null && !aUserDTO.getStatus().equalsIgnoreCase("") && aUserDTO.getStatus().equalsIgnoreCase("valid")){
				serviceManager.postMessage(aUserDTO.getUserID(),tokenID,message);		
				statusDTO.setStatus("success");
				statusDTO.setMessage("success");
//			}else{
//				statusDTO.setStatus("error");
//				statusDTO.setMessage("Err2031");
//			}			
			
			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);					
			statusDTO.setMessages(null);

			postMsgString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print post message " + postMsgString); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService PostMessage Exception Error" + e); // Console
		}
		
		return postMsgString;
	
	}
	
	@GET
	@Path("/Login")
	@Produces("application/json")
	public String validateUser(@Context UriInfo info) {
		
		UserDTO aUserDTO = null;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		LoginTokenDTO aTokenDTO = null;
		
		statusDTO = new StatusDTO();
		try {
			
			
			aTokenDTO = new LoginTokenDTO();
			ServiceManager serviceManager = new ServiceManager();
			String userName = info.getQueryParameters().getFirst("username");
			String pwd = info.getQueryParameters().getFirst("password");
			if (userName.endsWith(" ") || pwd.endsWith(" "))
			{
				statusDTO.setStatus("error");	
				statusDTO.setData(aTokenDTO);						
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);
				
				statusDTO.setMessage("Can not use spaces in Username and Password.");				
				loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
				log.error("MonitorProService Login Exception Error__  " + loginTokenString);
				return loginTokenString;
			}
			
			aUserDTO = serviceManager.validateLogin(info.getQueryParameters().getFirst("username"),info.getQueryParameters().getFirst("password"));	
			
			if(aUserDTO.getStatus().equalsIgnoreCase("active") && aUserDTO.getStatus() !=null){
				aTokenDTO.setToken(String.valueOf(UUID.randomUUID()));
				aTokenDTO.setIsadmin(aUserDTO.getIsAdmin());
				statusDTO.setMessage("Authorized");
				serviceManager.createLogin(aUserDTO.getUserID(),aTokenDTO.getToken());
				
			} else {
				aTokenDTO.setToken("");
				statusDTO.setMessage("Un-Authorized");
			}
			
			statusDTO.setStatus("success");	
			statusDTO.setData(aTokenDTO);			

			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);			
			
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			
			//System.out.println("Print login token " + loginTokenString); // Console
			System.out.println("**************************Log4j statement Started****************");
			log.info("Login Token ->"+ loginTokenString);
			System.out.println("**************************Log4j statement executed****************"); 
			
		} catch (Exception e) {
			
			statusDTO.setStatus("error");	
			statusDTO.setData(aTokenDTO);						
			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);
			// Test commit
			statusDTO.setMessage("Invalid User");
			
			//System.out.println("MonitorProService Login Exception Error" + e); // Console
			log.error("MonitorProService Login Exception Error__  " + e);
		}
		
		return loginTokenString;
	
	}
	
	@GET
	@Path("/Logout")
	@Produces("application/json")
	public String logoutUser(@Context UriInfo info) {
		
		boolean logoutStatus = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();		
		
			ServiceManager serviceManager = new ServiceManager();
			logoutStatus = serviceManager.logoutUser(info.getQueryParameters().getFirst("token"));	
				
			statusDTO.setStatus("success");	
			statusDTO.setData(null);			
			
			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);			

			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print login token " + loginTokenString); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService Login Exception Error" + e); // Console
		}
		
		return loginTokenString;
	
	}
	@GET
	@Path("/GetEmails")
	@Produces("application/json")
	public String getEmails(@Context UriInfo info) {
		
		
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		ArrayList <EmailDTO> nfemails=null;		
		
		try {
			statusDTO = new StatusDTO();		
		
			ServiceManager serviceManager = new ServiceManager();
			nfemails = serviceManager.getEmails();	
				
			statusDTO.setStatus("success");	
			statusDTO.setData(null);			
			
			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);	
			statusDTO.setNfemails(nfemails);

			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("MonitorProService nfemails " + loginTokenString); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService Login Exception Error" + e); // Console
		}
		return loginTokenString;
	}
	
	@GET
	@Path("/GetUsers")
	@Produces("application/json")
	public String getUsers(@Context UriInfo info) {
		
		
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		ArrayList <UserDTO> users=null;		
		
		try {
			statusDTO = new StatusDTO();		
		
			ServiceManager serviceManager = new ServiceManager();
			users = serviceManager.getUsers();	
				
			statusDTO.setStatus("success");	
			statusDTO.setData(null);			
			
			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);	
			statusDTO.setNfemails(null);
			statusDTO.setUsers(users);

			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("MonitorProService getUsers " + loginTokenString); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService getUsers Exception Error" + e); // Console
		}
		
		return loginTokenString;
	
	}
	
	@GET
	@Path("/SaveNfeMail")
	@Produces("application/json")
	public String saveEmailNotification(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		try {
			statusDTO = new StatusDTO();		
			
			ServiceManager serviceManager = new ServiceManager();
			//   //data: 'tokenId=' + GetCookie("token") + "&id=" + $("#NFID").val() + "&lob=" + $("#NFLob").val() + "&event=" + $("#Event").val() + "&emails=" + $("#NFEmails").val(),
			String enId = info.getQueryParameters().getFirst("id");
			String lobString = info.getQueryParameters().getFirst("lob");
			int lobId = 0;
			if (lobString.equalsIgnoreCase("") || lobString!=null){
				lobId = Integer.parseInt(lobString);
			}
			//int id = Integer.parseInt(info.getQueryParameters().getFirst("id"));
			String event=info.getQueryParameters().getFirst("event");
			
			System.out.println("Events from DB -->> "+ event);
			
			String email=info.getQueryParameters().getFirst("emails");
			
			if (enId.equals("") == false){
				int enid = Integer.parseInt(enId);
				serviceManager.updateEmailNotification(enid, lobId, event, email);
				
				statusDTO.setStatus("success");
				statusDTO.setData(null);
	
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				
			}
			else{
				System.out.println("NfE Save Section -->> "+ event);
				serviceManager.saveEmailNotification(lobId,event,email);
				
				statusDTO.setStatus("success");	
				statusDTO.setData(null);			
	
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
			}
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("Save Notification JSON" + loginTokenString); // Console
			
		} catch (Exception e){
			System.out.println("MonitorProService saveEmailNotification Exception Error" + e); // Console
		}
		
		return loginTokenString;
	
	}
	
	@GET
	@Path("/DeleteNfeMail")
	@Produces("application/json")
	public String deleteEmailNotification(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();					
			ServiceManager serviceManager = new ServiceManager();			
			String notificationId = info.getQueryParameters().getFirst("id");
			log.info("Email Notification ID to DELETE ->"+ notificationId);
			//notificationId = "183";
			 
			boolean b = serviceManager.deleteEmailNotification(notificationId);
			
			if (b)
			{
				log.info("Email Notification Deleted from DB. Notification ID->"+ notificationId);
				statusDTO.setStatus("success");	
				statusDTO.setData(null);
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				
			}
			else
			{
				log.error("Email Notification not deleted from the table. Notification ID ->"+ notificationId);
				statusDTO.setStatus("error");	
				statusDTO.setData(null);
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				
			}

			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print login token " + loginTokenString); // Console
			
		} catch (Exception e) {
			//System.out.println("MonitorProService updateTab Exception Error" + e); // Console
			log.error("MonitorProService deleteEmailNotification Exception " + e);
		}
		
		return loginTokenString;	
	}
	
	// This method is being used for Add and Update User
	@GET
	@Path("/SaveUser")
	@Produces("application/json")
	public String saveUser(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();		
		
			ServiceManager serviceManager = new ServiceManager();
			String userId = info.getQueryParameters().getFirst("id");
			String userName = info.getQueryParameters().getFirst("name");
			String pwd =info.getQueryParameters().getFirst("password");
			String isActive =info.getQueryParameters().getFirst("status");
			String isAdmin = info.getQueryParameters().getFirst("isadmin");
			String lobid = info.getQueryParameters().getFirst("userlobs");
			String emailid = info.getQueryParameters().getFirst("email");
			//System.out.println("MonitorProService.saveUser emailid:  "+emailid);
			
			if(isAdmin==null || isAdmin.equalsIgnoreCase("")){
				isAdmin="false";
			}
			
			if (isActive.equalsIgnoreCase("true")){
				isActive = "Active";
			}
			else
				isActive = "Inactive";
			
			if (userId.equals("") == false){
				
				boolean bool = serviceManager.updateUser(userId, userName, pwd , isActive , isAdmin,lobid, emailid);
				
				if (bool)
				{
					statusDTO.setStatus("success");	
					statusDTO.setData(null);			

					statusDTO.setLob(null);
					statusDTO.setTransactions(null);
					statusDTO.setSummary(null);
					statusDTO.setMessages(null);
					
					log.info("User updated. ID:"+ userId);
				}
			}else{
				serviceManager.saveUser(userName, pwd , isActive , isAdmin,lobid, emailid);
			
				statusDTO.setStatus("success");	
				statusDTO.setData(null);			

				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			

				
				//System.out.println("Print login token " + loginTokenString); // Console
			}
			
		} catch (Exception e) {
			System.out.println("MonitorProService saveUser Exception Error" + e); // Console
		}
		
		loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
		log.info(loginTokenString);
		return loginTokenString;
	
	}
	
//	@GET
//	@Path("/UpdateUser")
//	@Produces("application/json")
//	public String updateUser(@Context UriInfo info) {
//		
//		boolean status = false;
//		StatusDTO statusDTO = null;
//		String loginTokenString = null;
//		
//		
//		try {
//			statusDTO = new StatusDTO();		
//		
//			ServiceManager serviceManager = new ServiceManager();
//			
//			String userId = info.getQueryParameters().getFirst("userId");
//			String userName = info.getQueryParameters().getFirst("name");
//			String pwd =info.getQueryParameters().getFirst("password");
//			String isActive =info.getQueryParameters().getFirst("status");
//			String isAdmin = info.getQueryParameters().getFirst("isadmin");
//			String lobid = info.getQueryParameters().getFirst("userlobs");
//			String emailid = info.getQueryParameters().getFirst("email");
//			//System.out.println("MonitorProService.saveUser emailid: "+emailid);
//			if(isAdmin==null || isAdmin.equalsIgnoreCase("")){
//				isAdmin="false";
//			}
//			if(isActive==null || isActive.equalsIgnoreCase("")){
//				isActive="Active";
//			}
//			serviceManager.updateUser(userId, userName, pwd , isActive , isAdmin,lobid, emailid);
//			
//			statusDTO.setStatus("success");	
//			statusDTO.setData(null);
//			statusDTO.setLob(null);
//			statusDTO.setTransactions(null);
//			statusDTO.setSummary(null);
//			statusDTO.setMessages(null);			
//
//			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
//			//System.out.println("Print login token " + loginTokenString); // Console
//			
//		} catch (Exception e) {
//			System.out.println("MonitorProService updateUser Exception Error" + e); // Console
//			log.error("MonitorProService updateUser Exception Error" + e);
//		}
//		
//		return loginTokenString;
//	
//	}
	
	@GET
	@Path("/DeleteUser")
	@Produces("application/json")
	public String deleteUser(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		try {
			statusDTO = new StatusDTO();					
			ServiceManager serviceManager = new ServiceManager();			
			String userId = info.getQueryParameters().getFirst("id");
			
			//userId = "183";
			 
			boolean b = serviceManager.deleteUser(userId);
			log.info("UserID "+ userId);
			if (b)
			{
				log.info("User Deleted from DB.");
				statusDTO.setStatus("success");	
				statusDTO.setData(null);
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				
			}
			else
			{
				log.error("User information not deleted from User table.");
				statusDTO.setStatus("error");	
				statusDTO.setData(null);
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				
			}
			
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print login token " + loginTokenString); // Console
			
		} catch (Exception e) {
			//System.out.println("MonitorProService updateTab Exception Error" + e); // Console
			log.error("MonitorProService DeleteUser Exception" + e);
		}
		
		return loginTokenString;	
	}
	
	@GET
	@Path("/SaveTab")
	@Produces("application/json")
	public String saveTab(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();		
		
			ServiceManager serviceManager = new ServiceManager();
			String tabId = info.getQueryParameters().getFirst("id");
			System.out.println("Tab ID -->> "+ tabId);
			String tabName = info.getQueryParameters().getFirst("name");
			String isActive = info.getQueryParameters().getFirst("status");
			//String isActive ="Active";	
			if (isActive.equalsIgnoreCase("true")){
				isActive = "Active";
			}
			else
				isActive = "Inactive";
			
			System.out.println("Tab ID for UPDATE -->> "+ tabId);
			
			if (tabId.equals("") == false){
				
				boolean bool = serviceManager.updateTab(tabId, tabName, isActive);
				
				if (bool)
				{
					statusDTO.setStatus("success");	
					statusDTO.setData(null);			

					statusDTO.setLob(null);
					statusDTO.setTransactions(null);
					statusDTO.setSummary(null);
					statusDTO.setMessages(null);
					
					log.info("Tab updated. ID:"+ tabId);
				}
			}else{
				
				boolean b= serviceManager.saveTab(tabName, isActive);
				System.out.println("-------------- ELSE CODE ---------------"); // Console
				//Tab name already present
				if (b == false){
					
					statusDTO.setStatus("duplicate");
					statusDTO.setData(null);			

					statusDTO.setLob(null);
					statusDTO.setTransactions(null);
					statusDTO.setSummary(null);
					statusDTO.setMessages(null);
					log.info("Tab name is already present.");
				}else{
					statusDTO.setStatus("success");	
					statusDTO.setData(null);			

					statusDTO.setLob(null);
					statusDTO.setTransactions(null);
					statusDTO.setSummary(null);
					statusDTO.setMessages(null);					
				}
			}
			
			
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("Print login token " + loginTokenString); // Console
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MonitorProService saveTab Exception Error" + e); // Console
			log.error("MonitorProService saveTab Exception "+ e);
		}
		
		return loginTokenString;	
	}	
	
	/*@GET
	@Path("/UpdateTab")
	@Produces("application/json")
	//Tested
	public String updateTab(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();		
			
			ServiceManager serviceManager = new ServiceManager();
			
			String tabId = info.getQueryParameters().getFirst("tabid");
			String tabName = info.getQueryParameters().getFirst("username");
			String tabStatus = info.getQueryParameters().getFirst("status");
			
			tabId = "2171";
			tabName="FlightTesting";
			tabStatus="InActive";
			//String isActive ="Active";	
			 
			boolean b = serviceManager.updateTab(tabId, tabName, tabStatus);
			
			if (b){
				log.info("Tab Updated into the DB.");
				statusDTO.setStatus("success");	
				statusDTO.setData(null);			

				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);
				statusDTO.setMessage("Tab Updated");
				
			}else {
				statusDTO.setStatus("error");	
				statusDTO.setData(null);			

				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				statusDTO.setMessage("Tab bot updated.");
				log.error("Tab information not updated in LOB table.");
			}
			//System.out.println("Print login token " + loginTokenString); // Console
			
		} catch (Exception e) {
			//System.out.println("MonitorProService updateTab Exception Error" + e); // Console
			log.error("MonitorProService updateTab Exception Error" + e);
		}
		
		return loginTokenString;	
	}	
*/	
	
	@GET
	@Path("/DeleteTab")
	@Produces("application/json")
	public String deleteTab(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();					
			ServiceManager serviceManager = new ServiceManager();			
			String tabId = info.getQueryParameters().getFirst("id");
			
			//tabId = "2172";
			
			boolean b = serviceManager.deleteTab(tabId);
			
			if (b){
				statusDTO.setStatus("success");	
				statusDTO.setData(null);			

				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				statusDTO.setMessage("Tab deleted");
				log.info("Tab Deleted from DB. tabID"+ tabId);
				
			}else {
				
				statusDTO.setStatus("error");	
				statusDTO.setData(null);			
				
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				statusDTO.setMessage("Tab can not be deleted. TabID"+ tabId);
				
				log.error("Tab information not deleted from LOB table.");
			}
			
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print login token " + loginTokenString); // Console
			
		} catch (Exception e) {
			//System.out.println("MonitorProService updateTab Exception Error" + e); // Console
			log.error("MonitorProService deleteTab Exception Error" + e);
		}
		
		return loginTokenString;	
	}		

	@GET
	@Path("/GetPerfGraph")
	@Produces("application/json")
	public String getPerfGraph(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();		
		
			ServiceManager serviceManager = new ServiceManager();
			
			String days = "5";
			days=info.getQueryParameters().getFirst("days");
			String lobName ="Order";	
			lobName = info.getQueryParameters().getFirst("name");
			 
			statusDTO.setPerformance(serviceManager.getPerfGraph(lobName, days));
			
			statusDTO.setStatus("success");	
			statusDTO.setData(null);			

			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);			

			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("Print getPerfGraph :  " + loginTokenString); // Console
			
		} catch (Exception e) {
			System.out.println("MonitorProService saveTab Exception Error" + e); // Console
		}
		
		return loginTokenString;
	
	}	
	
	@GET
	@Path("/ChangePassword")
	@Produces("application/json")
	public String changePassword(@Context UriInfo info) {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		
		try {
			statusDTO = new StatusDTO();		
			
			ServiceManager serviceManager = new ServiceManager();
			String userName = info.getQueryParameters().getFirst("name");
			String currentPwd = info.getQueryParameters().getFirst("currentpwd");
			String newPwd = info.getQueryParameters().getFirst("newpwd");
			String retypePwd = info.getQueryParameters().getFirst("retype");
			
			log.info("CHANGE PROFILE + Parameter -> User Name" + userName +" Current Password "+ currentPwd +" Retype"+ retypePwd);
			
//			userName = "TariqKhan";
//			currentPwd = "tariq{}";
//			newPwd="tariq[12]";
//			retypePwd="tariq[12]";
			
			if (newPwd.equalsIgnoreCase(retypePwd)){
				
				status = serviceManager.changePwd(userName, currentPwd, newPwd, retypePwd);	
				
				if (!status){
					
					statusDTO.setStatus("error");	
					statusDTO.setData(null);			
					
					statusDTO.setLob(null);
					statusDTO.setTransactions(null);
					statusDTO.setSummary(null);
					statusDTO.setMessages(null);			
					log.error("Password mismatch in Change Password");
					
				}else{
					
					statusDTO.setStatus("success");	
					statusDTO.setData(null);			
					
					statusDTO.setLob(null);
					statusDTO.setTransactions(null);
					statusDTO.setSummary(null);
					statusDTO.setMessages(null);			
					
					log.info("Password changed successfully");

				}
			}
			else{
				statusDTO.setStatus("error");	
				statusDTO.setData(null);			
				
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				log.error("Current password and Retype password doesn't match");				
			}
			
			
			
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			//System.out.println("Print login token " + loginTokenString); // Console
		} catch (Exception e) {
			System.out.println("MonitorProService Change Password Exception " + e); // Console
			e.printStackTrace();
		}
		
		return loginTokenString;
		
	}	

	@GET
	@Path("/GetProfile")
	@Produces("application/json")
	public String getProfile(@Context UriInfo info){

		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		UserDTO userDTO = null;
		
		try {
			
			statusDTO = new StatusDTO();		
			
			ServiceManager serviceManager = new ServiceManager();
			String userName = info.getQueryParameters().getFirst("username");
			
			log.info("GET PROFILE + Parameter -> User Name" + userName);
			
//			userId = "160";
//			userName = "TariqKhan";
//			email="tariq@tariqkhan.com";
//			phone="0333-1234567";
//			address="Karachi . Pakistan";
			
			userDTO = serviceManager.getProfile(userName);			
			
			statusDTO.setStatus("success");
			statusDTO.setData(null);			
			
			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);
			statusDTO.setUser(userDTO);
			
//			if (!status){
//				
//				statusDTO.setStatus("error");
//				statusDTO.setData(null);			
//				
//				statusDTO.setLob(null);
//				statusDTO.setTransactions(null);
//				statusDTO.setSummary(null);
//				statusDTO.setMessages(null);			
//				log.error("Profile not updated.Please check the log file");
//				
//			}else{
//				
//				statusDTO.setStatus("success");	
//				statusDTO.setData(null);			
//				
//				statusDTO.setLob(null);
//				statusDTO.setTransactions(null);
//				statusDTO.setSummary(null);
//				statusDTO.setMessages(null);			
//				
//				log.info("Profile updated successfully.");
//				
//			}
			
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("User Data for PROFILE - > " + loginTokenString); 
			
		} catch (Exception e) {
			System.out.println("MonitorProService Get Profile Exception" + e); 
		}
		
		return loginTokenString;		
		
	}
	
	@GET
	@Path("/UpdateProfile")
	@Produces("application/json")
	public String updateProfile(@Context UriInfo info){

		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		
		try {
			statusDTO = new StatusDTO();		
			
			ServiceManager serviceManager = new ServiceManager();
			//String userId = info.getQueryParameters().getFirst("id");
			String userName = info.getQueryParameters().getFirst("username");
			String email= info.getQueryParameters().getFirst("email");
			String phone = info.getQueryParameters().getFirst("phone");
			String address = info.getQueryParameters().getFirst("address");
			
			log.info("UPDATE PROFILE - Parameter -> User Name" + userName);
			
//			userId = "160";
//			userName = "tariqnew";
//			email="new@tariqkhan.com";
//			phone="0333-0000000";
//			address="Karachi . Sindh . Pakistan";
			
			status = serviceManager.updateProfile(userName, email, phone, address);
			
			if (!status){
				
				statusDTO.setStatus("error");	
				statusDTO.setData(null);			
				
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				log.error("Profile not updated.Please check the log file");
				System.out.println("Profile not updated.Please check the log file");
			}else{
				
				statusDTO.setStatus("success");	
				statusDTO.setData(null);			
				
				statusDTO.setLob(null);
				statusDTO.setTransactions(null);
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);			
				
				log.info("Profile updated successfully.");
				System.out.println("Profile updated successfully.");
			}
			
			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("PROFILE JSON Format - > " + loginTokenString); 
			
		} catch (Exception e) {
			
			System.out.println("MonitorProService Login Exception Error" + e); // Console
		}
		
		return loginTokenString;		
		
	}
	 @GET
	    @Path(value="/GetActiveLOB")
	    @Produces(value={"application/json"})
	    public String getActiveLobTabs(@Context UriInfo info)
	    {
		 ArrayList <LobDTO> lobList=null;
	        StatusDTO statusDTO = null;
	        String lobTabString = null;
	        try
	        {
	            ServiceManager serviceManager = new ServiceManager();
	            lobList = serviceManager.getActiveLobList();
	            statusDTO = new StatusDTO();
	            statusDTO.setStatus("success");
	            statusDTO.setLob(null);
	            statusDTO.setSummary(null);
	            statusDTO.setMessages(null);
	            statusDTO.setLobTabs(lobList);
	            statusDTO.setMessage("success");
	            lobTabString = MonitorProTransformer.ConvertToJSONString(statusDTO);
	        }
	        catch(Exception e)
	        {
	            System.out.println((new StringBuilder("MonitorProService GetLob Exception Error ")).append(e).toString());
	        }
	        return lobTabString;
	    }
	 @GET
		@Path("/GetActiveStores")
		@Produces("application/json")
		public String getActiveStores(@Context UriInfo info) {
			ArrayList <StoreDTO> stores=null;		
			StatusDTO statusDTO = null;
			String storeString = null;
			
			try {

				ServiceManager serviceManager = new ServiceManager();
				stores = serviceManager.getStoreList();			
				statusDTO = new StatusDTO();
				statusDTO.setStatus("success");
				statusDTO.setLob(null);			
				statusDTO.setSummary(null);
				statusDTO.setMessages(null);
				statusDTO.setLobTabs(null);
				statusDTO.setStores(stores);
				statusDTO.setMessage("success");

				storeString = MonitorProTransformer.ConvertToJSONString(statusDTO);
				System.out.println("Print Stores " + storeString); // Console
			} catch (Exception e) {
				System.out.println("MonitorProService GetLob Exception Error " + e); // Console
			}
			
			return storeString;
		
		}

}
