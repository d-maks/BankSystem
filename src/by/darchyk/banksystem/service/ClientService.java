package by.darchyk.banksystem.service;

import java.util.List;

import by.darchyk.banksystem.dao.ClientDAO;
import by.darchyk.banksystem.dao.impl.ClientDAOImpl;
import by.darchyk.banksystem.entity.Client;;

public class ClientService {
	private ClientDAO clientDAO;
	
	public ClientService(){
		clientDAO = new ClientDAOImpl();
	}
	
	public List<Client> findClients(){
		return clientDAO.findClients();
	}
	
	public Client findClient(String login){
		return clientDAO.findClient(login);
	}
	
	public boolean setRole(String login, String role) {
		return clientDAO.setRole(login, role);
	}
	
	public boolean deleteClient(String login){
		return clientDAO.deleteClient(login);
	}
	
	public boolean addClient(Client client){
		return clientDAO.addClient(client);
	}
	
	public boolean checkClients(String login, String password){
		return clientDAO.checkClients(login, password);
	}
	
	public int getID(String login){
		return clientDAO.getID(login);
	}
	public String getRole(String login){
		return clientDAO.getRole(login);
	}
	
	public int countAdmins()
	{
		return clientDAO.countAdmins();
	}
}
