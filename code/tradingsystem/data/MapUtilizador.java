package tradingsystem.data;

import tradingsystem.business.recursoshumanos.Utilizador;

public interface MapUtilizador {

	/**
	 * 
	 * @param key
	 * @param value
	 */
	Utilizador put(String key, Utilizador value);

	/**
	 * 
	 * @param key
	 */
	Utilizador get(Object key);

}