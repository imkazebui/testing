package com.demo.EmailReader.asker;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IntegerAsker {
	private final Scanner scanner; 
	private final PrintStream out;
	
	public IntegerAsker(InputStream in, PrintStream out){
		scanner = new Scanner(in);
		this.out = out;
	}
	
	public int ask(String message){
		out.print(message);
		return scanner.nextInt();
	}
}
