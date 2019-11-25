package tradingsystem.business.recursoshumanos;

public class Trader extends Utilizador {

	private float plafond;

	public String toString() {
		StringBuilder sb = new StringBuilder("Trader={");
		sb.append("username=").append(username);
		sb.append(";password=").append(password);
		sb.append(";plafond=").append(plafond).append("};");
		return sb.toString();
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