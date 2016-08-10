package by.darchyk.banksystem.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.resource.MessageManager;
import by.darchyk.banksystem.service.ClientService;

public class SetUserCommand implements ActionCommand {
	private static final String LOGIN = "login";
	private static final String ADMIN = "admin";
	private static final String ERROR_MESSAGE = "errorMessage";

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(LOGIN);
		ClientService clientService = new ClientService();
		if(clientService.getRole(login).equals(ADMIN)&&clientService.countAdmins() == 1){
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.setusererror"));
		}
		else{
		clientService.setRole(login, "user");
		}
		List<Client> clients = clientService.findClients();
		request.setAttribute("list", clients);
		String page = ConfigurationManager.getProperty("path.page.admin_main");
		return page;
	}

}
