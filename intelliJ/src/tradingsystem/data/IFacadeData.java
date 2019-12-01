package tradingsystem.data;

import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockTypeNotValidException;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.Future;

public interface IFacadeData {

	/**
	 * 
	 * @param username
	 */
	IAtor getUtilizador(String username, String userType) throws SQLException, AtorTypeNotValidException;

	Collection<IAtivo> getAtivos() throws IOException, StockTypeNotValidException;

	/**
	 * 
	 * @param id
	 */
	float getValorAtualAtivo(String id, int typeOfCFD) throws IOException, CFDTypeNotValidException;

	/**
	 * 
	 * @param cfd
	 */
	Future<ICFD> putCFD(ICFD cfd);

	/**
	 * 
	 * @param utilizador
	 */
	void putUtilizador(IAtor utilizador) throws SQLException, AtorTypeNotValidException;

	/**
	 *
     * @param username
     * @return
     */
	Future<Collection<ICFD>> getCFDsOpen(String username);

	/**
	 *  @param id
	 * @param TP
	 * @param SL
	 * @return
	 */
	Future<Void> setCFDlimits(String id, float TP, float SL);

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	void addFundos(String username, float valor) throws SQLException;

	/**
	 * 
	 * @param idCFD
	 */
	Future<Float> getValorInvestidoCFD(String idCFD);

	Future<Integer> getNumeroDeAtivosCFD(String idCFD);

	/**
	 * 
	 * @param username
	 */
	boolean containsUtilizador(String username, String userType) throws SQLException, AtorTypeNotValidException;

	/**
	 * 
	 * @param idCFD
	 */
	Future<Boolean> containsCFD(String idCFD);

	Future<String> getNextId();

	boolean containsAtivo(String id) throws IOException;

	void updateEndDateCFD(String id, LocalDateTime endDate);

	Future<String> getIdAtivoDoCFD(String idCFD);

	Future<Integer> getTipoCFD(String id);

	float getPlafond(String username) throws SQLException, AtorNotExistsException;

	Future<SubjectCFD> getCFDSubject();

	SubjectAtivo getSubjectAtivo();
}