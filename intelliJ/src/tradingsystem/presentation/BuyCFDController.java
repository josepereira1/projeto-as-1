package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockIdNotExistsException;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class BuyCFDController implements Runnable {

	private BuyCFDView buyCFDView;
	private TradingSystem model;

	public BuyCFDController() throws SQLException, ClassNotFoundException {
		buyCFDView = new BuyCFDView();
		model = TradingSystem.getInstance().getInstance();
	}

	public void run() {
		buyCFDView.organization();
        try {
			buyCFDView.displayCurrentPrice(model.business.getValorAtualAtivo(buyCFDView.organizationId, 0), model.business.getValorAtualAtivo(buyCFDView.organizationId, 1));
			ICFD cfd = model.business.abrirCFD(buyCFDView.organizationId, model.ator.getUsername(), 1, -1, -1, buyCFDView.units).get();
			//buyCFDView.displayCurrentProfit(cfd.getBalanco(model.business.getValorAtualAtivo(buyCFDView.organizationId, 1)));    //	display profit	//TODO USAR ISTO NO ENCERRAR!!!

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
		}
	}

}