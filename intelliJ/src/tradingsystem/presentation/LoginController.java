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
		try {
			loginView.login();
			model.ator = model.business.autenticarUtilizador(loginView.username, loginView.password);
			if (model.ator == null) { // invalid password
				loginView.informInvalidPassword();
				this.run();
			} else { // goes to main menu
				new HomeController().run();
			}
		} catch (StringIndexOutOfBoundsException e) {
			//e.printStackTrace();
			loginView.informUsernameTooShort();
			this.run();
		} catch (SQLException e) {
			//e.printStackTrace();
			loginView.informConnIssue();
			System.exit(1); // exits() app
		} catch (AtorTypeNotValidException e) {
			//e.printStackTrace();
			loginView.informInvalidType();
			this.run();
		} catch (AtorNotExistsException e) {
			//e.printStackTrace();
			loginView.informUsernameNotExists();
			this.run();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}