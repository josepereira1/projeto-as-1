package tradingsystem.presentation;

import java.util.Scanner;

public class HomeView {

	public String option;

	public void executeOption() {
		Scanner input = new Scanner(System.in);
		option = input.nextLine();
	}

	public void informAvailableOptions() {
		System.out.println("\\? or \\help to open this frame");
		System.out.println("\\q or \\quit to quit this app");
		System.out.println("\\p or \\portfolio to display your portfolio");
		System.out.println("\\b or \\buy to buy stocks");
		System.out.println("\\s or \\sell to sell stocks");
		System.out.println("\\l or \\limits to set stock limits");
	}

	public void informConnIssue() {
		System.out.println("Connection to database server was lost! Try restarting this app.");
	}

	public void informUsernameNotExists() {
		System.out.println("Internal error, account with this username no longer exists! Contact an Administrator.");
	}

	public void informInvalidType() {
		System.out.println("Internal error, account is invalid! Contact an Administrator.");
	}

	public void informInvalidAction() {
		System.out.println("Please type a valid option.");
	}

	public void displayInitialSugestion() {
		System.out.println("type \\? or \\help to access help frame.");
	}

	public void token(){
		System.out.print(">>");
	}
}