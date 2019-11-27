package tradingsystem.presentation;

import java.util.Scanner;

public class HomeView {

	public String option;

	public void executeOption() {
		Scanner input = new Scanner(System.in);
		option = input.nextLine();
	}

	public void errors(){
		System.out.println("Error with aplication, try it later!");
	}

	public void displayInitialSugestion(){
		System.out.println("Type \\? or \\help to see options");
	}
}