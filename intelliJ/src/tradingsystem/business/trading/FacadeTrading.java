package tradingsystem.business.trading;

import tradingsystem.data.IFacadeData;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class FacadeTrading implements IFacadeTrading {

	private IFacadeData data;
	private static IFacadeTrading trading;

	public Collection<IAtivo> getAtivos() {
		// TODO - implement FacadeTrading.getAtivos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idAtivo
	 * @param username
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	public void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws ExecutionException, InterruptedException {
		String lastId = data.getLastId().get();

		ICFD cfd = TradingAbstractFactory.getInstance().createCFD("CFD");



		//data.putCFD();
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

	public static IFacadeTrading getInstance() {
		// TODO - implement FacadeTrading.getInstance
		throw new UnsupportedOperationException();
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