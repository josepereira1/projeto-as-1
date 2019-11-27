package tradingsystem.presentation;

import java.util.Scanner;

public class SellCFDView {

	public String organizationId;
	public int units;

	public void organization() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[Sell/short active] Organization id:\n>>");
		organizationId = scanner.nextLine();
	}

	public void displayCurrentPrice(float sellPrice, float buyPrice) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[Sell/short active] Current price ($): " + sellPrice + " / " + buyPrice);
		System.out.println("[Sell/short active] Units\n>>");
		units = scanner.nextInt();
	}

	public void displayCurrentProfit(float profit){
		System.out.println("[Sell/short active] CFD profit: " + profit +"($)");
	}

	public void stockIdNotExists(){
		System.out.println("Stock id" + organizationId +" not exists! Type correct id.");
	}

	public void error(){
		System.out.println("Internal error, try later!");
	}

	public void CFDTypeNotExists(){
		System.out.println("The CFD type not exists yet! Try later!");
	}

	public void sucess(){
		System.out.println("Open short CFD with sucess! Congrats!!!");
	}
}