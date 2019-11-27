package tradingsystem.business;

import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface IFacadeBusiness {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	IAtor autenticarUtilizador(String username, String password) throws AtorNotExistsException, SQLException, AtorTypeNotValidException;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	IAtor registarUtilizador(String username, String password, float plafond) throws SQLException, AtorTypeNotValidException, AtorExistsException;

	/**
	 * 
	 * @param idAtivo
	 * @param username
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws InterruptedException, ExecutionException, StockIdNotExistsException, IOException;

	/**
	 * 
	 * @param id
	 */
	void encerrarCFD(String id, String username) throws InterruptedException, ExecutionException, IOException, CFDNotExistsException, AtorNotExistsException, SQLException, AtorTypeNotValidException;

	Collection<IAtivo> getAtivos() throws AtorTypeNotValidException, IOException, StockTypeNotValidException;

	/**
	 * 
	 * @param username
	 */
	Collection<ICFD> getPortfolio(String username) throws ExecutionException, InterruptedException;

	/**
	 * 
	 * @param id
	 * @param TP
	 * @param SL
	 */
	void setCFDlimits(String id, float TP, float SL) throws InterruptedException, ExecutionException, CFDNotExistsException;

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	void addFundos(String username, float valor) throws AtorNotExistsException, SQLException, AtorTypeNotValidException;

	/**
	 * 
	 * @param id
	 */
	float getValorAtualAtivo(String id) throws IOException, StockIdNotExistsException;

	/**
	 * 
	 * @param idCFD
	 */
	float getBalanco(String idCFD) throws InterruptedException, ExecutionException, IOException, CFDNotExistsException;

    void initAutoCloseCFDs(String username) throws SQLException, AtorTypeNotValidException, AtorExistsException, AtorNotExistsException;

}