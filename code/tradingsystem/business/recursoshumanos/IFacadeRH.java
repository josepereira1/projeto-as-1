package tradingsystem.business.recursoshumanos;

public interface IFacadeRH {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	boolean autenticarUtilizador(String username, String password);

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	void registarUtilizador(String username, String password, float plafond);

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	void setFundos(String username, float valor);

}