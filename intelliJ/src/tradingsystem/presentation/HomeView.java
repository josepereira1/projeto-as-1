package tradingsystem.presentation;

import java.util.Scanner;

public class HomeView {

	public String option;

	public void informAvailableOptions() {
		System.out.println("\\? or \\help to open this frame");
		System.out.println("\\q or \\quit to quit this app");
		System.out.println("\\p or \\portfolio to display your portfolio");
		System.out.println("\\b or \\buy to buy stocks");
		System.out.println("\\s or \\sell to sell stocks");
		System.out.println("\\l or \\limits to set stock limits");
	}

	public void executeOption() {
		Scanner input = new Scanner(System.in);
		option = input.nextLine();
	}

	public void informInvalidAction() {
		System.out.println("Please type a valid option.");
	}

	public void errors(){
		System.out.println("Error with aplication, try it later!");
	}

	public void displayInitialSugestion(){
		System.out.println("Type \\? or \\help to see options");
	}
}