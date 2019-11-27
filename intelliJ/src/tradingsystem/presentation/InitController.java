package tradingsystem.presentation;

import java.sql.SQLException;

public class InitController implements Runnable {

	private InitView initView;

	public InitController() {
		this.initView = new InitView();
	}

	public void run() {
		try {
			initView.selectAction(); // display action selection
			switch (initView.action) {
				case 0:
					new LoginController().run();
					break;
				case 1:
					new RegisterController().run();
					break;
				default:
					initView.informInvalidAction(); // prints warning message
					this.run(); // re-evokes this controller until a valid option is chosen
					break;
			}
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			initView.informInvalidAction();
			this.run();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}