package by.darchyk.banksystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.darchyk.banksystem.dao.ClientDAO;
import by.darchyk.banksystem.entity.Client;
import by.darchyk.banksystem.pool.Pool;

public class ClientDAOImpl implements ClientDAO {
	private static final String QUERY_SELECT_ALL_ClIENTS = "Select * from client_information";
	private static final String QUERY_GET_LOGIN = "Select login from clients";
	private static final String QUERY_DELETE_CLIENT = "Delete from clients where login = ?";
	private static final String QUERY_FIND_CLIENT = "Select client_information.id,name,surname,address,phone_number,e_mail from clients join client_information on client_information.id = clients.id where login = ?";
	private static final String QUERY_ADD_CLIENT_INFORMATION = "Insert into client_information(id,name,surname,address,phone_number,e_mail) value((SELECT id FROM clients where login=?),?,?,?,?,?)";
	private static final String QUERY_ADD_CLIENT = "Insert into clients(login,password) value(?,?)";
	private static final String QUERY_GET_CLIENT = "Select * from clients where login = ? and password = ?";
	private static final String QUERY_GET_ID = "Select id from clients where login = ?";
	private static final String QUERY_GET_ROLE = "Select role from clients where login = ?";
	private static final String QUERY_SET_ROLE = "Update clients set role = ? where login = ?";
	private static final String QUERY_GET_COUNT_ADMINS = "Select count(*) from clients where role = 'admin'";

	private void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	private Client isClient(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setId(resultSet.getInt(1));
		client.setName(resultSet.getString(2));
		client.setSurname(resultSet.getString(3));
		client.setAddress(resultSet.getString(4));
		client.setPhoneNumber(resultSet.getString(5));
		client.setMail(resultSet.getString(6));
		return client;
	}

	@Override
	public List<Client> findClients() {
		List<Client> clients = new ArrayList<>();
		Connection connect = null;
		Statement statement = null;
		Statement loginStatement;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.createStatement();
			loginStatement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_ClIENTS);
			ResultSet loginSet = loginStatement.executeQuery(QUERY_GET_LOGIN);
			while (resultSet.next() && loginSet.next()) {
				Client client = isClient(resultSet);
				String login = loginSet.getString(1);
				client.setLogin(login);
				client.setRole(getRole(login));
				clients.add(client);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return clients;
	}

	@Override
	public Client findClient(String login) {
		Connection connect = null;
		PreparedStatement statement = null;
		Client client = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_FIND_CLIENT);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				client = isClient(resultSet);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return client;
	}

	@Override
	public boolean deleteClient(String login) {
		Connection connect = null;
		PreparedStatement statement = null;
		boolean flag = false;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_DELETE_CLIENT);
			statement.setString(1, login);
			statement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
			flag = false;
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return flag;
	}

	@Override
	public boolean addClient(Client client) {
		Connection connect = null;
		PreparedStatement statement = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			connect = Pool.getInstance().getResource();
			preparedStatement = connect.prepareStatement(QUERY_ADD_CLIENT);
			preparedStatement.setString(1, client.getLogin());
			preparedStatement.setString(2, client.getPassword());
			preparedStatement.executeUpdate();
			statement = connect.prepareStatement(QUERY_ADD_CLIENT_INFORMATION);
			statement.setString(1, client.getLogin());
			statement.setString(2, client.getName());
			statement.setString(3, client.getSurname());
			statement.setString(4, client.getAddress());
			statement.setString(5, client.getPhoneNumber());
			statement.setString(6, client.getMail());
			statement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
			flag = false;
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return flag;
	}

	@Override
	public boolean checkClients(String login, String password) {

		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_GET_CLIENT);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
			return false;
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
	}

	@Override
	public int getID(String login) {
		int id = 0;
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_GET_ID);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return id;
	}

	@Override
	public String getRole(String login) {
		String role = null;
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_GET_ROLE);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				role = resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return role;
	}

	@Override
	public boolean setRole(String login, String role) {
		boolean flag = false;
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_SET_ROLE);
			statement.setString(2, login);
			statement.setString(1, role);
			statement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
			flag = false;
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return flag;
	}

	@Override
	public int countAdmins() {
		Connection connect = null;
		Statement statement = null;
		int count = 0;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_GET_COUNT_ADMINS);
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return count;
	}

}
