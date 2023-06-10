package com.smilesnake.controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Damitha_Nuwan
 *
 */
public class Database {
	public static Connection myConnection() {
		Connection dbConnection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			Class.forName("com.mysql.cj.jdbc.Driver"); for the different packages
			dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mygame","root","");
		} catch (Exception e) {
			System.out.println(e);
		}
		return dbConnection;
	}
}