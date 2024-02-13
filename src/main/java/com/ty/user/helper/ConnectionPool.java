package com.ty.user.helper;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

	private static List<Connection> connectionPool = new ArrayList<Connection>();
	private static String driverPath = "org.postgresql.Driver";

	private static String url = "jdbc:postgresql://localhost:5432/user";
	private static String user = "postgres";
	private static String password = "root";

	private static final int POOL_SIZE = 4;

	static {
		try {
			Class.forName(driverPath);

			for (int i = 0; i < POOL_SIZE; i++) {
				Connection connection = createConnection();
				connectionPool.add(connection);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnectionObject() {
		if (!(connectionPool.isEmpty())) {
			return connectionPool.remove(0);
		} else {
			return createConnection();
		}
	}

	public static void receiveConnectionObject(Connection con) {
		if (connectionPool.size() < POOL_SIZE) {
			connectionPool.add(con);
		} else {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static Connection createConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}

