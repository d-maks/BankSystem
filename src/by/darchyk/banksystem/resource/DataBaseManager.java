package by.darchyk.banksystem.resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {
	//private static final ResourceBundle DB_BUNDLE = ResourceBundle.getBundle("resource.database");

	public Connection getConnection(Properties properties) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", properties);
		} catch (SQLException e) {
			throw new ExceptionInInitializerError();
		}

		return connection;
	}
}