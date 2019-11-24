package tradingsystem.business.recursoshumanos;

public interface IFacadeRecursosHumanos {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	IAtor autenticarUtilizador(String username, String password);

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	IAtor registarUtilizador(String username, String password, float plafond);

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	void setFundos(String username, float valor);
}