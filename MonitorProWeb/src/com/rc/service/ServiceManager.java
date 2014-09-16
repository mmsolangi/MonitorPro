package com.rc.service;

import java.util.ArrayList;

import com.rc.json.dto.ChartSummaryDTO;
import com.rc.json.dto.EmailDTO;
import com.rc.json.dto.LobDTO;
import com.rc.json.dto.MessageDTO;
import com.rc.json.dto.PageDTO;
import com.rc.json.dto.PerfGraphDTO;
import com.rc.json.dto.QueryDTO;
import com.rc.json.dto.StoreDTO;
import com.rc.json.dto.TransactionDTO;
import com.rc.json.dto.UserDTO;
import com.rc.session.DashboardSSBRemote;

public class ServiceManager {

	DashboardSSBRemote dashboardMgr=null;
	
	public PageDTO getLogHeaders(QueryDTO queryDTO) throws Exception {
		PageDTO logHeaders = null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			logHeaders = dashboardMgr.getLogHeaders(queryDTO);
		} catch (Exception e) {
			throw e;
		}
		return logHeaders;
	}

	public ArrayList<ChartSummaryDTO> getChartsStatus(QueryDTO queryDTO)throws Exception {
		ArrayList<ChartSummaryDTO> summary= null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}	
			summary = dashboardMgr.getChartSummary(queryDTO);
		} catch (Exception e) {
			throw e;
		}
		return summary;

	}

	public ArrayList<TransactionDTO> getHourlyChart() throws Exception{
		ArrayList<TransactionDTO> transactions= null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}	
			transactions = dashboardMgr.getHourlyChart();
		} catch (Exception e) {
			throw e;
		}
		return transactions;
	}

	public ArrayList<MessageDTO> getMBMessages() throws Exception{
		ArrayList<MessageDTO> mbMessages= null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}	
			mbMessages = dashboardMgr.getMBMessages();
		} catch (Exception e) {
			throw e;
		}
		return mbMessages;
	}

	public UserDTO validateLogin(String user, String password) throws Exception{
		UserDTO aUserDTO = null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			aUserDTO = dashboardMgr.validateLogin( user, password);
		} catch (Exception e) {
			throw e;
		}
		return aUserDTO;
	}

	public boolean postMessage(int userID,String tokenId, String messageDesc)throws Exception {
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.postMessage(userID, tokenId, messageDesc);
		} catch (Exception e) {
			throw e;
		}
		return isSuccess;
	}

	public void createLogin(int userID, String token) throws Exception{
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			dashboardMgr.createLogin( userID, token);
		} catch (Exception e) {
			throw e;
		}
		
	}

	public UserDTO validateSession(String tokenID) throws Exception{
		UserDTO aUserDTO = null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			aUserDTO=dashboardMgr.validateSession( tokenID);
		} catch (Exception e) {
			throw e;
		}
		return aUserDTO;
	}

	public ArrayList<LobDTO> getLobList() throws Exception{
		
		ArrayList<LobDTO> lobList= null;
		
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			lobList=dashboardMgr.getLobList();
		} catch (Exception e) {
			throw e;
		}
		return lobList;
	}

	public boolean logoutUser(String token) throws Exception{
		boolean logoutStatus=false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			logoutStatus=dashboardMgr.logoutUser(token);
		} catch (Exception e) {
			throw e;
		}
		return logoutStatus;
	}

	public ArrayList<EmailDTO> getEmails() throws Exception{
		ArrayList<EmailDTO> nfemails= null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}	
			nfemails = dashboardMgr.getNFEMails();
		} catch (Exception e) {
			throw e;
		}
		return nfemails;
	}

	public ArrayList<UserDTO> getUsers()throws Exception {
		ArrayList<UserDTO> users= null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}	
			users = dashboardMgr.getUsers();
		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	public void saveEmailNotification(int lobId, String event, String email)throws Exception  {
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.saveEmailNotification(lobId, event, email);
		} catch (Exception e) {
			throw e;
		}
	}

	public void updateEmailNotification(int enid, int lobId, String event, String email)throws Exception  {
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.saveEmailNotification(lobId, event, email);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean deleteEmailNotification(String notificationId) throws Exception{
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.deleteEmailNotification(notificationId);
		} catch (Exception e) {
			throw e;
		}
		return isSuccess;
	}
	
	public void saveUser(String userName, String pwd, String isActive,String isAdmin, String lobid, String emailid) throws Exception{
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.saveUser(userName, pwd, isActive,isAdmin,lobid,emailid);
		} catch (Exception e) {
			throw e;
		}
		
	}

	public boolean updateUser(String userId, String userName, String pwd, String isActive,String isAdmin, String lobid, String emailid) throws Exception{
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.updateUser(userId, userName, pwd, isActive, isAdmin, lobid, emailid);
		} catch (Exception e) {
			throw e;
		}
		return isSuccess;
	}
	
	public boolean deleteUser(String userId) throws Exception{
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.deleteUser(userId);
		} catch (Exception e) {
			throw e;
		}
		return isSuccess;
	}
	
	public boolean saveTab(String tabName, String isActive)throws Exception {
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.saveTab(tabName,isActive);
		} catch (Exception e) {
			throw e;
		}
		return isSuccess;
	}

	public boolean updateTab(String tabId, String tabName, String tabStatus)throws Exception {
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.updateTab(tabId, tabName, tabStatus);
		} catch (Exception e) {
			throw e;
		}
		return isSuccess;
	}
	
	public boolean deleteTab(String tabId)throws Exception {
		boolean isSuccess = false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			isSuccess = dashboardMgr.deleteTab(tabId);
		} catch (Exception e) {
			throw e;
		}
		return isSuccess;
	}
	
	public PerfGraphDTO getPerfGraph(String tabName, String days)throws Exception {
		PerfGraphDTO perfGraphDTO=null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
//			perfGraphDTO = dashboardMgr.getPerfGraph(tabName,days);
		} catch (Exception e) {
			throw e;
		}
		return perfGraphDTO;
	}

	public boolean changePwd(String userName, String currentPwd, String newPwd, String retype) throws Exception{
		boolean changePwdStatus=false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			changePwdStatus=dashboardMgr.changePassword(userName, currentPwd, newPwd, retype);
		} catch (Exception e) {
			throw e;
		}
		return changePwdStatus;
	}
	
	public boolean updateProfile(String userName, String emailId, String phone, String address) throws Exception{
		boolean changePwdStatus=false;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			changePwdStatus=dashboardMgr.updateProfile(userName, emailId, phone, address);
		} catch (Exception e) {
			throw e;
		}
		return changePwdStatus;
	}

	public UserDTO getProfile(String userName) throws Exception{
		UserDTO userDTO=null;
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			userDTO=dashboardMgr.getProfile(userName);
		} catch (Exception e) {
			throw e;
		}
		return userDTO;
	}

	public ArrayList<LobDTO> getActiveLobList() throws Exception{
		
		ArrayList<LobDTO> lobList= null;
		
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			lobList=dashboardMgr.getLobList();
		} catch (Exception e) {
			throw e;
		}
		return lobList;
	}

	public ArrayList<StoreDTO> getStoreList()throws Exception{
		ArrayList<StoreDTO> storeList= null;
		
		try {
			if(dashboardMgr == null){
				dashboardMgr =(DashboardSSBRemote)ServiceLocator.getInstance().getResources("java:global/MonitorPro/MonitorProEJB/DashboardSSB!com.rc.session.DashboardSSBRemote");
			}
			storeList=dashboardMgr.getStoreList();
		} catch (Exception e) {
			throw e;
		}
		return storeList;
	}
	
}