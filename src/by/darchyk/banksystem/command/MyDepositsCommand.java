package by.darchyk.banksystem.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.entity.Deposit;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.service.ClientService;
import by.darchyk.banksystem.service.DepositService;

public class MyDepositsCommand implements ActionCommand{
	private static String LOGIN = "login"; 
	
	@Override
	public String execute(HttpServletRequest request) {
		DepositService service = new DepositService();
		ClientService clientService = new ClientService();
		int idClient = clientService.getID((String) request.getSession().getAttribute(LOGIN));
		List<Deposit> deposits = service.findDepositForClient(idClient);
		Client client = clientService.findClient((String) request.getSession().getAttribute(LOGIN));
		request.setAttribute("list", deposits);
		request.setAttribute("client", client);
		String page = ConfigurationManager.getProperty("path.page.deposits");
		return page;
	}
	
}
