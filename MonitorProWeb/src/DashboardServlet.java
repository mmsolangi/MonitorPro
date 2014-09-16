
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriInfo;

import com.rc.exception.DashboardException;
import com.rc.json.dto.ChartDetailDTO;
import com.rc.json.dto.ChartSummaryDTO;
import com.rc.json.dto.LobDTO;
import com.rc.json.dto.MessageDTO;
import com.rc.json.dto.PageDTO;
import com.rc.json.dto.PerfGraphDTO;
import com.rc.json.dto.QueryDTO;
import com.rc.json.dto.StatusDTO;
import com.rc.json.dto.TransactionDTO;
import com.rc.json.transformer.MonitorProTransformer;
import com.rc.service.ServiceManager;
import com.rc.session.DashboardSSBRemote;

/**
 * Servlet implementation class DashboardServlet
 */
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EJB
	DashboardSSBRemote dashboardMgr = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doWork(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doWork(request, response);
	}

	private void doWork(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();

			out.println("<br>Getting hourly chart ...");
			getChartGraph();

		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
public String getChartGraph() {
	StatusDTO statusDTO = null;
	DashboardSSBRemote dashboardMgr = loadEJB();
	QueryDTO queryDTO = new QueryDTO();
	
	String loginTokenString = null;
		
	try {
		ArrayList<ChartSummaryDTO> chartSummary =dashboardMgr.getChartSummary(queryDTO);	
		statusDTO = new StatusDTO();		 
		statusDTO.setPerformance(null);		
		statusDTO.setStatus("success");	
		statusDTO.setData(null);		
		statusDTO.setLob(null);
		statusDTO.setTransactions(null);
		statusDTO.setSummary(null);
		statusDTO.setMessages(null);		
		statusDTO.setSummary(chartSummary);
	} catch (Exception e) {
		System.out.println("MonitorProService saveTab Exception Error" + e); // Console
	}
	

		loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
		System.out.println("Print login token " + loginTokenString); // Console
		return loginTokenString;
}
	
	
public String getPerfGraph() {
		
		boolean status = false;
		StatusDTO statusDTO = null;
		String loginTokenString = null;
		DashboardSSBRemote dashboardMgr = loadEJB();
		PerfGraphDTO perfGraphDTO=dashboardMgr.getPerfGraph("2010", "5");
		
		try {
			statusDTO = new StatusDTO();		
		
//			ServiceManager serviceManager = new ServiceManager();
//			
//			String days = "5";
//			days=info.getQueryParameters().getFirst("days");
//			String lobName ="Order";	
//			lobName = info.getQueryParameters().getFirst("name");
			 
			statusDTO.setPerformance(perfGraphDTO);
			
			statusDTO.setStatus("success");	
			statusDTO.setData(null);			

			statusDTO.setLob(null);
			statusDTO.setTransactions(null);
			statusDTO.setSummary(null);
			statusDTO.setMessages(null);			

			loginTokenString = MonitorProTransformer.ConvertToJSONString(statusDTO);
			System.out.println("Print login token " + loginTokenString); // Console
			
		} catch (Exception e) {
			System.out.println("MonitorProService saveTab Exception Error" + e); // Console
		}
		
		return loginTokenString;
	
	}	
	private void gethourlyChart( PrintWriter out) throws DashboardException			 {
		
		
		
		//dashboardMgr.createLogin(101, String.valueOf(UUID.randomUUID()));
		
		/*ArrayList<TransactionDTO> hourlyCharts = dashboardMgr.getHourlyChart();
		
		for (TransactionDTO aTransactionDTO : hourlyCharts) {
			out.println("<br>****");
			out.println("<br>Hour -> " + aTransactionDTO.getHour());
			out.println("<br>NoOfTransactions -> " + aTransactionDTO.getNoOfTransactions());
			
		}
		
		ArrayList<LobDTO> lobs = dashboardMgr.getLobList();
		
		for (LobDTO aLobDTO : lobs) {
			out.println("<br>****");
			out.println("<br>LOB NAME -> " + aLobDTO.getName());
						
		}
		
		ArrayList<MessageDTO> messages = dashboardMgr.getMBMessages();
		for (MessageDTO aMessageDTO : messages) {
			out.println("<br>****");
			out.println("<br>LOB NAME -> " + aMessageDTO.getMessage());	
				
		}
		QueryDTO queryDTO = new QueryDTO();
		
		PageDTO logheaders =dashboardMgr.getLogHeaders(queryDTO);
		*/
	}
	private DashboardSSBRemote loadEJB(){
		
		Context initialContext = null;  
		DashboardSSBRemote dashboardMgr = null;
		  
        try {  
            Hashtable env = new Hashtable();    
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");    
            env.put(Context.PROVIDER_URL, "iiop://localhost:2809"); 
            System.out.println("Build the context");  
            initialContext = new InitialContext();  
            System.out.println("Fetch out object from the context");              
            dashboardMgr = (DashboardSSBRemote) initialContext.lookup("ejb/DashboardSSB");    
              
        } catch (NamingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
		return dashboardMgr;  
        
	}
	

}
