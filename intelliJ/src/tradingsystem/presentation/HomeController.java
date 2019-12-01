package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorExistsException;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class HomeController implements Runnable {

	private static HomeController homeController;

	private HomeView homeView;
	private TradingSystem model;

	private HomeController() throws SQLException, ClassNotFoundException {
		homeView = new HomeView();
		model = TradingSystem.getInstance();
	}

	public static HomeController getInstance() throws SQLException, ClassNotFoundException {
		if (homeController == null) homeController = new HomeController();
		return homeController;
	}

	public void run() {
		try {

			if (model.firstTime) {
				model.business.initAutoCloseCFDs(model.ator.getUsername()); // starts auto CFD closing
				model.firstTime = false;
			}

			homeView.displayInitialSugestion();
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
					System.exit(1);
                    break;
				case "\\p":
				case "\\portfolio":
					ConsultPortfolioController.getInstance().run();
					break;
				case "\\b":
				case "\\buy":
					BuyCFDController.getIntance().run();
					break;
				case "\\s":
				case "\\sell":
					SellCFDController.getInstance().run();
					break;
				case "\\l":
				case "\\limits":
					SetLimitsCFDController.getInstance().run();
					break;
				case "\\c":
				case "\\close":
					CloseCFDSController.getInstance().run();
					break;
                case "\\a":
                case "\\ativos":
                    ConsultAtivosController.getInstance().run();
                    break;
				default:
					homeView.informInvalidAction();
					this.run(); //
					break;
			}

		} catch (AtorExistsException e) {
			// TODO remover este Exception do método
			e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
			homeView.informConnIssue();
			System.exit(1);
		} catch (AtorTypeNotValidException e) {
			//e.printStackTrace();
			homeView.informInvalidType();
			System.exit(1);
		} catch (AtorNotExistsException e) {
			//e.printStackTrace();
			homeView.informUsernameNotExists();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.exit(1);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.exit(1);
		} catch (ExecutionException e) {
			//e.printStackTrace();
			System.exit(1);
		}
	}

}