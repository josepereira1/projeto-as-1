package diagrama_de_classes;

public interface MapCFD {

	/**
	 * 
	 * @param key
	 * @param value
	 */
	CFD put(String key, CFD value);

	/**
	 * 
	 * @param key
	 */
	CFD remove(Object key);

}