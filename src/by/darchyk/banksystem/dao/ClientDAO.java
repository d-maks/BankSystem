package by.darchyk.banksystem.dao;

import java.util.List;

import by.darchyk.banksystem.entity.Client;

public interface ClientDAO {
	
	List<Client> findClients();
	Client findClient(String login);
	boolean deleteClient(String login);
	boolean addClient(Client client);
	boolean checkClients(String login, String passwords);
	int getID(String login);
	String getRole(String login);
	boolean setRole(String login, String role);
	int countAdmins();
}
