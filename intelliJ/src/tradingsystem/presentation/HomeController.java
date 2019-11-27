package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorExistsException;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;

import java.sql.SQLException;

public class HomeController implements Runnable {

	private HomeView homeView;
	private TradingSystem model;

	public HomeController() throws SQLException, ClassNotFoundException {
		homeView = new HomeView();
		model = TradingSystem.getInstance();
	}

	public void run() {
		try {

			if (model.firstTime) {
				homeView.displayInitialSugestion();    //	when applications is opened display for the first time display initial suggestion
				model.business.initAutoCloseCFDs(model.ator.getUsername()); // starts auto CFD closing
				model.firstTime = false;
			}

			homeView.executeOption();

			switch (homeView.option) {
				case "\\?":
				case "\\help":
					homeView.informAvailableOptions();
					this.run();
					break;
				case "\\q":
				case "\\quit":
					//new LogOutController(); 	//	TODO ainda não existe, mas deviamos criar para printar uma cena do género "See you later"
					break;
				case "\\p":
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
				case "\\l":
				case "\\limits":
					new SetLimitsCFDController();
					break;
				default:
					homeView.informInvalidAction();
					this.run(); //
					break;
			}

		} catch (AtorExistsException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AtorTypeNotValidException e) {
			e.printStackTrace();
		} catch (AtorNotExistsException e) {
			e.printStackTrace();
		}
	}

}