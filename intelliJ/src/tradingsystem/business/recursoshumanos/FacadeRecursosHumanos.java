package tradingsystem.business.recursoshumanos;

import tradingsystem.business.AtorExistsException;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;
import tradingsystem.data.FacadeData;
import tradingsystem.data.IFacadeData;

import java.sql.SQLException;

public class FacadeRecursosHumanos implements IFacadeRecursosHumanos {

	private IFacadeData data;
	private static IFacadeRecursosHumanos recursosHumanos;

	/**
	 * Constructs FacadeRecursosHumanos to manage human resources sub-system.
	 * @throws SQLException if impossible to access database.
	 * @throws ClassNotFoundException if FacadeData class is not found.
	 */
	private FacadeRecursosHumanos() throws SQLException, ClassNotFoundException {
		this.data = FacadeData.getInstance();
	}

	/**
	 * Returns IAtor if credentials are valid, otherwise returns null.
	 * @param username id of IAtor.
	 * @param password password of IAtor.
	 * @throws SQLException if impossible to access database.
	 * @throws AtorTypeNotValidException if actor type is not valid.
	 * @throws AtorNotExistsException if actor with specified username does not exist in database.
	 */
	public IAtor autenticarUtilizador(String username, String password) throws SQLException, AtorTypeNotValidException, AtorNotExistsException {

		IAtor res;
		String type = username.substring(0, 2); // fst two characters of username to get its type

		if (type.equals("a_")) res = this.data.getUtilizador(username, "Administrador");
		else if (type.equals("t_")) res = this.data.getUtilizador(username, "Trader");
		else throw new AtorTypeNotValidException();

		// we don't need to call contains() because get() will return null if username doesn't exist
		if (res == null) throw new AtorNotExistsException(username);

		if (res.autenticar(username, password)) return res; // valid password
		else return null; // invalid password
	}

	/**
	 * Register an IAtor in system.
	 * @param username id of IAtor.
	 * @param password password of IAtor.
	 * @param plafond initial wallet value of IAtor.
	 * @throws AtorTypeNotValidException if actor type is not valid.
	 * @throws AtorExistsException if actor with specified username already exists in database.
	 */
	public IAtor registarUtilizador(String username, String password, float plafond) throws SQLException, AtorTypeNotValidException, AtorExistsException {

		IAtor res;
		String type = username.substring(0, 2); // fst two characters of username to get its type

		if (type.equals("a_")) {
			if (this.data.containsUtilizador(username, "Administrador")) throw new AtorExistsException(username);
			res =  FactoryAtor.getInstance().createAtor("Administrador");
		}
		else if (type.equals("t_")) {
			if (this.data.containsUtilizador(username, "Trader")) throw new AtorExistsException(username);
			res =  FactoryAtor.getInstance().createAtor("Trader");
			((Trader)res).setPlafond(plafond);
		}
		else throw new AtorTypeNotValidException();

		// setters() common to both Actor types
		res.setUsername(username);
		res.setPassword(password);
		this.data.putUtilizador(res);
		return res;
	}

	/**
	 * Adds a plafond to Trader with specified username.
	 * @param username id of Trader.
	 * @param valor wallet value to add.
	 * @throws SQLException if impossible to access database.
	 * @throws AtorTypeNotValidException if actor type is not valid.
	 * @throws AtorNotExistsException if actor with specified username does not exist in database.
	 */
	public void addFundos(String username, float valor) throws AtorNotExistsException, SQLException, AtorTypeNotValidException {

		String type = username.substring(0, 2); // fst two characters of username to get its type

		if (type.equals("t_")) { // makes sure it's a Trader
			if (this.data.containsUtilizador(username, "Trader") == false) throw new AtorNotExistsException(username);
			this.data.addFundos(username, valor);
		}
		else throw new AtorTypeNotValidException();
	}

	/**
	 * Returns singleton.
	 * @throws SQLException if impossible to access database.
	 * @throws ClassNotFoundException if FacadeData class is not found.
	 */
	public static IFacadeRecursosHumanos getInstance() throws SQLException, ClassNotFoundException {
		if (recursosHumanos == null) recursosHumanos = new FacadeRecursosHumanos();
		return recursosHumanos;
	}

	public float getPlafond(String username) throws AtorNotExistsException, SQLException {
		return data.getPlafond(username);
	}

}