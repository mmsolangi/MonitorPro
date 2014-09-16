package com.rc.session;
import java.util.ArrayList;

import javax.ejb.Remote;

import com.rc.exception.DashboardException;
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

@Remote
public interface DashboardSSBRemote {
	public ArrayList<TransactionDTO> getHourlyChart() throws DashboardException;

	public PageDTO getLogHeaders(QueryDTO queryDTO);

	public ArrayList<ChartSummaryDTO> getChartSummary(QueryDTO queryDTO);

	public ArrayList<MessageDTO> getMBMessages();

	public UserDTO validateLogin(String user, String password);

	public boolean postMessage(int userID, String tokenId, String messageDesc);

	public void createLogin(int userID, String token);

	public ArrayList<LobDTO> getLobList();

	public UserDTO validateSession(String tokenID);

	public boolean logoutUser(String token);

	public ArrayList<EmailDTO> getNFEMails();

	public ArrayList<UserDTO> getUsers();

	public boolean saveEmailNotification(int lobId, String event, String email);
	
	public boolean updateEmailNotification(int enid, int lobId, String event, String email);	

	public boolean deleteEmailNotification(String userId);
	
	public boolean saveUser(String userName, String pwd, String isActive,String isAdmin, String lobid, String emailid);
	
	public boolean updateUser(String userId, String userName, String pwd, String isActive,String isAdmin, String lobid, String emailid);

	public boolean deleteUser(String userId);

	public boolean saveTab(String tabName, String isActive);
	
	public boolean updateTab(String tabid, String tabName, String tabStatus);
	
	public boolean deleteTab(String tabid);

	public PerfGraphDTO getPerfGraph(String tabName, String days);
	
	public boolean changePassword(String userName, String currentPwd, String newPwd, String retype);
	
	public boolean updateProfile(String userName, String emailId, String phone, String address);
	
	public UserDTO getProfile(String userName);

	public ArrayList<LobDTO> getActiveLobList();

	public ArrayList<StoreDTO> getStoreList(); 
}
