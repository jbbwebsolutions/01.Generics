package com.jbbwebsolutions.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLHelper {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/CustomerDb";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";

	private SQLHelper() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.out.println(JDBC_DRIVER);
			System.exit(1);
		}
	};

	public static SQLHelper instanceOf() {
		return new SQLHelper();
	}	

	public <E> List<E> getList(String sql, SQLFunction<ResultSet, Integer, E> sqlFunc) {
		List<E> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			int i = 1;
			while (rs.next()) {
				E e = sqlFunc.execute(rs, i);
				list.add(e);
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int modify(String sql) {
		int code = 1;

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
			Statement stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return code;
	}

}