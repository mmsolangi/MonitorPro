package com.rc.sessoin;

import java.sql.SQLException;

import javax.ejb.Local;

@Local
public interface DataloadSSBLocal {
	public void loaddata(com.rc.json.dto.LogHeader logheader) throws SQLException;;
}
