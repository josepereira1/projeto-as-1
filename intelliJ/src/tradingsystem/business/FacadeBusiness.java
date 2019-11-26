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

import java.io.IOException;
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
	public IAtor registarUtilizador(String username, String password, float plafond) throws SQLException, AtorTypeNotValidException, AtorExistsException {
		return this.recursosHumanos.registarUtilizador(username, password, plafond);
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
	public void abrirCFD(String idAtivo, String username, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) throws InterruptedException, ExecutionException, StockIdNotExistsException, IOException {
		trading.abrirCFD(idAtivo, username, tipo,stopLess,takeProfit, numeroDeAtivos);
	}

	/**
	 * 
	 * @param id
	 */
	public void encerrarCFD(String id, String username) throws InterruptedException, ExecutionException, IOException, CFDNotExistsException, AtorNotExistsException, SQLException, AtorTypeNotValidException {
		trading.encerrarCFD(id);
		recursosHumanos.addFundos(username, trading.getBalanco(id));	//	update plafond of user
	}

	public Collection<IAtivo> getAtivos() throws AtorTypeNotValidException, IOException, StockTypeNotValidException {
		return trading.getAtivos();
	}

	/**
	 * 
	 * @param username
	 */
	public Collection<ICFD> getPortfolio(String username) throws ExecutionException, InterruptedException {
		return trading.getPortfolio(username);
	}

	/**
	 * 
	 * @param id
	 * @param TP
	 * @param SL
	 */
	public void setCFDlimits(String id, float TP, float SL) throws InterruptedException, ExecutionException, CFDNotExistsException {
		trading.setCFDlimits(id,TP,SL);
	}

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	public void addFundos(String username, float valor) throws AtorNotExistsException, SQLException, AtorTypeNotValidException {
		recursosHumanos.addFundos(username, valor);
	}

	/**
	 * 
	 * @param id
	 */
	public float getValorAtualAtivo(String id) throws IOException, StockIdNotExistsException {
		return trading.getValorAtualAtivo(id);
	}

	public static IFacadeBusiness getInstance() throws SQLException, ClassNotFoundException {
		if (business == null) business = new FacadeBusiness();
		return business;
	}

	/**
	 * 
	 * @param idCFD
	 */
	public float getBalanco(String idCFD) throws InterruptedException, ExecutionException, IOException, CFDNotExistsException {
		return trading.getBalanco(idCFD);
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
						try {
							encerrarCFD(id, username);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (CFDNotExistsException e) {
							e.printStackTrace();
						} catch (AtorNotExistsException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (AtorTypeNotValidException e) {
							e.printStackTrace();
						}
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