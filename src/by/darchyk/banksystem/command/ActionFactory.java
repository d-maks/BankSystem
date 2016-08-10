package by.darchyk.banksystem.command;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.command.ActionCommand;
import by.darchyk.banksystem.command.CommandEnum;
import by.darchyk.banksystem.command.EmptyCommand;
import by.darchyk.banksystem.resource.MessageManager;

public class ActionFactory {
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}