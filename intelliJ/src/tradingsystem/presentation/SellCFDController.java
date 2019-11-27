package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.InvalidInputException;
import tradingsystem.business.StockIdNotExistsException;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class SellCFDController implements Runnable {

	private SellCFDView sellCFDView;
	private tradingsystem.TradingSystem model;
	private static final int TYPE_OF_CFD = 0;	//	SELL

	public SellCFDController() throws SQLException, ClassNotFoundException {
		sellCFDView = new SellCFDView();
		model = TradingSystem.getInstance();
	}

	public void run() {
		try {
			sellCFDView.organization();
			sellCFDView.displayCurrentPrice(model.business.getValorAtualAtivo(sellCFDView.organizationId, 0), model.business.getValorAtualAtivo(sellCFDView.organizationId, 1));
			ICFD cfd = model.business.abrirCFD(sellCFDView.organizationId, model.ator.getUsername(), TYPE_OF_CFD, -1, -1, sellCFDView.units).get();
			//buyCFDView.displayCurrentProfit(cfd.getBalanco(model.business.getValorAtualAtivo(buyCFDView.organizationId, 1)));    //	display profit	//TODO USAR ISTO NO ENCERRAR!!!

			sellCFDView.sucess();

			new HomeController().run();

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
		}
	}

}