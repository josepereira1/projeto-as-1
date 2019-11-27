package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.*;
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
			e.printStackTrace();
        	buyCFDView.error();
			System.exit(1);
		} catch (ExecutionException e) {
			e.printStackTrace();
        	buyCFDView.error();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
        	buyCFDView.error();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
        	buyCFDView.error();
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
        	buyCFDView.error();
			System.exit(1);
		} catch (CFDTypeNotValidException e) {
			e.printStackTrace();
        	buyCFDView.CFDTypeNotExists();
			System.exit(1);
		} catch (StockIdNotExistsException e) {
			e.printStackTrace();
        	buyCFDView.stockIdNotExists();
			this.run();
		} catch (InvalidInputException e) {
			//e.printStackTrace();
			buyCFDView.inputError();
			this.run();
		} catch (NoFundsToCFDException e) {
			//e.printStackTrace();
			buyCFDView.noFunds();
			this.run();
		} catch (AtorNotExistsException e) {
			//e.printStackTrace();
			buyCFDView.error();
			System.exit(1);
		}
	}

}