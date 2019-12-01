package tradingsystem.business.trading;

import tradingsystem.business.StockTypeNotValidException;

public interface ITradingAbstractFactory {

	/**
	 * 
	 * @param type
	 */
	IAtivo createAtivo(String type) throws StockTypeNotValidException;

	/**
	 * 
	 * @param type
	 */
	ICFD createCFD(String type);
}