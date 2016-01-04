package com.demo.EmailReader.bll;
import java.sql.*;

import com.demo.EmailReader.dao.*;
import com.demo.EmailReader.model.User;
public class LoginProcess {
	
	public User verifyLogin(String email, String pass){
		try {
			User u = null;
			DatabaseAccess.openDb();
			DatabaseAccess.setSql(queryCreator(email, pass));
			ResultSet rs = DatabaseAccess.executeSql();
			if(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail(rs.getString("email"));
				u.setLname(rs.getString("lname"));
				u.setFname(rs.getString("fname"));
			}
			DatabaseAccess.closeDb();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String queryCreator(String email, String pass){
		return "select * from users where email='"+email+"' and password='"+pass+"';";
	}
}
