package tradingsystem.business.trading;

import tradingsystem.business.*;
import tradingsystem.data.FacadeData;
import tradingsystem.data.IFacadeData;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FacadeTrading implements IFacadeTrading {

	private static IFacadeTrading trading;

	private IFacadeData data;

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
	public Future<ICFD> abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws ExecutionException, InterruptedException, IOException, StockIdNotExistsException, CFDTypeNotValidException, AtorNotExistsException, SQLException, NoFundsToCFDException {
		if(!data.containsAtivo(idAtivo)) throw new StockIdNotExistsException(idAtivo);	//	verify if stock id exists

		ICFD cfd = TradingAbstractFactory.getInstance().createCFD("CFD");

		float plafond = data.getPlafond(username);
		float valorAtualDoAtivo = data.getValorAtualAtivo(idAtivo,tipo);
		float valorInvestido = valorAtualDoAtivo * numeroDeAtivos;

		if(plafond >= valorInvestido) {

			data.addFundos(username, -valorInvestido);	//	tirar o dinheiro ao utilizador

			cfd.setId(data.getNextId().get());
			cfd.setIdAtivo(idAtivo);
			cfd.setUsername(username);
			cfd.setTipo(tipo);
			cfd.setStopLess(stopLess);
			cfd.setTakeProfit(takeProfit);
			cfd.setDataAbertura(LocalDateTime.now());
			cfd.setDataEncerramento(null);
			cfd.setNumeroDeAtivos(numeroDeAtivos);
			cfd.setValorInicial(valorAtualDoAtivo);
			cfd.setValorInvestido(valorInvestido);

			return data.putCFD(cfd);
		}else throw new NoFundsToCFDException(String.valueOf(plafond));
	}

	/**
	 * Close a CFD.
	 * @param id id CFD
	 * @return Returns profit of CFD
	 */
	public void encerrarCFD(String id) throws ExecutionException, InterruptedException, CFDNotExistsException {
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
	public float getValorAtualAtivo(String id, int typeOfCFD) throws IOException, StockIdNotExistsException, CFDTypeNotValidException {
		if(!data.containsAtivo(id)) throw new StockIdNotExistsException(id);	//	verify if stock id exists
		return data.getValorAtualAtivo(id, typeOfCFD);
	}

	/**
	 * Returns the profit of CFD.
	 * @param idCFD id of CFD
	 */
	public float getBalanco(String idCFD) throws CFDNotExistsException, ExecutionException, InterruptedException, IOException, CFDTypeNotValidException {
		if(!data.containsCFD(idCFD).get()) throw new CFDNotExistsException(idCFD);	//	verify if stock id exists
		return CFD.getBalanco(data.getValorAtualAtivo(data.getIdAtivoDoCFD(idCFD).get(), data.getTipoCFD(idCFD).get()), data.getNumeroDeAtivosCFD(idCFD).get(),data.getValorInvestidoCFD(idCFD).get());
	}

}