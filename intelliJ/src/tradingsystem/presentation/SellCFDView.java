package tradingsystem.presentation;

import tradingsystem.business.InvalidInputException;

import java.util.Scanner;

public class SellCFDView {

	public String organizationId;
	public int units;

	public void organization() throws InvalidInputException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[Sell/short active] Organization id:\n>>");
		if(scanner.hasNextLine() == false) throw new InvalidInputException();
		organizationId = scanner.nextLine();
	}

	public void displayCurrentPrice(float sellPrice, float buyPrice) throws InvalidInputException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[Sell/short active] Current price ($): " + sellPrice + " / " + buyPrice);
		System.out.println("[Sell/short active] Units\n>>");
		if(scanner.hasNextInt() == false)throw new InvalidInputException();
		units = scanner.nextInt();
	}

	public void displayCurrentProfit(float profit){
		System.out.println("[Sell/short active] CFD profit: " + profit +"($)");
	}

	public void stockIdNotExists(){
		System.out.println("Stock id " + organizationId +" not exists! Type correct id.");
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

	public void inputError(){
		System.out.println("Invalid values, please type numbers!");
	}
}