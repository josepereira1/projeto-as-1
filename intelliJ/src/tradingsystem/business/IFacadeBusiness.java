package tradingsystem.business;

import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;

import java.sql.SQLException;
import java.util.Collection;

public interface IFacadeBusiness {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	IAtor autenticarUtilizador(int username, String password);

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	IAtor registarUtilizador(String username, String password, float plafond);

	/**
	 * 
	 * @param idAtivo
	 * @param username
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos);

	/**
	 * 
	 * @param id
	 */
	void encerrarCFD(String id);

	Collection<IAtivo> getAtivos();

	/**
	 * 
	 * @param username
	 */
	Collection<ICFD> getPortfolio(String username);

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

	/**
	 * 
	 * @param id
	 */
	float getValorAtualAtivo(String id);

	/**
	 * 
	 * @param idCFD
	 */
	float getBalanco(String idCFD);

    void initAutoCloseCFDs(String username) throws SQLException, AtorTypeNotValidException, AtorExistsException;
}