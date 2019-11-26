package tradingsystem.business.trading;

import java.time.LocalDateTime;

public class CFD implements ICFD {

	private String id;
	private String idAtivo;
	private int tipo;
	private String username;
	private float stopLess;
	private float takeProfit;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataEncerramento;
	private int numeroDeAtivos;
	private float valorInicial;
	private float valorInvestido;

	public CFD(){

	}

	//TODO	MUDAR AS VARIÁVEIS NO DIAGRAMA PARA PRIVADAS

	/**
	 *
	 * @return
	 */
	public String toString() {
		// TODO - implement CFD.toString
		StringBuilder sb = new StringBuilder("CFD={");
		sb.append("id=").append(id);
		sb.append(";idAtivo=").append(idAtivo);
		sb.append(";tipo=").append(tipo);
		sb.append(";username=").append(username);
		sb.append(";stopLess=").append(stopLess);
		sb.append(";takeProfit=").append(takeProfit);
		sb.append(";dataAbertura=").append(dataAbertura);
		sb.append(";dataEncerramento=").append(dataEncerramento);
		sb.append(";numeroDeAtivos=").append(numeroDeAtivos);
		sb.append(";valorInicial=").append(valorInicial);
		sb.append(";valorInvestido=").append(valorInvestido).append("};");
		return sb.toString();
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
	 *	Calculate profit of CFD
	 * @param valorAtualAtivo current value of stock
	 * @param numeroDeAtivos number of stock
	 * @param valorInvestido invested value
	 */
	public static float getBalanco(float valorAtualAtivo, int numeroDeAtivos, int valorInvestido) {
		return valorInvestido-(valorAtualAtivo*numeroDeAtivos);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getIdAtivo() {
		return idAtivo;
	}

	@Override
	public void setIdAtivo(String idAtivo) {
		this.idAtivo = idAtivo;
	}

	@Override
	public int getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
	public float getStopLess() {
		return stopLess;
	}

	@Override
	public void setStopLess(float stopLess) {
		this.stopLess = stopLess;
	}

	@Override
	public float getTakeProfit() {
		return takeProfit;
	}

	@Override
	public void setTakeProfit(float takeProfit) {
		this.takeProfit = takeProfit;
	}

	@Override
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	@Override
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	@Override
	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	@Override
	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	@Override
	public int getNumeroDeAtivos() {
		return numeroDeAtivos;
	}

	@Override
	public void setNumeroDeAtivos(int numeroDeAtivos) {
		this.numeroDeAtivos = numeroDeAtivos;
	}

	@Override
	public float getValorInicial() {
		return valorInicial;
	}

	@Override
	public void setValorInicial(float valorInicial) {
		this.valorInicial = valorInicial;
	}

	@Override
	public float getValorInvestido() {
		return valorInvestido;
	}

	@Override
	public void setValorInvestido(float valorInvestido) {
		this.valorInvestido = valorInvestido;
	}
}