package tradingsystem.business.recursoshumanos;

public interface IAtor {

	/**
	 *
	 * @param username
	 * @param password
	 */
	boolean autenticar(String username, String password);

	/**
	 * Return username.
	 * @return Return username
	 */
	String getUsername();

	/**
	 * Set username.
	 * @param username username
	 */
	void setUsername(String username);

	/**
	 * Return password.
	 * @return return password
	 */
	String getPassword();

	/**
	 * Set password.
	 * @param password password
	 */
	void setPassword(String password);

}