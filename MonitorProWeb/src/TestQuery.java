import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.rc.json.dto.ChartDetailDTO;
import com.rc.json.dto.ChartSummaryDTO;
import com.rc.json.dto.QueryDTO;


public class TestQuery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestQuery testQuery = new TestQuery();
		//testQuery.buildPerfGraphQueryString("Inventory", "3");
		testQuery.buildChartQuery();
	}
	
	
	private String buildChartQuery(){
		StringBuilder sb = new StringBuilder();	
		sb.append("select 'Inventory' as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='Inventory'   AND  CLIENTREQUESTDATETIME > (CURRENT_TIMESTAMP - 2 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
		sb.append( " union " );
		sb.append("select 'Order' as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='Order'   AND  CLIENTREQUESTDATETIME > (CURRENT_TIMESTAMP - 2 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
		sb.append( " union " );
		sb.append("select 'Catalog' as LOB, count(case when OUTCOMESTATUS ='Success' then 1 else null end) as Success, count(case when OUTCOMESTATUS ='Errors' then 1 else null end) as Errors, count(case when OUTCOMESTATUS ='Warnings' then 1 else null end) as Warnings, count(case when OUTCOMESTATUS ='Critical Errors' then 1 else null end) as CriricalErrors from OUTCOMELOGHEADER where LOBCODE='Catalog'   AND  CLIENTREQUESTDATETIME > (CURRENT_TIMESTAMP - 2 DAY)   AND  CLIENTREQUESTDATETIME < CURRENT_TIMESTAMP");
		
		System.out.println("buildChartQuery : "+sb.toString());
		return sb.toString();
	}
private String buildPerfGraphQueryString(String lobName, String days ){		
		
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
				sb.append("select CURRENT_TIMESTAMP as DAY, timestamp(b.dbupdcreatedttm) AS DATA1 , timestamp(a.dbupdcreatedttm) AS DATA2 from OUTCOMELOGDETAIL a, OUTCOMELOGDETAIL b,outcomelogheader c  where a.outcomehdrid=c.outcomehdrid and a.executionseq=1 and b.outcomehdrid =a.outcomehdrid  and b.executionseq=2 and b.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+ j+" DAY)   AND  b.dbupdcreatedttm < CURRENT_TIMESTAMP and c.lobcode= '"+lobName+"'  ");
			}else{
				sb.append( " union " );
				sb.append("select (CURRENT_TIMESTAMP - "+ i+" DAY)AS DAY, timestamp(b.dbupdcreatedttm) AS DATA1 , timestamp(a.dbupdcreatedttm) AS DATA2 from OUTCOMELOGDETAIL a, OUTCOMELOGDETAIL b,outcomelogheader c  where a.outcomehdrid=c.outcomehdrid and a.executionseq=1 and b.outcomehdrid =a.outcomehdrid  and b.executionseq=2 and b.dbupdcreatedttm > (CURRENT_TIMESTAMP - "+j+" DAY)   AND  b.dbupdcreatedttm < (CURRENT_TIMESTAMP - "+ i+" DAY) and c.lobcode= '"+lobName+"' ");
			
			}
			i++;
		}
		System.out.println("buildPerfGraphQueryString : "+sb.toString());
		return sb.toString();
	}


}
