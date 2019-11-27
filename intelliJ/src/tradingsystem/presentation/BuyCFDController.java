package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.StockIdNotExistsException;

import java.io.IOException;
import java.sql.SQLException;

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
            buyCFDView.displayCurrentPrice(model.business.getValorAtualAtivo(buyCFDView.organizationId));

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
            // e.printStackTrace();
        }

    }

}