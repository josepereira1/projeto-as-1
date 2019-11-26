package tradingsystem.presentation;

import java.util.Scanner;

public class LoginView {

	public String username;
	public String password;

	public void informConnIssue() {
		System.out.println("Could not establish connection to database server! Try restarting this app.");
	}

	public void informUsernameNotExists() {
		System.out.println("Account with that username does not exists.");
	}

	public void informUsernameTooShort() {
		System.out.println("Invalid username, length is too short!");
	}

	public void informInvalidType() {
		System.out.println("Invalid username type, try:");
		System.out.println("t_<username> for Trader.");
		System.out.println("a_<username> for Administrator.");
	}

	public void informInvalidPassword() {
		System.out.println("Invalid password, try again.");
	}

	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("username:");
		System.out.print(">> ");
		if (sc.hasNextLine()) {
			this.username = sc.nextLine();
		}
		System.out.println("password:");
		System.out.print(">> ");
		if (sc.hasNextLine()) {
			this.password = sc.nextLine();
		}
	}

}