package by.darchyk.banksystem.command;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.entity.Deposit;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.resource.MessageManager;
import by.darchyk.banksystem.service.ClientService;
import by.darchyk.banksystem.service.DepositService;
import by.darchyk.banksystem.service.HistoryService;

public class AddDepositCommand implements ActionCommand {

	private static final String SUM = "sum";
	private static final String TERM = "term";
	private static final String PERCENT = "percent";
	private static final String LOGIN = "login";
	private static final String ERROR_MESSAGE = "errorMessage";
	private final static String EXP_SUM = "^[0-9]{4,7}$";
	private final static String EXP_PERCENT = "^[0-9]{1,2}$";
	private final static String EXP_TERM = "^[0-9]{1,3}$";
	
	private boolean isAuditorSum(String sum) {
		return !(sum != null && sum.matches(EXP_SUM));
	}
	
	private boolean isAuditorPercent(String percent) {
		return !(percent != null && percent.matches(EXP_PERCENT));
	}
	
	private boolean isAuditorTerm(String term) {
		return !(term != null && term.matches(EXP_TERM));
	}

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Deposit deposit = new Deposit();
		ClientService clientService = new ClientService();
		HistoryService historyService = new HistoryService();
		java.util.Date d = new java.util.Date();
        Date dataTime = new java.sql.Date(d.getTime());
		deposit.setDate(dataTime);
		
		if(isAuditorSum(request.getParameter(SUM))){
			request.setAttribute(TERM, request.getParameter(TERM));
			request.setAttribute(PERCENT, request.getParameter(PERCENT));
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.sumerror"));
			return page = ConfigurationManager.getProperty("path.page.new_deposit");
		}
		
		if(isAuditorTerm(request.getParameter(TERM))){
			request.setAttribute(PERCENT, request.getParameter(PERCENT));
			request.setAttribute(SUM, request.getParameter(SUM));
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.termerror"));
			return page = ConfigurationManager.getProperty("path.page.new_deposit");
		}
		
		if(Integer.parseInt(request.getParameter(TERM))<3)
		{
			request.setAttribute(PERCENT, request.getParameter(PERCENT));
			request.setAttribute(SUM, request.getParameter(SUM));
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.termerror"));
			return page = ConfigurationManager.getProperty("path.page.new_deposit");
		}
		
		if(isAuditorPercent(request.getParameter(PERCENT))){
			request.setAttribute(TERM, request.getParameter(TERM));
			request.setAttribute(SUM, request.getParameter(SUM));
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.percenterror"));
			return page = ConfigurationManager.getProperty("path.page.new_deposit");
		}
		
		if(Integer.parseInt(request.getParameter(PERCENT))<3)
		{
			request.setAttribute(TERM, request.getParameter(TERM));
			request.setAttribute(SUM, request.getParameter(SUM));
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.percenterror"));
			return page = ConfigurationManager.getProperty("path.page.new_deposit");
		}
		
		deposit.setSum(Integer.parseInt(request.getParameter(SUM)));
		deposit.setTerm(Integer.parseInt(request.getParameter(TERM)));
		deposit.setPercent(Integer.parseInt(request.getParameter(PERCENT)));
		String login = (String) request.getSession().getAttribute(LOGIN);
		deposit.setIdClient(clientService.getID(login));
		while (true) {
			int number = (int) (Math.random() * Integer.MAX_VALUE);
			DepositService service = new DepositService();
			List<Integer> depositNumbers = service.getDepositNumbers();
			int index = -1;
			index = depositNumbers.indexOf(number);
			if (index == -1) {
				deposit.setDepositNumber(number);
				service.addDeposit(deposit);
				int idClient = clientService.getID((String) request.getSession().getAttribute(LOGIN));
				List<Deposit> deposits = service.findDepositForClient(idClient);
				Client client = clientService.findClient((String) request.getSession().getAttribute(LOGIN));
				String action = dataTime+" внесен депозит на сумму "+deposit.getSum();
				historyService.addDeposit(deposit.getDepositNumber(),action);
				request.setAttribute("list", deposits);
				request.setAttribute("client", client);
				page = ConfigurationManager.getProperty("path.page.deposits");
				return page;
			}
		}

	}

}
