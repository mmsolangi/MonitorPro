package com.rc.sessoin;

import java.sql.SQLException;

import javax.ejb.Remote;

@Remote
public interface DataloadSSBRemote {
	 public void loaddata(com.rc.json.dto.LogHeader logheader) throws SQLException;

}
