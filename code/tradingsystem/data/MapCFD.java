package tradingsystem.data;

import tradingsystem.business.trading.CFD;

import java.util.Collection;

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

	Collection<CFD> values();

}