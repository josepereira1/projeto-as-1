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
            buyCFDView.displayCurrentPrice(model.business.getValorAtualAtivo(buyCFDView.organizationId, 0),model.business.getValorAtualAtivo(buyCFDView.organizationId, 1));
			ICFD cfd = model.business.abrirCFD(buyCFDView.organizationId, model.ator.getUsername(), 1, -1, -1 ,buyCFDView.units).get();
			buyCFDView.displayCurrentProfit(cfd.getBalanco(model.business.getValorAtualAtivo(buyCFDView.organizationId, 1)));	//	display profit

			new HomeController().run();

        } catch (IOException e) {
            buyCFDView.error();
            try {
                new HomeController();
            } catch (SQLException ex) {
                //ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                //ex.printStackTrace();
            }
            //e.printStackTrace();
        } catch (StockIdNotExistsException e) {
           buyCFDView.stockIdNotExists();
           this.run();
            //e.printStackTrace();
        } catch (CFDTypeNotValidException e) {
			buyCFDView.CFDTypeNotExists();
			try {
				new HomeController().run();
			} catch (SQLException ex) {
				buyCFDView.error();
				//ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				buyCFDView.error();
				//ex.printStackTrace();
			}
			//e.printStackTrace();
		} catch (InterruptedException e) {
			//e.printStackTrace();
			buyCFDView.error();
		} catch (ExecutionException e) {
			//e.printStackTrace();
			buyCFDView.error();
		} catch (SQLException e) {
			buyCFDView.error();
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			buyCFDView.error();
			//e.printStackTrace();
		}
	}

}