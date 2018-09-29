package com.mixicoding.manageapp.database;

import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Description: Lớp kết nối Database
 * Write by:	Võ Phi Khanh
 * Create date: 15/4/2018
 */

public class Database {
	public static Connection con = null;
	private static Database instance = new Database();

	public static Database getInstance() {
		return instance;
	}

	public void connect() {
		try {
			con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaTro;user=sa;password=sapassword");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void disconnect() {
		if (con != null)
			try {
				con.close();

			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

	public Connection getConnection() {
		return con;
	}
}
