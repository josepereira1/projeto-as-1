package tradingsystem.data;

import org.json.JSONObject;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ITradingAbstractFactory;

import java.util.Collection;


public class AtivoDAO {

	private static String APIToken;
	private ITradingAbstractFactory tradingAbastractFactory;

	public Collection<IAtivo> values() {
		// TODO - implement AtivoDAO.values
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param url
	 */
	private static JSONObject RESTGet(String url) {
		// TODO - implement AtivoDAO.RESTGet
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public float getValorAtual(String id) {
		// TODO - implement AtivoDAO.getValorAtual
		throw new UnsupportedOperationException();
	}

}