package tradingsystem.presentation;

import java.sql.SQLException;

public class InitController implements Runnable {

	private InitView initView;
	private static InitController initController;

	private InitController() {
		this.initView = new InitView();
	}

	public static InitController getInstance() {
		if (initController == null) initController = new InitController();
		return initController;
	}

	public void run() {
		try {
			initView.selectAction(); // display action selection
			switch (initView.action) {
				case 0:
					LoginController.getInstance().run();
					break;
				case 1:
					RegisterController.getInstance().run();
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