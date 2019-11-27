package tradingsystem.business.trading;

import tradingsystem.business.CFDNotExistsException;
import tradingsystem.business.StockIdNotExistsException;
import tradingsystem.business.StockTypeNotValidException;
import tradingsystem.data.FacadeData;
import tradingsystem.data.IFacadeData;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class FacadeTrading implements IFacadeTrading {

	private IFacadeData data;
	private static IFacadeTrading trading;

	private FacadeTrading() throws SQLException, ClassNotFoundException {
		this.data = FacadeData.getInstance();
	}

	/**
	 * Returns a collection of stocks.
	 * @throws IOException IOException
	 * @throws StockTypeNotValidException error on type of stock
	 */
	public Collection<IAtivo> getAtivos() throws IOException, StockTypeNotValidException{
		return data.getAtivos();
	}

	/**
	 * Opens/add a new CFD.
	 * @param idAtivo id of stock associated to this CFD
	 * @param username username of owner
	 * @param tipo type of CFD (Buy or Sell)
	 * @param stopLess stop less value
	 * @param takeProfit take profit value
	 * @param numeroDeAtivos number of stock
	 */
	public void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws ExecutionException, InterruptedException, IOException, StockIdNotExistsException {
		if(!data.containsAtivo(idAtivo)) throw new StockIdNotExistsException(idAtivo);	//	verify if stock id exists

		ICFD cfd = TradingAbstractFactory.getInstance().createCFD("CFD");

		cfd.setId(data.getNextId().get());
		cfd.setIdAtivo(idAtivo);
		cfd.setUsername(username);
		cfd.setTipo(tipo);
		cfd.setStopLess(stopLess);
		cfd.setTakeProfit(takeProfit);
		cfd.setDataAbertura(LocalDateTime.now());
		cfd.setNumeroDeAtivos(numeroDeAtivos);
		float currentValueStock = data.getValorAtualAtivo(idAtivo);
		cfd.setValorInicial(currentValueStock);
		cfd.setValorInvestido(currentValueStock*numeroDeAtivos);

		data.putCFD(cfd);
	}

	/**
	 * Close a CFD.
	 * @param id id CFD
	 * @return Returns profit of CFD
	 */
	public void encerrarCFD(String id) throws ExecutionException, InterruptedException, CFDNotExistsException, IOException {
		if(!data.containsCFD(id).get()) throw new CFDNotExistsException(id);	//	verify if stock id exists
		data.updateEndDateCFD(id,LocalDateTime.now());	//	in manual or automatic end CFD, the end date is current date
	}

	/**
	 * Show portfolio of user.
	 * @param username username
	 */
	public Collection<ICFD> getPortfolio(String username) throws ExecutionException, InterruptedException {
		return data.getCFDs(username).get();
	}

	/**
	 * Set take profit and/or stop less limits.
	 * @param id id of CFD
	 * @param TP value of take profit
	 * @param SL value of stop less
	 */
	public void setCFDlimits(String id, float TP, float SL) throws CFDNotExistsException, ExecutionException, InterruptedException {
		if(!data.containsCFD(id).get()) throw new CFDNotExistsException(id);	//	verify if stock id exists

		data.setCFDlimits(id,TP,SL).get();	//	although the return being void, we want an active wait
	}

	/**
	 * Returns a single instance of this class. If not exists, create otherwise return this instance.
	 * @throws SQLException SQLException
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	public static IFacadeTrading getInstance() throws SQLException, ClassNotFoundException {
		if(trading == null) trading = new FacadeTrading();
		return trading;
	}

	/**
	 *  Returns the current value of stock
	 * @param id id of stock
	 */
	public float getValorAtualAtivo(String id) throws IOException, StockIdNotExistsException {
		if(!data.containsAtivo(id)) throw new StockIdNotExistsException(id);	//	verify if stock id exists
		return data.getValorAtualAtivo(id);
	}

	/**
	 * Returns the profit of CFD.
	 * @param idCFD id of CFD
	 */
	public float getBalanco(String idCFD) throws CFDNotExistsException, ExecutionException, InterruptedException, IOException {
		if(!data.containsCFD(idCFD).get()) throw new CFDNotExistsException(idCFD);	//	verify if stock id exists
		ICFD cfd = data.getCFD(idCFD).get();

		return CFD.getBalanco(data.getValorAtualAtivo(cfd.getIdAtivo()),cfd.getNumeroDeAtivos(),cfd.getValorInvestido());
	}

}