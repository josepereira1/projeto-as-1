package tradingsystem.data;

import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.ITradingAbstractFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.Future;

public class CFDDAO {

	private Connection conn;
	private static final String schema = "trading";
	private static final String username = "root";
	private static final String password = "password";
	private static final boolean verifyServerCertificate = false;
	private static final boolean userSSL = false;

	private static final String url = "jdbc:mysql://localhost/" + schema + "?verifyServerCertificate=" + verifyServerCertificate + "&user=" + username + "&password=" + password + "&useSSL=" + userSSL;

	private GenericActiveObject genericActiveObject;
	private ITradingAbstractFactory tradingAbstractFacrtory;

	public CFDDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn =  DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public Future<ICFD> put(String key, ICFD value) {
		// TODO - implement CFDDAO.put
		throw new UnsupportedOperationException();
	}

	public Future<String> getLastId() {
		// TODO - implement CFDDAO.getLastId
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param key
	 */
	public Future<ICFD> remove(Object key) {
		// TODO - implement CFDDAO.remove
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public Future<Collection<ICFD>> getCFDs(String username) {
		// TODO - implement CFDDAO.getCFDs
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TP
	 * @param SL
	 * @param id
	 */
	public void setLimits(float TP, float SL, String id) {
		// TODO - implement CFDDAO.setLimits
		throw new UnsupportedOperationException();
	}

	public Future<Collection<ICFD>> values() {
		// TODO - implement CFDDAO.values
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Float> getStopLess(String idCFD) {
		// TODO - implement CFDDAO.getStopLess
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCFD
	 */
	public Future<Float> getTakeProfit(String idCFD) {
		// TODO - implement CFDDAO.getTakeProfit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public boolean contains(String id) {
		// TODO - implement CFDDAO.contains
		throw new UnsupportedOperationException();
	}

}