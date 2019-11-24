package tradingsystem.business.recursoshumanos;

import tradingsystem.business.trading.ITradingAbstractFactory;
import tradingsystem.data.IFacadeData;

public class FacadeRecursosHumanos implements IFacadeRecursosHumanos {

	private IFacadeData data;
	private FactoryAtor factoryActor;
	private static IFacadeRecursosHumanos recursosHumanos;
	private ITradingAbstractFactory tradingAbastracFactory;

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public IAtor autenticarUtilizador(String username, String password) {
		// TODO - implement FacadeRecursosHumanos.autenticarUtilizador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	public IAtor registarUtilizador(String username, String password, float plafond) {
		// TODO - implement FacadeRecursosHumanos.registarUtilizador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	public void setFundos(String username, float valor) {
		// TODO - implement FacadeRecursosHumanos.setFundos
		throw new UnsupportedOperationException();
	}

	public static IFacadeRecursosHumanos getInstance() {
		// TODO - implement FacadeRecursosHumanos.getInstance
		throw new UnsupportedOperationException();
	}

}