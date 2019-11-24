package tradingsystem.data;

import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.recursoshumanos.Utilizador;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;

import java.util.Collection;
import java.util.concurrent.Future;

public interface IFacadeData {

	/**
	 * 
	 * @param username
	 */
	IAtor getUtilizador(String username);

	Collection<IAtivo> getAtivos();

	/**
	 * 
	 * @param id
	 */
	float getValorAtualAtivo(String id);

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
	void putUtilizador(Utilizador utilizador);

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
	void addFundos(String username, float valor);

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
	float getValorInvestidoCFD(String idCFD);

	int getNumeroDeAtivosCFD();

	/**
	 * 
	 * @param username
	 */
	boolean containsUtilizador(String username);

	/**
	 * 
	 * @param id
	 */
	boolean containsCFD(String id);

}