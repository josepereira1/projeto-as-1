package tradingsystem.data;

import tradingsystem.business.AtorTypeNotValidException;
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
	float getValorAtualAtivo(String id) throws IOException;

	/**
	 * 
	 * @param id
	 */
	Future<ICFD> getCFD(String id);

	/**
	 * 
	 * @param id
	 */
	void removeCFD(String id);

	/**
	 * 
	 * @param cfd
	 */
	void putCFD(ICFD cfd);

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
	Future<Collection<ICFD>> getCFDs(String username);

	/**
	 *
	 * @param username
	 * @return
	 */
	Future<Collection<String>> getCFDsIds(String username);

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
	Future<Float> getTakeProfit(String idCFD);

	/**
	 * 
	 * @param idCFD
	 */
	Future<Float> getStopLess(String idCFD);

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

	Future<Void> updateEndDateCFD(String id, LocalDateTime endDate);

}