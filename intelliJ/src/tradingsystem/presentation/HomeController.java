package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorExistsException;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

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
				model.business.initAutoCloseCFDs(model.ator.getUsername()); // starts auto CFD closing
				model.firstTime = false;
			}

			homeView.token();

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
					new ConsultPortfolioController().run();
					break;
				case "\\b":
				case "\\buy":
					new BuyCFDController().run();
					break;
				case "\\s":
				case "\\sell":
					new SellCFDController().run();
					break;
				case "\\l":
				case "\\limits":
					new SetLimitsCFDController().run();
					break;
				case "\\c":
				case "\\close":
					new CloseCFDSController().run();
					break;
                case "\\a":
                case "\\ativos":
                    new ConsultAtivosController().run();
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