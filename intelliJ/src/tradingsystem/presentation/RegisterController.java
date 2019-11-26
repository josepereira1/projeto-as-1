package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorExistsException;
import tradingsystem.business.AtorTypeNotValidException;

import java.sql.SQLException;

public class RegisterController {

	private TradingSystem model;
	private RegisterView registerView;

	public RegisterController() throws SQLException, ClassNotFoundException {
		this.model = TradingSystem.getInstance();
		this.registerView = new RegisterView();
	}

	public void run() {
		try {
			registerView.register();
			if (registerView.password.equals(registerView.confirmPassword) == false) {
				registerView.informPassowrdNotMatch();
				this.run();
			}
			if (registerView.plafond < 200f) {
				registerView.informInvalidPlafondNumber();
				this.run();
			}
			model.ator = model.business.registarUtilizador(registerView.username, registerView.password, registerView.plafond);
			new HomeController().run();
		} catch (StringIndexOutOfBoundsException e) {
			//e.printStackTrace();
			registerView.informUsernameTooShort();
			this.run();
		} catch (SQLException e) {
			//e.printStackTrace();
			registerView.informConnIssue();
			System.exit(1); // exits() app
		} catch (AtorTypeNotValidException e) {
			//e.printStackTrace();
			registerView.informInvalidType();
			this.run();
		} catch (AtorExistsException e) {
			//e.printStackTrace();
			registerView.informUsernameAlreadyExists();
			this.run();
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			registerView.informInvalidPlafondNumber();
			this.run();
		}
	}

}