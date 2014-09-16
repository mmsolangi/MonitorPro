package com.rc.service;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.rc.json.dto.LogHeader;
import com.rc.sessoin.DataloadSSBRemote;
public class ServiceManager {

	DataloadSSBRemote dashboardMgr=null;
	private static final Logger LOG = Logger.getLogger(ServiceManager.class.getName());
	public void loaddata(LogHeader logHeader){
		try{
			dashboardMgr =(DataloadSSBRemote)ServiceLocator.getInstance()
					.getResources("java:global/DLJWebServiceEAR/DLJWebServiceEJB/DataloadSSB!com.rc.sessoin.DataloadSSBRemote");
		}catch(Exception e){
			System.out.println("An exception occured while accessing EJB"+e.getMessage());
			e.printStackTrace();
		}
	try {
		dashboardMgr.loaddata(logHeader);
	} catch (SQLException e) {
		e.printStackTrace();
		LOG.info("Exception occured while persisting" +e.getMessage());
	}
	}
	
}