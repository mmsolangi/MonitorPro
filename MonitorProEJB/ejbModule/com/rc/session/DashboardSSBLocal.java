package com.rc.session;
import java.util.ArrayList;

import javax.ejb.Local;

import com.rc.exception.DashboardException;
import com.rc.json.dto.TransactionDTO;

@Local
public interface DashboardSSBLocal {
	public ArrayList<TransactionDTO> getHourlyChart() throws DashboardException;
}
