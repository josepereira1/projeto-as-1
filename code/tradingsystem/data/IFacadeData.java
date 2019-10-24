package tradingsystem.data;

import tradingsystem.business.recursoshumanos.Utilizador;
import tradingsystem.business.trading.Ativo;
import tradingsystem.business.trading.CFD;

import java.util.Collection;

public interface IFacadeData {

	/**
	 * 
	 * @param username
	 */
	Utilizador getUtilizador(String username);

	Collection<Ativo> getAtivos();

	/**
	 * 
	 * @param id
	 */
	float getValorAtualAtivo(String id);

	/**
	 * 
	 * @param id
	 */
	CFD getCFD(String id);

	/**
	 * 
	 * @param cfd
	 */
	void putCFD(CFD cfd);

	/**
	 * 
	 * @param id
	 */
	void removeCFD(String id);

	/**
	 * 
	 * @param utilizador
	 */
	void putUtilizador(Utilizador utilizador);

	/**
	 * 
	 * @param username
	 */
	Collection<CFD> getCFDs(String username);

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

	Collection<CFD> getCFDs();

}