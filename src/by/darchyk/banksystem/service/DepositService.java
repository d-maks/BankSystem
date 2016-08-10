package by.darchyk.banksystem.service;

import java.util.List;

import by.darchyk.banksystem.dao.DepositDAO;
import by.darchyk.banksystem.dao.impl.DepositDAOImpl;
import by.darchyk.banksystem.entity.Deposit;

public class DepositService {
	private DepositDAO depositDAO;

	public DepositService() {
		depositDAO = new DepositDAOImpl();
	}

	public List<Deposit> findDeposits() {
		return depositDAO.findDeposits();
	}

	public Deposit findDeposit(int depositNumber) {
		return depositDAO.findDeposit(depositNumber);
	}

	public boolean deleteDeposit(int depositNumber) {
		return depositDAO.deleteDeposit(depositNumber);
	}

	public boolean addDeposit(Deposit deposit) {
		return depositDAO.addDeposit(deposit);
	}

	public List<Integer> getDepositNumbers() {
		return depositDAO.getDepositNumbers();
	}

	public int getSum(int depositNumber) {
		return depositDAO.getSum(depositNumber);
	}

	public boolean changeSum(int sum, int depositNumber) {
		return depositDAO.changeSum(sum, depositNumber);
	}

	public List<Deposit> findDepositForClient(int idClient) {
		return depositDAO.findDepositForClient(idClient);
	}
	
	public List<Integer> getDepositNumberForClient(int idClient) {
		return depositDAO.getDepositNumberForClient(idClient);
	}
}
