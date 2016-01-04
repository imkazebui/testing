package com.demo.EmailReader;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.demo.EmailReader.asker.IntegerAsker;
import com.demo.EmailReader.asker.StringAsker;

/**
 * Unit test for simple App.
 */
public class MenuTest
{
	Menu menu;
	@Before
	public void createMenuObject(){
		menu = new Menu();
	}
	@After
	public void destroyMenuObject(){
		menu = null;
	}
	@Test
	public void getMenuNotNullTest(){
		Assert.assertTrue(menu!=null);
	}
	@Test
	public void getUserItemCodeBetweenOneAndFour(){
		IntegerAsker asker = mock(IntegerAsker.class);
		when(asker.ask(anyString())).thenReturn(3);
		Assert.assertEquals(menu.askUserItemCode(asker), 3);
	}
	@Test
	public void getUserItemCodeNotBetweenOneAndFour(){
		IntegerAsker asker = mock(IntegerAsker.class);
		when(asker.ask("Nhap vao chuc nang can thuc hien:")).thenReturn(5);
		when(asker.ask("Nhap khong dung, vui long nhap lai:")).thenReturn(3);
		menu.askUserItemCode(asker);
		verify(asker).ask("Nhap khong dung, vui long nhap lai:");
	}
	@Test
	public void testReturnValueOfAskEmail(){
		String test = "test@er.com";
		StringAsker asker = mock(StringAsker.class);
		when(asker.ask("Nhap email:")).thenReturn("test@er.com");
		Assert.assertEquals(test, menu.askEmail(asker));
		
	}
	@Test
	public void testReturnValueOfAskPassword(){
		String test = "123456";
		StringAsker asker = mock(StringAsker.class);
		when(asker.ask("Nhap password:")).thenReturn("123456");
		Assert.assertEquals(test, menu.askPassword(asker));
		
	}
	@Test
	public void testReturnValueOfAskMessage(){
		String test = "message";
		StringAsker asker = mock(StringAsker.class);
		when(asker.ask("Nhap noi dung:")).thenReturn("message");
		Assert.assertEquals(test, menu.askMessage(asker));
		
	}
	@Test
	public void testAskReceiver(){
		StringAsker asker = mock(StringAsker.class);
		when(asker.ask("Nhap email nguoi nhan:")).thenReturn("admin");
		when(asker.ask("Email nguoi nhan khong ton tai. Nhap lai:")).thenReturn("admin@er.com");
		menu.askReceiver(asker);
		verify(asker).ask("Email nguoi nhan khong ton tai. Nhap lai:");
		
	}
}
