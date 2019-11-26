package tradingsystem.data;

import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.Future;

public interface IFacadeData {

	/**
	 * 
	 * @param username
	 */
	public IAtor getUtilizador(String username, String userType) throws SQLException, IAtorTypeNotValid;

	Collection<IAtivo> getAtivos() throws IOException;

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
	void putUtilizador(IAtor utilizador) throws SQLException, IAtorTypeNotValid;

	/**
	 * 
	 * @param username
	 */
	Future<Collection<ICFD>> getCFDs(String username);

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
	boolean containsUtilizador(String username, String userType) throws SQLException, IAtorTypeNotValid;

	/**
	 * 
	 * @param idCFD
	 */
	Future<Boolean> containsCFD(String idCFD);

}