package io.github.brunovcosta.quillbill.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DB {
	public final String HOST = "localhost";
	public final String PORT = "5432";
	public final String DATABASE = "quillbill";
	public final String USER = "postgres";
	public final String PASSWORD = "postgres";
	public final String DRIVER = "org.postgresql.Driver";

	private Connection connection;
	public DB(){
		try{
			connection = DriverManager.getConnection("jdbc:postgresql://"+HOST+":"+PORT+"/"+DATABASE,USER,PASSWORD);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
	}

	public ArrayList<HashMap<String, String>> query(String sql,Object ... strings){
		try {
			Class.forName(DRIVER);

			PreparedStatement statement = connection.prepareStatement(sql);
			for(int i=0;i<strings.length;i++){
				statement.setObject(i+1, strings[i]);
			}
			ResultSet resultSet = statement.executeQuery();

			ArrayList<HashMap<String, String>> table = DB.resultSet2table(resultSet);
			statement.close();
			
			return table;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int update(String sql,Object ... strings){
		try {
			Class.forName("org.postgresql.Driver");

			PreparedStatement statement = connection.prepareStatement(sql);
			for(int i=0;i<strings.length;i++){
				statement.setObject(i+1, strings[i]);
			}
			int updated_count = statement.executeUpdate();
			statement.close();

			return updated_count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void close(){
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static ArrayList<HashMap<String, String>> resultSet2table(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		while (rs.next()){
			HashMap<String, String> row = new HashMap<String,String>(columns);
			for(int i=1; i<=columns; ++i){           
				row.put(md.getColumnName(i),rs.getString(i));
			}
			list.add(row);
		}

		return list;
	}

	public static String table2string(ArrayList<HashMap<String, String>> table){
		String allRows = "";
		for(HashMap<String, String> row : table){
			for(String column : row.keySet()){
				allRows += row.get(column);
				allRows += ",";
			}
			allRows += "\n";
		}

		return allRows;
	}
}
