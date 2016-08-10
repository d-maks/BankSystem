package by.darchyk.banksystem.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.banksystem.entity.History;
import by.darchyk.banksystem.resource.ConfigurationManager;
import by.darchyk.banksystem.service.DepositService;
import by.darchyk.banksystem.service.HistoryService;

public class SaveCommand implements ActionCommand {
	private static final String NUMBER = "number";
	
	private void saveToFile(int number, List<History> histories){
		Writer writer = null;
		try {
			writer = new FileWriter(new File("C:\\Users\\Public\\Downloads\\"+number+"_actions.txt"));
			for(History history : histories)
			{
				writer.write(history.getAction()+"\r\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String execute(HttpServletRequest request) {
		int number = Integer.parseInt(request.getParameter(NUMBER));
		HistoryService historyService = new HistoryService();
		List<History> histories = historyService.findActions(number);
		saveToFile(number, histories);
		DepositService depositService = new DepositService();
		int sum = depositService.getSum(number);
		request.setAttribute("sum", sum);
		request.setAttribute("list", histories);
		request.setAttribute("number", number);
		String page = ConfigurationManager.getProperty("path.page.history");
		return page;
	}

}
