package com.demo.EmailReader.bll;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.EmailReader.dao.DatabaseAccess;
import com.demo.EmailReader.model.Email;
public class EmailProcess {
	public Email[] getEmails(int userId, String type){
		Email[] emails = null;
		try {
			Email email = null;
			DatabaseAccess.openDb();
			DatabaseAccess.setSql(queryCreator(userId, type));
			ResultSet rs = DatabaseAccess.executeSql();
			
			int rowcount = 0;
			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst(); 
			}
			emails = new Email[rowcount];
			for(int i = 0; i < rowcount;i++){
				rs.next();
				email = new Email();
				email.setId(rs.getInt("id"));
				email.setSender(rs.getInt("sender"));
				email.setReceiver(rs.getInt("receiver"));
				email.setMessage(rs.getString("message"));
				emails[i] = email;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseAccess.closeDb();
		}
		
		return emails;
	}
	
	private String queryCreator(int userId, String type){
		return "select * from emails where "+type+"='"+userId+"';";
	}
	
	public void listEmailsReceiveToScreen(int userId){
		Email[] emails = getEmails(userId, "receiver");
		System.out.println("**So thu trong hop thu den :"+emails.length);
		for (int i = 0; i<emails.length; i++)
		{
			System.out.println(">>"+(i+1)+"<<");
			System.out.println("From:"+UserProcess.getEmailById(emails[i].getSender()));
			System.out.println("Content:"+emails[i].getMessage());
			System.out.println("");
		}
	}
	public void listEmailsSendToScreen(int userId){
		Email[] emails = getEmails(userId, "sender");
		System.out.println("**So thu trong hop thu da gui :"+emails.length);
		for (int i = 0; i<emails.length; i++)
		{
			System.out.println(">>"+(i+1)+"<<");
			System.out.println("To:"+UserProcess.getEmailById(emails[i].getReceiver()));
			System.out.println("Content:"+emails[i].getMessage());
			System.out.println("");
		}
	}

	public boolean createEmail(int fromEmail, int toEmail, String message) {
		int result = 0;
		try {
			DatabaseAccess.openDb();
			DatabaseAccess.setSql("insert into emails(sender, receiver, message) values('"+fromEmail+"','"+toEmail+"','"+message+"');");
			result = DatabaseAccess.executeUp();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseAccess.closeDb();
		}
		if(result>0)
			return true;
		else
			return false;
	}
}
