package tradingsystem.business.recursoshumanos;

import tradingsystem.business.AtorExistsException;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;

import java.sql.SQLException;

public interface IFacadeRecursosHumanos {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	IAtor autenticarUtilizador(String username, String password) throws SQLException, AtorTypeNotValidException, AtorNotExistsException;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param plafond
	 */
	IAtor registarUtilizador(String username, String password, float plafond) throws SQLException, AtorTypeNotValidException, AtorExistsException;

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	void addFundos(String username, float valor) throws SQLException, AtorTypeNotValidException, AtorNotExistsException;
}