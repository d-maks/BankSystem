package by.darchyk.banksystem.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.service.ClientService;
import by.darchyk.banksystem.service.DepositService;

public class ReduceDepositCommand implements ActionCommand {
	private static final String LOGIN = "login";

	@Override
	public String execute(HttpServletRequest request) {
		DepositService depositService = new DepositService();
		ClientService clientService = new ClientService();
		int idClient = clientService.getID((String) request.getSession().getAttribute(LOGIN));
		List<Integer> depositNumbers = depositService.getDepositNumberForClient(idClient);
		request.setAttribute("list", depositNumbers);
		String page = ConfigurationManager.getProperty("path.page.reduce_deposit");
		return page;
	}

}
