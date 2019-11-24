package tradingsystem.business.trading;

import java.time.LocalDateTime;

public class CFD implements ICFD {

	private String id;
	private String idAtivo;
	private int tipo;
	private String username;
	private float stopLess;
	private float takeProfit;
	private LocalDateTime dataExpiracao;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataEncerramento;
	private int numeroDeAtivos;
	private float valorInicial;
	private float valorInvestido;

	//TODO	MUDAR AS VARIÁVEIS NO DIAGRAMA PARA PRIVADAS

	/**
	 *
	 * @return
	 */
	public String toString() {
		// TODO - implement CFD.toString
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @return
	 */
	public CFD clone() {
		// TODO - implement CFD.clone
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param o
	 * @return
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
	 * @return
	 */
	public static float getBalanco(float valorAtualAtivo, int numeroDeAtivos, int valorInvestido) {
		// TODO - implement CFD.getBalanco
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}


	/**
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 */
	public String getIdAtivo() {
		return idAtivo;
	}

	/**
	 *
	 * @param idAtivo
	 */
	public void setIdAtivo(String idAtivo) {
		this.idAtivo = idAtivo;
	}

	/**
	 *
	 * @return
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 *
	 * @param tipo
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 *
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *
	 * @return
	 */
	public float getStopLess() {
		return stopLess;
	}

	/**
	 *
	 * @param stopLess
	 */
	public void setStopLess(float stopLess) {
		this.stopLess = stopLess;
	}

	/**
	 *
	 * @return
	 */
	public float getTakeProfit() {
		return takeProfit;
	}

	/**
	 *
	 * @param takeProfit
	 */
	public void setTakeProfit(float takeProfit) {
		this.takeProfit = takeProfit;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	/**
	 *
	 * @param dataExpiracao
	 */
	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	/**
	 *
	 * @param dataAbertura
	 */
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 *
	 * @param dataEncerramento
	 */
	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 *
	 * @return
	 */
	public int getNumeroDeAtivos() {
		return numeroDeAtivos;
	}

	/**
	 *
	 * @param numeroDeAtivos
	 */
	public void setNumeroDeAtivos(int numeroDeAtivos) {
		this.numeroDeAtivos = numeroDeAtivos;
	}

	/**
	 *
	 * @return
	 */
	public float getValorInicial() {
		return valorInicial;
	}

	/**
	 *
	 * @param valorInicial
	 */
	public void setValorInicial(float valorInicial) {
		this.valorInicial = valorInicial;
	}

	/**
	 *
	 * @return
	 */
	public float getValorInvestido() {
		return valorInvestido;
	}

	/**
	 *
	 * @param valorInvestido
	 */
	public void setValorInvestido(float valorInvestido) {
		this.valorInvestido = valorInvestido;
	}
}