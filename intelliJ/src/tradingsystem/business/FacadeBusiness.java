package tradingsystem.business;

import tradingsystem.business.recursoshumanos.FacadeRecursosHumanos;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.recursoshumanos.IFacadeRecursosHumanos;
import tradingsystem.business.trading.FacadeTrading;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.IFacadeTrading;
import tradingsystem.data.FacadeData;
import tradingsystem.data.IFacadeData;

import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class FacadeBusiness implements IFacadeBusiness {

	private static final int SEC = 1000;
	private static final int INTERVAL = 1 * SEC;

	private static IFacadeBusiness business;

	private IFacadeData data;
	private IFacadeRecursosHumanos recursosHumanos;
	private IFacadeTrading trading;

	private FacadeBusiness() throws SQLException, ClassNotFoundException {
		this.data = FacadeData.getInstance();
		this.recursosHumanos = FacadeRecursosHumanos.getInstance();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public IAtor autenticarUtilizador(String username, String password) throws AtorNotExistsException, SQLException, AtorTypeNotValidException {
		return this.recursosHumanos.autenticarUtilizador(username, password);
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	public IAtor registarUtilizador(String username, String password, float plafond) {
		// TODO - implement FacadeBusiness.registarUtilizador
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
	public void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) {
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

	public Collection<IAtivo> getAtivos() {
		// TODO - implement FacadeBusiness.getAtivos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public Collection<ICFD> getPortfolio(String username) {
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

	/**
	 * 
	 * @param id
	 */
	public float getValorAtualAtivo(String id) {
		// TODO - implement FacadeBusiness.getValorAtualAtivo
		throw new UnsupportedOperationException();
	}

	public static IFacadeBusiness getInstance() throws SQLException, ClassNotFoundException {
		if (business == null) business = new FacadeBusiness();
		return business;
	}

	/**
	 * 
	 * @param idCFD
	 */
	public float getBalanco(String idCFD) {
		// TODO - implement FacadeBusiness.getBalanco
		throw new UnsupportedOperationException();
	}

	@Override
	public void initAutoCloseCFDs(String username) throws SQLException, AtorTypeNotValidException, AtorExistsException {

		String type = username.substring(0, 2); // fst two characters of username to get its type

		if (type.equals("a_")) {
			if (this.data.containsUtilizador(username, "Administrador")) throw new AtorExistsException(username);
		}
		else if (type.equals("t_")) {
			if (this.data.containsUtilizador(username, "Trader")) throw new AtorExistsException(username);
		}
		else throw new AtorTypeNotValidException();

		new Thread(() -> {
			try {
				while(true) {
					Thread.sleep(INTERVAL);
					Collection<String> cfds = this.data.getCFDsIds(username).get();
					for (String id : cfds) {
						encerrarCFD(id);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}).start();
	}

}