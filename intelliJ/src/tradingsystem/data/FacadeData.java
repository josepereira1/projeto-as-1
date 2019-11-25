package tradingsystem.data;

import tradingsystem.business.recursoshumanos.Utilizador;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.ITradingAbstractFactory;

import java.util.Collection;
import java.util.concurrent.Future;

public class FacadeData implements IFacadeData {

	private UtilizadorDAO utilizadores;
	private AtivoDAO ativos;
	private CFDDAO cfds;
	private static IFacadeData data;
	private ITradingAbstractFactory tradingAbastractFactory;

	/**
	 * 
	 * @param username
	 */
	public Utilizador getUtilizador(String username) {
		// TODO - implement FacadeData.getUtilizador
		throw new UnsupportedOperationException();
	}

	public Collection<IAtivo> getAtivos() {
		// TODO - implement FacadeData.getAtivos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cfd
	 */
	public void putCFD(ICFD cfd) {
		// TODO - implement FacadeData.putCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void removeCFD(String id) {
		// TODO - implement FacadeData.removeCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param utilizador
	 */
	public void putUtilizador(Utilizador utilizador) {
		// TODO - implement FacadeData.putUtilizador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public float getValorAtualAtivo(String id) {
		// TODO - implement FacadeData.getValorAtualAtivo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param TP
	 * @param SL
	 */
	public void setCFDlimits(String id, float TP, float SL) {
		// TODO - implement FacadeData.setCFDlimits
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	public void addFundos(String username, float valor) {
		// TODO - implement FacadeData.addFundos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Future<ICFD> getCFD(String id) {
		// TODO - implement FacadeData.getCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public Future<Collection<ICFD>> getCFDs(String username) {
		// TODO - implement FacadeData.getCFDs
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Float> getTakeProfit(String idCFD) {
		// TODO - implement FacadeData.getTakeProfit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Float> getStopLess(String idCFD) {
		// TODO - implement FacadeData.getStopLess
		throw new UnsupportedOperationException();
	}

	public static IFacadeData getInstance() {
		// TODO - implement FacadeData.getInstance
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCFD
	 */
	public float getValorInvestidoCFD(String idCFD) {
		// TODO - implement FacadeData.getValorInvestidoCFD
		throw new UnsupportedOperationException();
	}

	public int getNumeroDeAtivosCFD(String idCFD) {
		// TODO - implement FacadeData.getNumeroDeAtivosCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public boolean containsUtilizador(String username) {
		// TODO - implement FacadeData.containsUtilizador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public boolean containsCFD(String id) {
		// TODO - implement FacadeData.containsCFD
		throw new UnsupportedOperationException();
	}

}