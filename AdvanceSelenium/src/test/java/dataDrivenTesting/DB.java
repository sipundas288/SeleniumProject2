package dataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DB {
	public static void main(String[] args) throws SQLException {
		
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
		Statement sta = con.createStatement();
		ResultSet res = sta.executeQuery("Select * from Demo1");
		while(res.next()) {
			System.out.println(res.getString(1));
		}
		
	}

}
