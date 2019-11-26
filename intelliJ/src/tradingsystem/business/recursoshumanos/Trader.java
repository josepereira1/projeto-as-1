package tradingsystem.business.recursoshumanos;

public class Trader extends Utilizador {

	private float plafond;

	@Override
	public String toString() {
		return "Trader{" +
				"plafond=" + plafond +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public Utilizador clone() {
		// TODO - implement Trader.clone
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// TODO - implement Trader.equals
		throw new UnsupportedOperationException();
	}

	public float getPlafond() {
		return plafond;
	}

	public void setPlafond(float plafond) {
		this.plafond = plafond;
	}
}