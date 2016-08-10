package by.darchyk.banksystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.darchyk.banksystem.dao.HistoryDAO;
import by.darchyk.banksystem.entity.History;
import by.darchyk.banksystem.pool.Pool;

public class HistoryDAOImpl implements HistoryDAO{
	private static final String QUERY_SELECT_ALL_ACTIONS = "Select action from history where number = ?";
	private static final String QUERY_ADD_ACTION = "Insert into history (number,action) value(?,?)";
	
	private void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}
	
	public List<History> findActions(int depositNumber) {
		List<History> actions = new ArrayList<>();
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_SELECT_ALL_ACTIONS);
			statement.setInt(1, depositNumber);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				History action = new History();
				action.setAction(resultSet.getString(1));
				actions.add(action);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return actions;
	}
	
	public boolean addDeposit(int depositNumber,String action) {
		boolean flag = false;
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_ADD_ACTION);
			statement.setInt(1, depositNumber);
			statement.setString(2, action);
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
}
