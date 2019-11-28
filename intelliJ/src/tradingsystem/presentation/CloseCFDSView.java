package tradingsystem.presentation;

import tradingsystem.business.InvalidInputException;

import java.util.Scanner;

public class CloseCFDSView {
    public String CFDId;

    public void closeCFD() throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Close CFD] CFD ID:");
        if(scanner.hasNextLine() == false) throw new InvalidInputException();
        CFDId = scanner.nextLine();
    }

    public void inputError(){
        System.err.println("Input error, please try again!");
    }

    public void sucess(){
        System.out.println("Successfully close CFD!");
    }

    public void error(){
        System.err.println("Internal error! Try later!");
    }
}
