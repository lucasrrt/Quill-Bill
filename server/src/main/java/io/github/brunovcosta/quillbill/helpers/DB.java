package io.github.brunovcosta.quillbill.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DB {
	public static String HOST = "localhost";
	public static String PORT = "5432";
	public static String DATABASE = "quillbill";
	public static String USERNAME = "postgres";
	public static String PASSWORD = "postgres";

	public static ResultSet query(String sql){
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection("jdbc:postgresql://"+HOST+":"+PORT+"/"+DATABASE,USERNAME,PASSWORD);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			//statement.close();
			//connection.close();

			return resultSet;
		} catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	}

	public static int update(String sql){
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection("jdbc:postgresql://"+HOST+":"+PORT+"/"+DATABASE,USERNAME,PASSWORD);
			statement = connection.createStatement();
			int updated_count = statement.executeUpdate(sql);

			//statement.close();
			//connection.close();

			return updated_count;
		} catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return 0;
		}
	}

	static public String table2string(ResultSet resultSet){
		try{
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String allRows = "";
			while(resultSet.next()){
				for(int i = 1;i<=columnsNumber;i++){
					allRows += resultSet.getString(i);
					allRows += " ";
				}
				allRows += ",";
			}

			return allRows;

		}catch(Exception e){
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	}
}
