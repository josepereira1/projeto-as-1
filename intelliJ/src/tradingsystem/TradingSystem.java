package tradingsystem;

import tradingsystem.business.FacadeBusiness;
import tradingsystem.business.IFacadeBusiness;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.presentation.InitController;

public class TradingSystem {

	public IFacadeBusiness business;
	public IAtor ator;
	private static TradingSystem tradingSystem;

	private TradingSystem() {
		this.business = FacadeBusiness.getInstance();
		this.ator = null;
	}

	public void initAutoCloseCFDs(String username) {
		this.business.initAutoCloseCFDs(username);
	}

	public static TradingSystem getInstance() {
		if (tradingSystem == null) tradingSystem = new TradingSystem();
		return tradingSystem;
	}

	/**
	 * Starts
	 * @param args
	 */
	public static void main(String[] args) {
		new InitController().run();
	}

}