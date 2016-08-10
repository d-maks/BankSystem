package by.darchyk.banksystem.command;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.resource.MessageManager;
import by.darchyk.banksystem.service.ClientService;

public class AddClientCommand implements ActionCommand {

	private static final String INPUTLOGIN = "inputLogin";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "inputPassword";
	private static final String NAME = "firstName";
	private static final String SURNAME = "lastName";
	private static final String E_MAIL = "inputEmail";
	private static final String SECOND_PASSWORD = "confirmPassword";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String ADDRESS = "postalAddress";
	private static final String ERROR_MESSAGE = "errorMessage";
	private final static String EXP_TELEPHONE = "((-?[0-9][0-9][0-9][0-9][0-9][0-9][0-9]))";
	private final static String EXP_LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{4,20}$";
	public final static String EXP_FIRST_NAME = "^[а-яА-ЯёЁ]{2,20}$";
	public final static String EXP_lAST_NAME = "^[а-яА-ЯёЁ]{2,20}$";

	private boolean isAuditorPhoneNumber(String phoneNumber) {
		return !(phoneNumber != null && phoneNumber.matches(EXP_TELEPHONE));
	}

	private boolean isAuditorLogin(String login) {
		return !(login != null && login.matches(EXP_LOGIN));
	}

	private boolean isAuditorFirstName(String firstName) {
		return !(firstName != null && firstName.matches(EXP_FIRST_NAME));
	}

	private boolean isAuditorLastName(String lastName) {
		return !(lastName != null && lastName.matches(EXP_lAST_NAME));
	}

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Client client = new Client();
		ClientService service = new ClientService();
		client.setLogin(request.getParameter(INPUTLOGIN));
		client.setPassword(request.getParameter(PASSWORD));
		client.setName(request.getParameter(NAME));
		client.setSurname(request.getParameter(SURNAME));
		client.setMail(request.getParameter(E_MAIL));
		client.setPhoneNumber(request.getParameter(PHONE_NUMBER));
		client.setAddress(request.getParameter(ADDRESS));

		if (isAuditorFirstName(client.getSurname())) {
			request.setAttribute(INPUTLOGIN, client.getLogin());
			request.setAttribute(ADDRESS, client.getAddress());
			request.setAttribute(E_MAIL, client.getMail());
			request.setAttribute(NAME, client.getName());
			request.setAttribute(PHONE_NUMBER, client.getPhoneNumber());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.surnameererror"));
			return page = ConfigurationManager.getProperty("path.page.reg");
		}

		if (isAuditorLastName(client.getName())) {
			request.setAttribute(INPUTLOGIN, client.getLogin());
			request.setAttribute(ADDRESS, client.getAddress());
			request.setAttribute(E_MAIL, client.getMail());
			request.setAttribute(SURNAME, client.getSurname());
			request.setAttribute(PHONE_NUMBER, client.getPhoneNumber());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.nameererror"));
			return page = ConfigurationManager.getProperty("path.page.reg");
		}

		if (isAuditorLogin(client.getLogin())) {
			request.setAttribute(PHONE_NUMBER, client.getPhoneNumber());
			request.setAttribute(ADDRESS, client.getAddress());
			request.setAttribute(E_MAIL, client.getMail());
			request.setAttribute(NAME, client.getName());
			request.setAttribute(SURNAME, client.getSurname());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.regloginerror"));
			return page = ConfigurationManager.getProperty("path.page.reg");
		}

		if (isAuditorPhoneNumber(client.getPhoneNumber())) {
			request.setAttribute(INPUTLOGIN, client.getLogin());
			request.setAttribute(ADDRESS, client.getAddress());
			request.setAttribute(E_MAIL, client.getMail());
			request.setAttribute(NAME, client.getName());
			request.setAttribute(SURNAME, client.getSurname());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.phonenumbererror"));
			return page = ConfigurationManager.getProperty("path.page.reg");
		}

		if (client.getPassword().length() <= 5) {
			request.setAttribute(INPUTLOGIN, client.getLogin());
			request.setAttribute(ADDRESS, client.getAddress());
			request.setAttribute(E_MAIL, client.getMail());
			request.setAttribute(NAME, client.getName());
			request.setAttribute(SURNAME, client.getSurname());
			request.setAttribute(PHONE_NUMBER, client.getPhoneNumber());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.passworderror"));
			return page = ConfigurationManager.getProperty("path.page.reg");
		}

		if (service.checkClients(client.getLogin(), client.getPassword())) {
			request.setAttribute(PHONE_NUMBER, client.getPhoneNumber());
			request.setAttribute(ADDRESS, client.getAddress());
			request.setAttribute(E_MAIL, client.getMail());
			request.setAttribute(NAME, client.getName());
			request.setAttribute(SURNAME, client.getSurname());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.repeatloginerror"));
			return page = ConfigurationManager.getProperty("path.page.reg");
		}

		if (client.getPassword().equals(request.getParameter(SECOND_PASSWORD))) {
			service.addClient(client);
			request.getSession().setAttribute(LOGIN, client.getLogin());
			request.setAttribute("user", client.getLogin());
			page = ConfigurationManager.getProperty("path.page.main");
		} else {
			request.setAttribute(PHONE_NUMBER, client.getPhoneNumber());
			request.setAttribute(ADDRESS, client.getAddress());
			request.setAttribute(E_MAIL, client.getMail());
			request.setAttribute(NAME, client.getName());
			request.setAttribute(SURNAME, client.getSurname());
			request.setAttribute(INPUTLOGIN, client.getLogin());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty("message.confirmerror"));
			page = ConfigurationManager.getProperty("path.page.reg");
		}
		return page;
	}
}
