package tradingsystem.business.trading;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface IFacadeTrading {

	Collection<IAtivo> getAtivos();

	/**
	 * 
	 * @param idAtivo
	 * @param username
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws ExecutionException, InterruptedException;

	/**
	 * 
	 * @param id
	 */
	void encerrarCFD(String id);

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
	 * @param id
	 */
	float getValorAtualAtivo(String id);

	/**
	 * 
	 * @param idCFD
	 */
	float getBalanco(String idCFD);

}