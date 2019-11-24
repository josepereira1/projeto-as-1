package tradingsystem.data;

import org.json.JSONObject;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ITradingAbstractFactory;

import java.util.Collection;


public class AtivoDAO {

	private static String APIToken = "IwkmgAv406p5lc2mJ3vXkww56P6cw9QIjPmtpW4I4e4weBztvCsji44H9NLr";
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