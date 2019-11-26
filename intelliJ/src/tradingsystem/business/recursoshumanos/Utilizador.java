package tradingsystem.business.recursoshumanos;

public class Utilizador implements IAtor {

	public String username;
	public String password;

	public String toString() {
		StringBuilder sb =  new StringBuilder("Utilizador={");
		sb.append("username=").append(username);
		sb.append(";password=").append(password).append("};");
		return sb.toString();
	}

	public Utilizador clone() {
		// TODO - implement Utilizador.clone
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// TODO - implement Utilizador.equals
		throw new UnsupportedOperationException();
	}

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