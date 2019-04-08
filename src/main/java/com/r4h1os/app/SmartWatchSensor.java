package com.r4h1os.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.edgent.function.Supplier;

@SuppressWarnings("serial")
public class SmartWatchSensor implements Supplier<Triple<String , Integer , Integer>> {
	ResultSet res = null;
	
	Triple<String , Integer , Integer> pair = null;
	String timestamp;
	int steps , heart_rate;
	Connection con;
	
	public SmartWatchSensor() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(
					"jdbc:sqlite:/Users/milanimac/Developer/eclipse_workspace/Mini_Project_IoT/Gadgetbridge.db", 
					"", "");
			
			Statement statement = con.createStatement();
			res = statement.executeQuery("select datetime(TIMESTAMP, 'unixepoch') ,"
										+ " steps , heart_rate from MI_BAND_ACTIVITY_SAMPLE");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	@Override
	public Triple<String, Integer , Integer> get() {
		try {
			if(res.next()) {
				timestamp = res.getString(1);
				steps = res.getInt(2);
				heart_rate = res.getInt(3);
				pair = new MutableTriple<String , Integer , Integer>(timestamp , steps ,heart_rate );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pair;
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
