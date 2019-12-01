package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.*;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class SellCFDController implements Runnable {

	private static SellCFDController sellCFDController;

	private SellCFDView sellCFDView;
	private TradingSystem model;
	private static final int TYPE_OF_CFD = 0;	//	SELL

	private SellCFDController() throws SQLException, ClassNotFoundException {
		sellCFDView = new SellCFDView();
		model = TradingSystem.getInstance();
	}

	public static SellCFDController getInstance() throws SQLException, ClassNotFoundException {
		if (sellCFDController == null) sellCFDController = new SellCFDController();
		return sellCFDController;
	}

	public void run() {
		try {
			sellCFDView.organization();
			sellCFDView.displayCurrentPrice(model.business.getValorAtualAtivo(sellCFDView.organizationId, 0), model.business.getValorAtualAtivo(sellCFDView.organizationId, 1));
			ICFD cfd = model.business.abrirCFD(sellCFDView.organizationId, model.ator.getUsername(), TYPE_OF_CFD, -1, -1, sellCFDView.units).get();
			//buyCFDView.displayCurrentProfit(cfd.getBalanco(model.business.getValorAtualAtivo(buyCFDView.organizationId, 1)));    //	display profit	//TODO USAR ISTO NO ENCERRAR!!!

			sellCFDView.sucess();

			HomeController.getInstance().run();

		} catch (ClassNotFoundException e) {
			sellCFDView.error();
			System.exit(1);
		} catch (ExecutionException e) {
			sellCFDView.error();
			System.exit(1);
		} catch (SQLException e) {
			sellCFDView.error();
			System.exit(1);
		} catch (IOException e) {
			sellCFDView.error();
			System.exit(1);
		} catch (InterruptedException e) {
			sellCFDView.error();
			System.exit(1);
		} catch (CFDTypeNotValidException e) {
			sellCFDView.CFDTypeNotExists();
			System.exit(1);
		} catch (StockIdNotExistsException e) {
			sellCFDView.stockIdNotExists();
			this.run();
		} catch (InvalidInputException e) {
			//e.printStackTrace();
			sellCFDView.inputError();
			this.run();
		} catch (NoFundsToCFDException e) {
			//e.printStackTrace();
			sellCFDView.noFunds();
			this.run();
		} catch (AtorNotExistsException e) {
			//e.printStackTrace();
			sellCFDView.error();
			System.exit(1);
		}
	}

}