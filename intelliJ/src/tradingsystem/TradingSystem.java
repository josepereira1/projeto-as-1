package tradingsystem;

import tradingsystem.business.FacadeBusiness;
import tradingsystem.business.IFacadeBusiness;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.presentation.InitController;

import java.sql.SQLException;


public class TradingSystem {

	public IFacadeBusiness business;
	public IAtor ator;
	private static TradingSystem tradingSystem;

	private TradingSystem() throws SQLException, ClassNotFoundException {
		this.business = FacadeBusiness.getInstance();
		this.ator = null;
	}

	public static TradingSystem getInstance() throws SQLException, ClassNotFoundException {
		if (tradingSystem == null) tradingSystem = new TradingSystem();
		return tradingSystem;
	}

	/**
	 * Starts
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new InitController().run();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}