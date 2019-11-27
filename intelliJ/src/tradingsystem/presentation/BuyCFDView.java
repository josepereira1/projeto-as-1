package tradingsystem.presentation;

import java.util.Random;
import java.util.Scanner;

public class BuyCFDView {

	public String organizationId;
	public int units;
	public float amount;

	public void organization() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[Buy/long active] Organization id:\n>>");
		organizationId = scanner.nextLine();
	}

	public void displayCurrentPrice(float currentPrice) {
		// TODO - TEMOS QUE ADICIONAR O CURRENT PRICE AO VPP
		throw new UnsupportedOperationException();
	}

	public void getValue() {
		System.out.println("[Buy/long active]");
	}

	public void stockIdNotExists(){
		System.out.println("Stock id" + organizationId +" not exists! Type correct id.");
	}

	public void error(){
		System.out.println("Internal error, try later!");
	}

}