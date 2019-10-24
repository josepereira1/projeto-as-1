package diagrama_de_classes;

public class CFD {

	public String id;
	public String idAtivo;
	public int tipo;
	public String usernameVendedor;
	public float stopLess;
	public String usernameComprador;
	public float takeProfit;
	public LocalDateTime dataExpiracao;
	public LocalDateTime dataAbertura;
	public LocalDateTime dataEncerramento;
	public int numeroDeAtivos;
	public float valorInicial;
	private float valorAtual;
	public float valorInvestido;
	private float balanco;

	public float getValorAtual() {
		return this.valorAtual;
	}

	public float getBalanco() {
		return this.balanco;
	}

	public String toString() {
		// TODO - implement CFD.toString
		throw new UnsupportedOperationException();
	}

	public CFD clone() {
		// TODO - implement CFD.clone
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// TODO - implement CFD.equals
		throw new UnsupportedOperationException();
	}

}