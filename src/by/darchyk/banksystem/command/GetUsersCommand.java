package by.darchyk.banksystem.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.service.ClientService;

public class GetUsersCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		ClientService clientService = new ClientService();
		List<Client> clients = clientService.findClients();
		request.setAttribute("list", clients);
		String page = ConfigurationManager.getProperty("path.page.users");
		return page;
	}

}
