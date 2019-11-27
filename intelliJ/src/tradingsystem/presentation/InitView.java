package tradingsystem.presentation;

import java.util.Scanner;

public class InitView {

	public int action;

	public void informInvalidAction() {
		System.out.println("Please type a valid option.");
	}

	public void selectAction() {
		Scanner sc = new Scanner(System.in);
		System.out.println("[0] - Login");
		System.out.println("[1] - Register");
		System.out.print(">> ");
		if (sc.hasNextLine()) {
			this.action = Integer.parseInt(sc.nextLine());
		}
	}

}