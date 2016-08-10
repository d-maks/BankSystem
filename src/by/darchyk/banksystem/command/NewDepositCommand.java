package by.darchyk.banksystem.command;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.resource.ConfigurationManager;

public class NewDepositCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.new_deposit");
		return page;
	}

}
