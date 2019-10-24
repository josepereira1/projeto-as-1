package tradingsystem.data;

import tradingsystem.business.recursoshumanos.Utilizador;
import tradingsystem.business.trading.Ativo;
import tradingsystem.business.trading.CFD;

import java.util.Collection;

public class FacadeData implements IFacadeData {

	private UtilizadorDAO utilizadores;
	private AtivoDAO ativos;
	private CFDDAO cfds;

	/**
	 * 
	 * @param username
	 */
	public Utilizador getUtilizador(String username) {
		// TODO - implement FacadeData.getUtilizador
		throw new UnsupportedOperationException();
	}

	public Collection<Ativo> getAtivos() {
		// TODO - implement FacadeData.getAtivos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public CFD getCFD(String id) {
		// TODO - implement FacadeData.getCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cfd
	 */
	public void putCFD(CFD cfd) {
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

	@Override
	public Collection<CFD> getCFDs(String username) {
		return null;
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
		// TODO - implement FacadeData.setFundos
		throw new UnsupportedOperationException();
	}

	public Collection<CFD> getCFDs() {
		// TODO - implement FacadeData.getCFDs
		throw new UnsupportedOperationException();
	}

}