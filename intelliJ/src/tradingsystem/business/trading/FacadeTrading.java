package tradingsystem.business.trading;

import tradingsystem.business.StockTypeNotValidException;
import tradingsystem.data.FacadeData;
import tradingsystem.data.IFacadeData;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	 * Add new CFD.
	 * @param idAtivo id of stock associated to this CFD
	 * @param username username of owner
	 * @param tipo type of CFD (Buy or Sell)
	 * @param stopLess stop less value
	 * @param takeProfit take profit value
	 * @param numeroDeAtivos number of stock
	 */
	public void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws ExecutionException, InterruptedException, IOException {
		ICFD cfd = TradingAbstractFactory.getInstance().createCFD("CFD");

		//TODO FALTA VERIFICAR ANTES SE O ID DO ATIVO EXISTE, MAS TENHO QUE FAZER O CONTAINS NO ATIVO PRIMEIRO E AINDA N FIZ

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
	 * 
	 * @param id
	 */
	public void encerrarCFD(String id) {
		// TODO - implement FacadeTrading.encerrarCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public Collection<ICFD> getPortfolio(String username) {
		// TODO - implement FacadeTrading.getPortfolio
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param TP
	 * @param SL
	 */
	public void setCFDlimits(String id, float TP, float SL) {
		// TODO - implement FacadeTrading.setCFDlimits
		throw new UnsupportedOperationException();
	}

	public static IFacadeTrading getInstance() throws SQLException, ClassNotFoundException {
		if(trading == null) trading = new FacadeTrading();
		return trading;
	}

	/**
	 * 
	 * @param id
	 */
	public float getValorAtualAtivo(String id) {
		// TODO - implement FacadeTrading.getValorAtualAtivo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCFD
	 */
	public float getBalanco(String idCFD) {
		// TODO - implement FacadeTrading.getBalanco
		throw new UnsupportedOperationException();
	}

}