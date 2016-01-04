package com.demo.EmailReader.dao;
import java.sql.*;
public class DatabaseAccess {
	static final String DB_URL = "jdbc:mysql://localhost:3306/emailreader";
	
	static final String USER = "root";
	static final String PASS = "";
	
	private static String sql = "";
	private static Connection conn = null;
	private static Statement stmt = null;
	public static void openDb() throws SQLException, Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}
	
	public static void setSql(String sql){
		DatabaseAccess.sql = sql;
	}
	
	public static ResultSet executeSql() throws SQLException{
		return stmt.executeQuery(DatabaseAccess.sql);
	}
	
	public static void closeDb(){
		try{
         if(stmt!=null)
            stmt.close();
         }catch(SQLException se2){
        }
        try{
         if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
	}

	public static int executeUp() {
		int result = 0;
		try {
			result = stmt.executeUpdate(DatabaseAccess.sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
