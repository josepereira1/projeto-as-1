package tradingsystem.business.recursoshumanos;

public class Utilizador implements IAtor {

	private String username;
	private String password;

	/**
	 * Returns true if given credentials matches, otherwise returns false.
	 * @param username id of Utilizador.
	 * @param password password of Utilizador.
	 */
	public boolean autenticar(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
}