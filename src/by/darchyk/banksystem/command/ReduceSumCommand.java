package by.darchyk.banksystem.command;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.resource.MessageManager;
import by.darchyk.banksystem.service.ClientService;
import by.darchyk.banksystem.service.DepositService;
import by.darchyk.banksystem.service.HistoryService;

public class ReduceSumCommand implements ActionCommand {
	private static final String SUM = "sum";
	private static final String NUMBER = "number";
	private static final String LOGIN = "login";
	private static final String ERROR_MESSAGE = "errorMessage";
	private final static String EXP_SUM = "^[0-9]{4,7}$";
	
	private boolean isAuditorSum(String sum) {
		return !(sum != null && sum.matches(EXP_SUM));
	}

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int sum = 0;
		java.util.Date d = new java.util.Date();
        Date dataTime = new java.sql.Date(d.getTime());
		DepositService service = new DepositService();
		HistoryService historyService = new HistoryService();
		
		if(isAuditorSum(request.getParameter(SUM))){
			DepositService depositService = new DepositService();
			ClientService clientService = new ClientService();
			int idClient = clientService.getID((String) request.getSession().getAttribute(LOGIN));
			List<Integer> depositNumbers = depositService.getDepositNumberForClient(idClient);
			request.setAttribute("list", depositNumbers);
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.sumerror"));
			return page = ConfigurationManager.getProperty("path.page.reduce_deposit");
		}
		
		sum = service.getSum(Integer.parseInt(request.getParameter(NUMBER)));
		int currentSum = sum - Integer.parseInt(request.getParameter(SUM));
		if (currentSum == 0) {
			service.deleteDeposit(Integer.parseInt(request.getParameter(NUMBER)));
		} else {
			service.changeSum(currentSum, Integer.parseInt(request.getParameter(NUMBER)));
		}
		String action = dataTime+" со счета снято "+Integer.parseInt(request.getParameter(SUM));
		historyService.addDeposit(Integer.parseInt(request.getParameter(NUMBER)), action);
		MyDepositsCommand getTable = new MyDepositsCommand();
		page = getTable.execute(request);

		return page;
	}

}
