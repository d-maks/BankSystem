package by.darchyk.banksystem.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.resource.MessageManager;
import by.darchyk.banksystem.service.ClientService;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String ROLE_ADMIN = "admin";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		ClientService service = new ClientService();

		if (service.checkClients(login, pass)) {
			request.setAttribute("user", login);
			request.getSession().setAttribute(PARAM_NAME_LOGIN, login);
			ClientService clientService = new ClientService();
			List<Client> clients = clientService.findClients();
			request.setAttribute("list", clients);
			if (clientService.getRole(login).equals(ROLE_ADMIN)) {
				page = ConfigurationManager.getProperty("path.page.admin_main");
			}
			else{
				page = ConfigurationManager.getProperty("path.page.main");
			}
		} else {
			request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}
}
