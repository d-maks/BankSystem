package by.darchyk.banksystem.service;

import java.util.List;

import by.darchyk.banksystem.dao.HistoryDAO;
import by.darchyk.banksystem.dao.impl.HistoryDAOImpl;
import by.darchyk.banksystem.entity.History;

public class HistoryService {
	private HistoryDAO historyDAO;

	public HistoryService() {
		historyDAO = new HistoryDAOImpl();
	}

	public List<History> findActions(int depositNumber) {
		return historyDAO.findActions(depositNumber);
	}
	public boolean addDeposit(int depositNumber,String action){
		return historyDAO.addDeposit(depositNumber,action);
	}
}
