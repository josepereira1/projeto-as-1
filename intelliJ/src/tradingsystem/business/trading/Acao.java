package tradingsystem.business.trading;

public class Acao extends Ativo {

	@Override
	public String toString() {
		return "Acao{" +
				"id='" + id + '\'' +
				", designacao='" + designacao + '\'' +
				", valorVenda=" + valorVenda +
				", valorCompra=" + valorCompra +
				'}';
	}

	public Ativo clone() {
		// TODO - implement Acao.clone
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// TODO - implement Acao.equals
		throw new UnsupportedOperationException();
	}
}