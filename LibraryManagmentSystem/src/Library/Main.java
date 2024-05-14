package Library;
import java.util.Scanner;

public class Main {
	
	static Scanner s;
	static Database database;
	
	public static void main(String[] args) {
		
		database = new Database();
		System.out.println("Welcome to Library Managment System!");
		
		int num;
		System.out.println("0. Exit\n1. Login\n2. New User");
		s = new Scanner(System.in);
		num = s.nextInt();
			
		switch (num) {
			case 1: login(); break;
			case 2: newuser(); break;
		}

		
	}
	
	private static void login() {
		System.out.println("Enter phone number:");
		String phonenumber = s.next();
		System.out.println("Enter email:");
		String email = s.next();
		int n = database.login(phonenumber, email);
		if(n != -1) {
			User user = database.getUser(n);
			user.menu( database, user);
		} else {
			System.out.println("User doesn't exist!");
		}
	}
	
	private static void newuser() {
		System.out.println("Enter name:");
		String name = s.next();
		if (database.userExists(name)) {
			System.out.println("User exists!");
			newuser();
		}
		System.out.println("Enter phone number:");
		String phonenumber = s.next();
		System.out.println("Enter email:");
		String email = s.next();
		System.out.println("1. Admin\n2. Normal User");
		int n2 = s.nextInt();
		User user;
		if (n2==1) {
			user = new Admin(name, email, phonenumber);
		}else {
			user = new NormalUser (name, email, phonenumber);
		}
		database.AddUser(user);
		user.menu(database, user);
	}

}
