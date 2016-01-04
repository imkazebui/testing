package com.demo.EmailReader;

import com.demo.EmailReader.asker.IntegerAsker;
import com.demo.EmailReader.asker.StringAsker;
import com.demo.EmailReader.bll.EmailProcess;
import com.demo.EmailReader.bll.LoginProcess;
import com.demo.EmailReader.bll.UserProcess;
import com.demo.EmailReader.model.User;

public class Menu {
	User user = null;
	String menuDetail = "";
	public void showMainMenu(){
		int itemCode = 0;
		do{
		//menuDetail condition display
		menuDetail = filterMenuDetailBeforeDisplay();
		//
		System.out.println(menuDetail);
		itemCode = askUserItemCode(new IntegerAsker(System.in, System.out));
		switch(itemCode){
		case 1:
			if(user!=null){
				user=null;
				System.out.println("Dang xuat thanh cong!");
				break;
			}else{
				LoginProcess login = new LoginProcess();
				user = login.verifyLogin(askEmail(new StringAsker(System.in, System.out)), askPassword(new StringAsker(System.in, System.out)));
				if(user==null){
					System.out.println("Ten dang nhap hoac mat khau khong dung !");
				}else{
					System.out.println("Dang nhap thanh cong !");
				}
				break;
			}
		case 2:
			if(user==null){
				System.exit(0);
			}else{
				EmailProcess emailPr = new EmailProcess();
				emailPr.listEmailsReceiveToScreen(user.getId());
				emailPr.listEmailsSendToScreen(user.getId());
			}
			break;
		case 3:
			EmailProcess emailPr = new EmailProcess();
			int fromEmail = user.getId();
			String toEmailStr = askReceiver(new StringAsker(System.in, System.out));
			if(toEmailStr == "") break;
			int toEmail = UserProcess.getIdByEmail(toEmailStr);
			if(toEmail == 0){
				System.out.println("Co loi xay ra voi email nguoi nhan.");
				break;
			}
			String message = askMessage(new StringAsker(System.in, System.out));
			if(message == "") break;
			emailPr.createEmail(fromEmail, toEmail, message);
			System.out.println("Email gui den '"+toEmailStr+"' thanh cong!");
			break;
		}
		}while(itemCode!=4);
		System.out.println("Tam biet!");
	}
	
	public String filterMenuDetailBeforeDisplay() {
		String title = "======Email Reader=====\n";
		String itemMenu1 = "1-"+((user!=null)?"Logout("+user.getEmail()+")":"Login") + "\n";
		String itemMenu2 = ((user!=null)?"2-Xem email\n":"");
		String itemMenu3 = ((user!=null)?"3-Soan email\n":"");
		String itemMenu4 = ((user!=null)?"4-":"2-")+"Thoat\n";
		
		return title+itemMenu1+itemMenu2+itemMenu3+itemMenu4;
		
	}
	public int askUserItemCode(IntegerAsker asker){
		int input = asker.ask("Nhap vao chuc nang can thuc hien:");
		while(input <1 || input>4)
			input = asker.ask("Nhap khong dung, vui long nhap lai:");
		return input;
	}
	public String askEmail(StringAsker asker){
		String input = asker.ask("Nhap email:");
		return input;
	}
	public String askPassword(StringAsker asker){
		String input = asker.ask("Nhap password:");
		return input;
	}
	public String askReceiver(StringAsker asker) {
		String input = asker.ask("Nhap email nguoi nhan:");
		while(!UserProcess.checkExists(input)){
			input = asker.ask("Email nguoi nhan khong ton tai. Nhap lai:");
		}
		return input;
	}
	public String askMessage(StringAsker asker) {
		String input = asker.ask("Nhap noi dung:");
		return input;
	}
}	
	
