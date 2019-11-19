package com.company.house.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CompanyHouseDBConnection {

	public static Connection connection = null;

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");

		try {
			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMPANY_HOUSE", "root", "Admin@123");
			}
			System.out.println("DB connection successful.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to make connection.");
			e.printStackTrace();
		}
		return connection;
	}
}
