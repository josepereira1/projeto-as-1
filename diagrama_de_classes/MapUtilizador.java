package diagrama_de_classes;

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