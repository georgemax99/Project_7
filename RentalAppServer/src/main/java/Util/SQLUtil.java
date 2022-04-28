package Util;

import java.sql.*;

public class SQLUtil {

	public static final String DEV_HOST = "database-3.cwv3n8ialhs8.us-east-1.rds.amazonaws.com";
	public static final String DEV_DATABASE = "rentalApp";
	public static final String DEV_USER = "admin";
	public static final String DEV_PASSWORD = "Myoung99";

	public static Connection getConnection() {
		String connectionString = "jdbc:mysql://" + DEV_HOST + ":3306/" + DEV_DATABASE
				+ "?user=" + DEV_USER + "&password=" + DEV_PASSWORD;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(connectionString);
			//conn = DriverManager.getConnection()
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to database.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to database.");
		}
		return conn;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
	}

	public static void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}

}
