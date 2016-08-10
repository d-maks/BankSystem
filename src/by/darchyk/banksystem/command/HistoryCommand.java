package by.darchyk.banksystem.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.History;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.service.DepositService;
import by.darchyk.banksystem.service.HistoryService;

public class HistoryCommand implements ActionCommand {
	private static final String NUMBER = "number";

	@Override
	public String execute(HttpServletRequest request) {
		HistoryService historyService = new HistoryService();
		DepositService depositService = new DepositService();
		int number = Integer.parseInt(request.getParameter(NUMBER));
		List<History> history = historyService.findActions(number);
		int sum = depositService.getSum(number);
		request.setAttribute("sum", sum);
		request.setAttribute("list", history);
		request.setAttribute("number", number);
		String page = ConfigurationManager.getProperty("path.page.history");
		return page;
	}

}
