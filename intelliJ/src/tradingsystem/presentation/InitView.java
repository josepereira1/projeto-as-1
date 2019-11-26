package tradingsystem.presentation;

import java.util.Scanner;

public class InitView {

	public int action;

	public void informInvalidAction() {
		System.out.println("Por favor introduza uma opção válida!");
	}

	public void selectAction() {
		Scanner sc = new Scanner(System.in);
		System.out.println("[0] - Login");
		System.out.println("[1] - Fazer registo");
		if (sc.hasNextLine()) {
			this.action = Integer.parseInt(sc.nextLine());
		}
	}

}