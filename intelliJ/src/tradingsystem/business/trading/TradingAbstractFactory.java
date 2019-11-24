package tradingsystem.business.trading;

public class TradingAbstractFactory implements ITradingAbstractFactory {

	private static ITradingAbstractFactory abstractFactory;

	/**
	 * 
	 * @param type
	 */
	public IAtivo createAtivo(String type) {
		// TODO - implement TradingAbstractFactory.createAtivo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param type
	 */
	public ICFD createCFD(String type) {
		// TODO - implement TradingAbstractFactory.createCFD
		throw new UnsupportedOperationException();
	}

	public static ITradingAbstractFactory getInstance() {
		// TODO - implement TradingAbstractFactory.getInstance
		throw new UnsupportedOperationException();
	}

}