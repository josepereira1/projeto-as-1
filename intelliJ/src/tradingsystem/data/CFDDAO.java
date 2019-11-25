package tradingsystem.data;

import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.ITradingAbstractFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CFDDAO {

	private Connection conn;
	private static final String schema = "trading";
	private static final String username = "root";
	private static final String password = "password";
	private static final boolean verifyServerCertificate = false;
	private static final boolean userSSL = false;

	private static final String url = "jdbc:mysql://localhost/" + schema + "?verifyServerCertificate=" + verifyServerCertificate + "&user=" + username + "&password=" + password + "&useSSL=" + userSSL;

	private GenericActiveObject genericActiveObject;
	private ITradingAbstractFactory tradingAbstractFactory;

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
		genericActiveObject = new GenericActiveObject();	//	thread start
	}

	/**
	 * Convert object of LocalDateTime suitable to Mysql database.
	 * @param localDateTime localDateTime to convert
	 * @return Convert object of LocalDateTime suitable to Mysql database
	 */
	private static String toLocalDateTime(LocalDateTime localDateTime){
		return "'" + Date.valueOf(localDateTime.toLocalDate()) + ":" + localDateTime.getHour() + ":" + localDateTime.getMinute() + "'";
	}

	/**
	 * Insert CFD into database.
	 * @param key key of CFD
	 * @param value CFD
	 */
	public Future<ICFD> put(String key, ICFD value) {
	    FutureTask<ICFD> futureTask = new FutureTask<>(() -> {
			Statement statement = null;
			String time;
			try {
				statement = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String sql = "INSERT INTO CFD (id, idAtivo, tipo, username, stopLess, takeProfit, dataAbertura, dataEncerramento, numeroDeAtivos, valorInicial, valorInvestido) VALUES (";
			sql += "' " + value.getId() + "'" + ",";
			sql += "' " + value.getIdAtivo() + "'" + ",";
			sql += value.getTipo() + ",";
			sql += "' " + value.getUsername() + "'" + ",";
			sql += value.getStopLess() + ",";
			sql += value.getTakeProfit() + ",";
			sql += toLocalDateTime(value.getDataAbertura()) + ",";
			sql += toLocalDateTime(value.getDataEncerramento()) + ",";
			sql += value.getNumeroDeAtivos() + ",";
			sql += value.getValorInicial() + ",";
			sql += value.getValorInvestido() + ")";

			System.out.println(sql);

			try {
				Thread.sleep(5000);
				System.out.println("Thread sleep");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return value;
		});

	    genericActiveObject.submit(futureTask);
	    return futureTask;
    }

	/**
	 *	Return the last id of CFD.
	 * @return Return the last id of CFD
	 */
	public Future<String> getLastId() {
		FutureTask<String> futureTask = new FutureTask<String>(() ->{

		String lastId = "";
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "SELECT MAX(CFD.id) FROM CFD";
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(rs.next()) {
				lastId = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return String.valueOf(Integer.valueOf(lastId) + 1);
		});
		return futureTask;
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