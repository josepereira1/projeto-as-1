package tradingsystem.business;

import tradingsystem.business.trading.Ativo;

import java.util.Collection;

public interface IFacadeBusiness {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	boolean autenticarUtilizador(int username, String password);

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	void registarUtilizador(String username, String password, float plafond);

	/**
	 * 
	 * @param idAtivo
	 * @param usernameVendedor
	 * @param usernameComprador
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	void abrirCFD(String idAtivo, String usernameVendedor, String usernameComprador, int tipo, float stopLess, float takeProfit, int numeroDeAtivos);

	/**
	 * 
	 * @param id
	 */
	void encerrarCFD(String id);

	Collection<Ativo> getAtivos();

	/**
	 * 
	 * @param username
	 */
	Collection<Ativo> getPortfolio(String username);

	/**
	 * 
	 * @param id
	 * @param TP
	 * @param SL
	 */
	void setCFDlimits(String id, float TP, float SL);

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	void setFundos(String username, float valor);

}