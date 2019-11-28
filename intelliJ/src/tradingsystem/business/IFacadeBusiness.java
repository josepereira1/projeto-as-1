package tradingsystem.business;

import tradingsystem.SubjectAtivo;
import tradingsystem.SubjectCFD;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
	Future<ICFD> abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws InterruptedException, ExecutionException, StockIdNotExistsException, IOException, CFDTypeNotValidException, NoFundsToCFDException, SQLException, AtorNotExistsException;

	/**
	 * 
	 * @param id
	 */
	void encerrarCFD(String id, String username) throws InterruptedException, ExecutionException, IOException, CFDNotExistsException, AtorNotExistsException, SQLException, AtorTypeNotValidException, CFDTypeNotValidException;

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
	public float getValorAtualAtivo(String id, int typeOfCFD) throws IOException, StockIdNotExistsException, CFDTypeNotValidException;

	/*
	float getBalanco(String idCFD) throws InterruptedException, ExecutionException, IOException, CFDNotExistsException, CFDTypeNotValidException;
	*/

    void initAutoCloseCFDs(String username) throws SQLException, AtorTypeNotValidException, AtorExistsException, AtorNotExistsException;

	float getPlafond(String username) throws AtorNotExistsException, SQLException;

	Future<SubjectCFD> getCFDSubject();

	SubjectAtivo getSubjectAtivo();

}