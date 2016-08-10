package by.darchyk.banksystem.dao;

import java.util.List;

import by.darchyk.banksystem.entity.History;

public interface HistoryDAO {
	List<History> findActions(int depositNumber);
	boolean addDeposit(int depositNumber,String action);
}
