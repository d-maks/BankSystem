package by.darchyk.banksystem.dao;

import java.util.List;

import by.darchyk.banksystem.entity.Deposit;

public interface DepositDAO {
	
	
	List<Deposit> findDeposits();
	Deposit findDeposit(int depositNumber);
	boolean deleteDeposit(int depositNumber);
	boolean addDeposit(Deposit deposit);	
	List<Integer> getDepositNumbers(); 
	int getSum(int depositNumber);
	boolean changeSum(int sum,int depositNumber);
	List<Deposit> findDepositForClient(int idClient);
	List<Integer> getDepositNumberForClient(int idClient); 
}
