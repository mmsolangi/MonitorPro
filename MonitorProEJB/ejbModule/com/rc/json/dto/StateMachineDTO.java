package com.rc.json.dto;

import java.util.Calendar;
import java.util.List;

// Referenced classes of package com.rc.json.dto:
//            BizObjectStateDTO, LobDTO

public class StateMachineDTO {

	public StateMachineDTO() {
	}

	String status;
	BizObjectStateDTO currentState;
	LobDTO businessObject;
	String correlationId;
	Calendar time;
	List allStates;
}
