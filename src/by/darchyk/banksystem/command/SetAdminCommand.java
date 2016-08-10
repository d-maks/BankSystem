package by.darchyk.banksystem.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.service.ClientService;

public class SetAdminCommand implements ActionCommand {
	private static final String LOGIN = "login";
	
	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(LOGIN);
		ClientService clientService = new ClientService();
		clientService.setRole(login, "admin");
		List<Client> clients = clientService.findClients();
		request.setAttribute("list", clients);
		String page = ConfigurationManager.getProperty("path.page.admin_main");
		return page;
	}

}
