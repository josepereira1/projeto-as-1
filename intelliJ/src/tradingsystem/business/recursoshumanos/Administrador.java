package tradingsystem.business.recursoshumanos;

public class Administrador extends Utilizador {

	@Override
	public String toString() {
		return "Administrador{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public Utilizador clone() {
		// TODO - implement Administrador.clone
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// TODO - implement Administrador.equals
		throw new UnsupportedOperationException();
	}

}