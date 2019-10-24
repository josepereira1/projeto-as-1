package tradingsystem.data;

import tradingsystem.JSONObject;
import tradingsystem.business.trading.Ativo;

import java.util.Collection;

public class AtivoDAO implements MapAtivo {

	private static String APIToken;

	public Collection<Ativo> values() {
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