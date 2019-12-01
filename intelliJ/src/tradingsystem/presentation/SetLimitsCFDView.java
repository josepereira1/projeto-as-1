package tradingsystem.presentation;

import tradingsystem.business.InvalidInputException;

import java.util.Scanner;

public class SetLimitsCFDView {

	public String idCFD;
	public float takeProfit;
	public float stopLess;

	public SetLimitsCFDView(){
		takeProfit = -1;
		stopLess = -1;
	}

	public void setLimit() throws InvalidInputException {
		idCFD = "";
		takeProfit = -1;
		stopLess = -1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("[Set limits] CFD ID:");
		idCFD = scanner.nextLine();
		System.out.println("[Set limits] take profit value ($) (fill with <<-1>> if not usable):");
		if(scanner.hasNextFloat() == false) throw new InvalidInputException();
		takeProfit = scanner.nextFloat();
		System.out.println("[Set limits] stop less value ($) (fill with <<-1>> if not usable):");
		if(scanner.hasNextFloat() == false) throw new InvalidInputException();
		stopLess = scanner.nextFloat();
	}

	public void CFDIdNotExists(){
		System.out.println("CFD with "+ idCFD + "id not exists! Try Again");
	}

	public void sucess(){
		System.out.println("Sucess!");
	}

	public void error(){
		System.out.println("Internal error, try later!");
	}

	public void inputError(){
		System.out.println("Invalid values, please type numbers!");
	}

}