package tradingsystem.presentation;

import java.sql.SQLException;

public class HomeController {

	private HomeView homeView;
	private boolean firstTime = true;

	public HomeController() {
		homeView = new HomeView();
	}

	public void run(){
		if(firstTime) homeView.displayInitialSugestion();	//	when open aplication display for the first time display initial sugestion.2
		firstTime = false;
		homeView.executeOption();

		switch (homeView.option){
			case "\\?":
			case "\\help":
				new HelpController();
				break;
			case "\\login":
				//new LoginController();	//	TODO se o login pode estar aqui, aqui,
				break;
			case "\\shutdown":	//	TODO existe o \q \quit, com este é um bocado desnecessário, temos que escolher um dos dois
				//new LogOutController(); 	//	TODO ainda não existe, mas deviamos criar para printar uma cena do género "See you later"
				break;
			case "\register":
				try {
					new RegisterController();
				} catch (SQLException e) {
					//e.printStackTrace();
					homeView.errors();
				} catch (ClassNotFoundException e) {
					//e.printStackTrace();
					homeView.errors();
				}
				break;
			case "\\portfolio":
				new ConsultPortfolioController();
				break;
			case "\\b":
			case "\\buy":
				new BuyCFDController();
				break;
			case "\\s":
			case "\\sell":
				new SellCFDController();
				break;
			case "\\limits":
				new SetLimitsCFDController();
				break;
		}
	}

}