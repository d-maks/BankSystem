package by.darchyk.banksystem.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.darchyk.banksystem.dao.DepositDAO;
import by.darchyk.banksystem.entity.Deposit;
import by.darchyk.banksystem.pool.Pool;

public class DepositDAOImpl implements DepositDAO {
	private static final String QUERY_SELECT_ALL_DEPOSITS = "Select * from deposits";
	private static final String QUERY_DELETE_DEPOSIT = "Delete from deposits where deposit_number = ?";
	private static final String QUERY_FIND_DEPOSIT = "Select * from deposits where deposit_number = ?";
	private static final String QUERY_ADD_DEPOSIT = "Insert into deposits(sum, date, term, percent,deposit_number,id_client) value(?,?,?,?,?,?)";
	private static final String QUERY_GET_DEPOSITS_NUMBER = "Select deposit_number from deposits";
	private static final String QUERY_GET_DEPOSITS_NUMBER_ONE_CLIENT = "Select deposit_number from deposits where id_client = ?";
	private static final String QUERY_GET_SUM = "Select sum from deposits where deposit_number = ?";
	private static final String QUERY_CHANGE_SUM = "Update deposits set sum = ? where deposit_number = ?";
	private static final String QUERY_GET_DEPOSITS_ONE_CLIENT = "Select * from deposits where id_client = ?";

	private void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	private Deposit isDeposit(ResultSet resultSet) throws SQLException {
		Deposit deposit = new Deposit();
		deposit.setId(resultSet.getInt(1));
		deposit.setSum(resultSet.getInt(2));
		deposit.setDate(Date.valueOf(resultSet.getString(3)));
		deposit.setTerm(resultSet.getInt(4));
		deposit.setPercent(resultSet.getInt(5));
		deposit.setDepositNumber(resultSet.getInt(6));
		deposit.setIdClient(resultSet.getInt(7));
		return deposit;
	}

	@Override
	public List<Deposit> findDeposits() {
		List<Deposit> deposits = new ArrayList<>();
		Connection connect = null;
		Statement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_DEPOSITS);
			while (resultSet.next()) {
				Deposit deposit = isDeposit(resultSet);
				deposits.add(deposit);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return deposits;
	}

	@Override
	public Deposit findDeposit(int depositNumber) {
		Connection connect = null;
		PreparedStatement statement = null;
		Deposit deposit = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_FIND_DEPOSIT);
			statement.setInt(depositNumber, 1);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				deposit = isDeposit(resultSet);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return deposit;
	}

	@Override
	public boolean deleteDeposit(int depositNumber) {
		boolean flag = false;
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_DELETE_DEPOSIT);
			statement.setInt(1,depositNumber);
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
	public boolean addDeposit(Deposit deposit) {
		boolean flag = false;
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_ADD_DEPOSIT);
			statement.setInt(1, deposit.getSum());
			statement.setDate(2, deposit.getDate());
			statement.setInt(3, deposit.getTerm());
			statement.setInt(4, deposit.getPercent());
			statement.setInt(5, deposit.getDepositNumber());
			statement.setInt(6, deposit.getIdClient());
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
	public List<Integer> getDepositNumbers() {
		List<Integer> deposits = new ArrayList<>();
		Connection connect = null;
		Statement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_GET_DEPOSITS_NUMBER);
			while (resultSet.next()) {
				Integer number = resultSet.getInt(1);
				deposits.add(number);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return deposits;
	}

	@Override
	public int getSum(int depositNumber) {
		Connection connect = null;
		PreparedStatement statement = null;
		int sum = 0;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_GET_SUM);
			statement.setInt(1, depositNumber);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				sum = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return sum;
	}

	public boolean changeSum(int sum,int depositNumber){
		boolean flag = false;
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_CHANGE_SUM);
			statement.setInt(2, depositNumber);
			statement.setInt(1, sum);
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
	
	public List<Deposit> findDepositForClient(int idClient) {
		List<Deposit> deposits = new ArrayList<>();
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_GET_DEPOSITS_ONE_CLIENT);
			statement.setInt(1, idClient);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Deposit deposit = isDeposit(resultSet);
				deposits.add(deposit);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return deposits;
	}

	@Override
	public List<Integer> getDepositNumberForClient(int idClient) {
		Connection connect = null;
		PreparedStatement statement = null;
		List<Integer> numbers = new ArrayList<>();
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_GET_DEPOSITS_NUMBER_ONE_CLIENT);
			statement.setInt(1, idClient);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Integer number = resultSet.getInt(1);
				numbers.add(number);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return numbers;
	}

}
