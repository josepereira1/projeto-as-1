package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;

import java.sql.SQLException;

public class LoginController implements Runnable {

	private LoginView loginView;
	private TradingSystem model;

	public LoginController() throws SQLException, ClassNotFoundException {
		this.loginView = new LoginView();
		this.model = TradingSystem.getInstance();
	}

	public void run() {
		loginView.login();
		try {
			model.ator = model.business.autenticarUtilizador(loginView.username, loginView.password);
			if (model.ator == null) {
				loginView.informInvalidPassword();
				this.run();
			} else {
				new HomeController().run();
			}
		} catch (AtorNotExistsException | StringIndexOutOfBoundsException e) {
			//e.printStackTrace();
			loginView.informInvalidUsername();
			this.run();
		} catch (SQLException e) {
			//e.printStackTrace();
			loginView.informConnIssue();
			System.exit(1);
		} catch (AtorTypeNotValidException e) {
			//e.printStackTrace();
			loginView.informInvalidType();
			this.run();
		}
	}

}