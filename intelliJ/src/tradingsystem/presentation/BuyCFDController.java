package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.InvalidInputException;
import tradingsystem.business.StockIdNotExistsException;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class BuyCFDController implements Runnable {

	private BuyCFDView buyCFDView;
	private TradingSystem model;
	private static final int TYPE_OF_CFD = 1;	//	SELL

	public BuyCFDController() throws SQLException, ClassNotFoundException {
		buyCFDView = new BuyCFDView();
		model = TradingSystem.getInstance().getInstance();
	}

	public void run() {
        try {
			buyCFDView.organization();
        	buyCFDView.displayCurrentPrice(model.business.getValorAtualAtivo(buyCFDView.organizationId, 0), model.business.getValorAtualAtivo(buyCFDView.organizationId, 1));
			ICFD cfd = model.business.abrirCFD(buyCFDView.organizationId, model.ator.getUsername(), TYPE_OF_CFD, -1, -1, buyCFDView.units).get();
			//buyCFDView.displayCurrentProfit(cfd.getBalanco(model.business.getValorAtualAtivo(buyCFDView.organizationId, 1)));    //	display profit	//TODO USAR ISTO NO ENCERRAR!!!
			buyCFDView.sucess();
			new HomeController().run();

		} catch (ClassNotFoundException e) {
			buyCFDView.error();
			System.exit(1);
		} catch (ExecutionException e) {
			buyCFDView.error();
			System.exit(1);
		} catch (SQLException e) {
			buyCFDView.error();
			System.exit(1);
		} catch (IOException e) {
			buyCFDView.error();
			System.exit(1);
		} catch (InterruptedException e) {
			buyCFDView.error();
			System.exit(1);
		} catch (CFDTypeNotValidException e) {
			buyCFDView.CFDTypeNotExists();
			System.exit(1);
		} catch (StockIdNotExistsException e) {
			buyCFDView.stockIdNotExists();
			this.run();
		} catch (InvalidInputException e) {
			//e.printStackTrace();
			buyCFDView.inputError();
			this.run();
		}
	}

}