package tradingsystem.business.trading;

public interface ITradingAbstractFactory {

	/**
	 * 
	 * @param type
	 */
	IAtivo createAtivo(String type);

	/**
	 * 
	 * @param type
	 */
	ICFD createCFD(String type);
}