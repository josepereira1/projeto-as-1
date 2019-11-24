package tradingsystem.business.trading;

import java.time.LocalDateTime;

public class CFD implements ICFD {

	public String id;
	public String idAtivo;
	public int tipo;
	public String username;
	public float stopLess;
	public float takeProfit;
	public LocalDateTime dataExpiracao;
	public LocalDateTime dataAbertura;
	public LocalDateTime dataEncerramento;
	public int numeroDeAtivos;
	public float valorInicial;
	public float valorInvestido;

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

	/**
	 * 
	 * @param valorAtualAtivo
	 * @param numeroDeAtivos
	 * @param valorInvestido
	 */
	public static float getBalanco(float valorAtualAtivo, int numeroDeAtivos, int valorInvestido) {
		// TODO - implement CFD.getBalanco
		throw new UnsupportedOperationException();
	}

}