package tradingsystem.presentation;

import java.util.Scanner;

public class RegisterView {

	public String username;
	public String password;
	public String confirmPassword;
	public float plafond;

	public void informConnIssue() {
		System.out.println("Could not establish connection to database server! Try restarting this app.");
	}

	public void informUsernameTooShort() {
		System.out.println("Invalid username, length is too short!");
	}

	public void informInvalidType() {
		System.out.println("Invalid username type, try:");
		System.out.println("t_<username> for Trader.");
	}

	public void informUsernameAlreadyExists() {
		System.out.println("Account with that username already exists!");
	}

	public void informPassowrdNotMatch() {
		System.out.println("Password does not match.");
	}

	public void informInvalidPlafondNumber() {
		System.out.println("Plafond inválido.");
	}

	public void informUsername() {
		System.out.println("Successful registered as: " + username);
	}

	public void register() {
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
		System.out.println("confirm password:");
		System.out.print(">> ");
		if (sc.hasNextLine()) {
			this.confirmPassword = sc.nextLine();
		}
		System.out.println("initial investment (€): minimum is 200 €");
		System.out.print(">> ");
		if (sc.hasNextLine()) {
			this.plafond = Float.parseFloat(sc.nextLine());
		}
	}
}