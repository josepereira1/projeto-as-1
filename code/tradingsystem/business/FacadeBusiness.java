package tradingsystem.business;

import tradingsystem.data.FacadeData;
import tradingsystem.business.recursoshumanos.IFacadeRH;
import tradingsystem.business.trading.IFacadeTrading;
import tradingsystem.business.trading.Ativo;

import java.util.Collection;

public class FacadeBusiness implements IFacadeBusiness {

	private FacadeData data;
	private IFacadeRH rh;
	private IFacadeTrading trading;

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public boolean autenticarUtilizador(int username, String password) {
		// TODO - implement FacadeBusiness.autenticarUtilizador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	public void registarUtilizador(String username, String password, float plafond) {
		// TODO - implement FacadeBusiness.registarUtilizador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idAtivo
	 * @param usernameVendedor
	 * @param usernameComprador
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	public void abrirCFD(String idAtivo, String usernameVendedor, String usernameComprador, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) {
		// TODO - implement FacadeBusiness.abrirCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void encerrarCFD(String id) {
		// TODO - implement FacadeBusiness.encerrarCFD
		throw new UnsupportedOperationException();
	}

	public Collection<Ativo> getAtivos() {
		// TODO - implement FacadeBusiness.getAtivos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public Collection<Ativo> getPortfolio(String username) {
		// TODO - implement FacadeBusiness.getPortfolio
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param TP
	 * @param SL
	 */
	public void setCFDlimits(String id, float TP, float SL) {
		// TODO - implement FacadeBusiness.setCFDlimits
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	public void setFundos(String username, float valor) {
		// TODO - implement FacadeBusiness.setFundos
		throw new UnsupportedOperationException();
	}

}