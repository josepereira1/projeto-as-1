package tradingsystem.business.trading;

import tradingsystem.business.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface IFacadeTrading {

	Collection<IAtivo> getAtivos() throws IOException, AtorTypeNotValidException, StockTypeNotValidException;

	/**
	 * 
	 * @param idAtivo
	 * @param username
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	Future<ICFD> abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws ExecutionException, InterruptedException, IOException, StockIdNotExistsException, CFDTypeNotValidException;

	/**
	 * 
	 * @param id
	 */
	void encerrarCFD(String id) throws ExecutionException, InterruptedException, CFDNotExistsException, IOException;

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
	void setCFDlimits(String id, float TP, float SL) throws CFDNotExistsException, ExecutionException, InterruptedException;

	/**
	 * 
	 * @param id
	 */
	public float getValorAtualAtivo(String id, int typeOfCFD) throws IOException, StockIdNotExistsException, CFDTypeNotValidException;

	/**
	 * 
	 * @param idCFD
	 */
	float getBalanco(String idCFD) throws CFDNotExistsException, ExecutionException, InterruptedException, IOException, CFDTypeNotValidException;

	void initAutoCloseCFDs(String username) throws SQLException, AtorTypeNotValidException, AtorExistsException, AtorNotExistsException;

}