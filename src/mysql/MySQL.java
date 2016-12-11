package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/VoiceMonitor";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public MySQL() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public ResultSet executeQuery(String sql) {
		connection = this.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public int executeInsert(String sql) {
		connection = this.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int executeUpdate(String sql, Object[] obj) {
		connection = this.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				preparedStatement.setObject(i + 1, obj[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int executeUpdate(String sql) {
		connection = this.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void closeAll() throws Exception {
		if (null != resultSet) {
			resultSet.close();
		}
		if (null != preparedStatement) {
			preparedStatement.close();
		}
		if (null != connection) {
			connection.close();
		}
	}

}
