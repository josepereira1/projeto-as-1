package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorExistsException;
import tradingsystem.business.AtorTypeNotValidException;

import java.sql.SQLException;

public class RegisterController {

	private static RegisterController registerController;

	private TradingSystem model;
	private RegisterView registerView;

	private RegisterController() throws SQLException, ClassNotFoundException {
		this.model = TradingSystem.getInstance();
		this.registerView = new RegisterView();
	}

	public static RegisterController getInstance() throws SQLException, ClassNotFoundException {
		if (registerController == null) registerController = new RegisterController();
		return registerController;
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
			registerView.username = "t_" + registerView.username;
			model.ator = model.business.registarUtilizador(registerView.username, registerView.password, registerView.plafond);
			registerView.informUsername();
			HomeController.getInstance().run();
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}