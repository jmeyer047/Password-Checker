package oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordApp {

	public static void main(String[] args) throws NoNumberException {
		String filename = "C:\\Users\\jmeye\\eclipse-workspace\\JavaTraining\\src\\oop\\password1.txt";
		File file = new File(filename);
		String[] passwords = new String[5];
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for(int i = 0; i<5; i++) {
				passwords[i] = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, file not found :"+filename);
		} catch (IOException e) {
			System.out.println("Couldn't read your file :"+filename);
			System.out.println(e.toString());
		}
		
		for(String password: passwords) {
			try {
				if(password.matches(".*\\d+.*") == false) {
					throw new NoNumberException(password);
				}
				System.out.println(password);
			} catch (NoNumberException e) {
				System.out.println(e.toString());
			}
			
			try {
				if(password.matches(".*[a-zA-Z].*") == false) {
					throw new NoLetterException(password);
				}
				System.out.println(password);
			} catch (NoLetterException e) {
				System.out.println(e.toString());
			}
			
			Pattern p = Pattern.compile("[^A-Za-z0-9]");
			Matcher m = p.matcher(password); 
			boolean b = m.find();
			
			try {
				if(!b) {
					throw new NoSpecialException(password);
				}
				System.out.println(password);
			} catch (NoSpecialException e) {
				System.out.println(e.toString());
			}
		}

	}

}

class NoNumberException extends Exception{
	String pass;
	NoNumberException(String pass){
		this.pass = pass;
	}
	public String toString() {
		return ("NoNumberException :"+pass);
	}
}

class NoLetterException extends Exception{
	String pass;
	NoLetterException(String pass){
		this.pass = pass;
	}
	public String toString() {
		return ("NoLetterException :"+pass);
	}
}

class NoSpecialException extends Exception{
	String pass;
	NoSpecialException(String pass){
		this.pass = pass;
	}
	public String toString() {
		return ("NoSpecialException :"+pass);
	}
}
