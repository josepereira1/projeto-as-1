package tradingsystem.presentation;

import java.util.Scanner;

public class SetLimitsCFDView {

	public String idCFD;
	public float takeProfit;
	public float stopLess;

	public SetLimitsCFDView(){
		takeProfit = -1;
		stopLess = -1;
	}

	public void setLimit() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[Set limits] active ID:");
		idCFD = scanner.nextLine();
		System.out.println("[Set limits] take profit value ($) (fill with <<-1>> if not usable):");
		if(scanner.hasNextInt()) {	//	only numbers
			takeProfit = scanner.nextFloat();
		}
		System.out.println("[Set limits] stop less value ($) (fill with <<-1>> if not usable):");
		if(scanner.hasNextInt()) {	//	only numbers
			stopLess = scanner.nextFloat();
		}
	}

	public void CFDIdNotExists(){
		System.out.println("CFD id not exists!");
	}

	public void sucess(){
		System.out.println("Sucess!");
	}

	public void error(){
		System.out.println("Internal error, try later!");
	}

}