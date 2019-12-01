package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.CFDNotExistsException;
import tradingsystem.business.InvalidInputException;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class SetLimitsCFDController implements Runnable {

	private static SetLimitsCFDController setLimitsCFDController;

	private SetLimitsCFDView setLimitsCFDView;
	private TradingSystem model;

	private SetLimitsCFDController() throws SQLException, ClassNotFoundException {
		setLimitsCFDView = new SetLimitsCFDView();
		model = TradingSystem.getInstance();
	}

	public static SetLimitsCFDController getInstance() throws SQLException, ClassNotFoundException {
		if (setLimitsCFDController == null) setLimitsCFDController = new SetLimitsCFDController();
		return setLimitsCFDController;
	}

	public void run() {
		try {
			setLimitsCFDView.setLimit();
			model.business.setCFDlimits(setLimitsCFDView.idCFD, setLimitsCFDView.takeProfit, setLimitsCFDView.stopLess);
			setLimitsCFDView.sucess();
			HomeController.getInstance().run();
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.exit(1);
		} catch (ExecutionException e) {
			//e.printStackTrace();
			System.exit(1);
		} catch (CFDNotExistsException e) {
			setLimitsCFDView.CFDIdNotExists();
			this.run();    //	restart method
		} catch (SQLException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			setLimitsCFDView.error();
		} catch (InvalidInputException e) {
			//e.printStackTrace();
			setLimitsCFDView.inputError();
			this.run();
		}
	}

}