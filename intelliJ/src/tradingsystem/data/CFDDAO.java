package tradingsystem.data;

import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.TradingAbstractFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CFDDAO {

	private static final String schema = "trading";
	private static final String username = "root";
	private static final String password = "password";
	private static final boolean verifyServerCertificate = false;
	private static final boolean userSSL = false;
	private static final String url = "jdbc:mysql://localhost/" + schema + "?verifyServerCertificate=" + verifyServerCertificate + "&user=" + username + "&password=" + password + "&useSSL=" + userSSL;

	private Connection conn;
	private GenericActiveObject genericActiveObject;

	/** Constructs a Data Access Object to establish connection to database. */
	public CFDDAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.conn =  DriverManager.getConnection(url);
		this.genericActiveObject = new GenericActiveObject(); //	starts Active Object Scheduler
	}

	/** Given a ResultSet creates an ICFD.
	 * Note: before calling this method make sure to evoke rs.next().
	 * @param rs ResultSet previous instantiated. */
	private static ICFD getCFD(ResultSet rs) throws SQLException {
		ICFD cfd = TradingAbstractFactory.getInstance().createCFD("CFD");
		cfd.setId(rs.getString("id"));
		cfd.setIdAtivo(rs.getString("idAtivo"));
		cfd.setTipo(rs.getInt("tipo"));
		cfd.setUsername(rs.getString("username"));
		cfd.setStopLess(rs.getFloat("stopLess"));
		cfd.setTakeProfit(rs.getFloat("takeProfit"));
		cfd.setDataAbertura(rs.getTimestamp("dataAbertura").toLocalDateTime());
		Timestamp data = rs.getTimestamp("dataEncerramento");
		if (data != null) cfd.setDataEncerramento(data.toLocalDateTime());
		else cfd.setDataEncerramento(null);
		cfd.setNumeroDeAtivos(rs.getInt("numeroDeAtivos"));
		cfd.setValorInicial(rs.getFloat("valorInicial"));
		cfd.setValorInvestido(rs.getFloat("valorInvestido"));
		return cfd;
	}

	/**
	 * Inserts ICFD into database.
	 * @param value ICFD.
	 */
	public Future<ICFD> put(ICFD value) {
	    FutureTask<ICFD> futureTask = new FutureTask<>(() -> {

	    	Statement statement = conn.createStatement();

			String sql = "INSERT INTO CFD (id, idAtivo, tipo, username, stopLess, takeProfit, dataAbertura, dataEncerramento, numeroDeAtivos, valorInicial, valorInvestido) VALUES (";
			sql += "'" + value.getId() + "'" + ",";
			sql += "'" + value.getIdAtivo() + "'" + ",";
			sql += value.getTipo() + ",";
			sql += "'" + value.getUsername() + "'" + ",";
			sql += value.getStopLess() + ",";
			sql += value.getTakeProfit() + ",";
			sql += "'" + Timestamp.valueOf(value.getDataAbertura()) + "',";
			sql += value.getDataEncerramento() != null ? "'" + Timestamp.valueOf(value.getDataEncerramento()) + "'" : "null" + ",";
			sql += value.getNumeroDeAtivos() + ",";
			sql += value.getValorInicial() + ",";
			sql += value.getValorInvestido() + ")";

			statement.executeUpdate(sql);

			return value;
		});

	    genericActiveObject.submit(futureTask);
	    return futureTask;
    }

	/**
	 * Returns an ICD from databse.
	 * @param id key of ICFD.
	 */
	public Future<ICFD> get(String id) {
		FutureTask<ICFD> futureTask = new FutureTask<ICFD>(() -> {
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM CFD WHERE id = " + "'" + id + "'";
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) return getCFD(rs);
			else return null;
		});

		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 *	Returns the last id of ICFD.
	 */
	public Future<String> getLastId() {

		FutureTask<String> futureTask = new FutureTask<>(() ->{

			String lastId = "";
			Statement statement = conn.createStatement();
			String sql = "SELECT MAX(CFD.id) as id FROM CFD";
			ResultSet rs = statement.executeQuery(sql);

			if(rs.next()) {
				lastId = rs.getString("id");
			}

			int res = Integer.parseInt(lastId) + 1;
			return Integer.toString(res);
		});

		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Removes ICFD from database.
	 * @param id id of ICFD.
	 */
	public Future<ICFD> remove(String id) {

		FutureTask<ICFD> futureTask = new FutureTask<ICFD>(() -> {

			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM CFD WHERE id = " + "'" + id + "'";
			ResultSet rs = statement.executeQuery(sql);
			ICFD res = null;
			if (rs.next()) res = getCFD(rs);

			sql = "SET SQL_SAFE_UPDATES = 0";
			statement.executeUpdate(sql);
			sql = "DELETE FROM CFD WHERE id=" + id;
			statement.executeUpdate(sql);

			return res;
		});

		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns a Collection containing all ICFDs associated to the specified user.
	 * @param username id of user.
	 */
	public Future<Collection<ICFD>> getCFDs(String username) {

		FutureTask<Collection<ICFD>> futureTask = new FutureTask<Collection<ICFD>>(() -> {

			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM CFD WHERE username = " + "'" + username + "'";
			ResultSet rs = statement.executeQuery(sql);

			Collection<ICFD> result = new ArrayList<>();

			while (rs.next()) {
				ICFD cfd = getCFD(rs);
				result.add(cfd);
			}

			return result;
		});

		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Sets the stop less and take profit limits associated to the specified ICFD.
	 * @param TP take profit of ICFD.
	 * @param SL stop Less of ICFD.
	 * @param id key of ICFD.
	 */
	public void setLimits(float TP, float SL, String id) {
		Runnable task = () -> {
			try {
				Statement statement = conn.createStatement();
				String sql = "SET SQL_SAFE_UPDATES = 0";
				statement.executeQuery(sql);

				sql = "UPDATE cfd SET stopLess=" + SL + ", takeprofit=" + TP + " WHERE id =" + id;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		this.genericActiveObject.submit(task);
	}

	/**
	 * Returns a Collection containing all ICFDs in database.
	 */
	public Future<Collection<ICFD>> values() {
		FutureTask<Collection<ICFD>> futureTask = new FutureTask<Collection<ICFD>>(() -> {

			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM CFD";
			ResultSet rs = statement.executeQuery(sql);

			Collection<ICFD> result = new ArrayList<>();

			while (rs.next()) {
				ICFD cfd = getCFD(rs);
				result.add(cfd);
			}

			return result;
		});

		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns stop less from specified ICFD.
	 * @param id id of ICFD.
	 */
	public Future<Float> getStopLess(String id) {
		FutureTask<Float> futureTask = new FutureTask<>(() -> {
			Statement statement = conn.createStatement();
			String sql = "SELECT stopLess FROM cfd WHERE id=" + id;
			ResultSet rs = statement.executeQuery(sql);
			float res = 0;
			if (rs.next()) {
				res = rs.getFloat("stopLess");
			}
			return res;
		});

		this.genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns take profit from specified ICFD.
	 * @param id id of ICFD.
	 */
	public Future<Float> getTakeProfit(String id) {
		FutureTask<Float> futureTask = new FutureTask<>(() -> {
			Statement statement = conn.createStatement();
			String sql = "SELECT takeProfit FROM cfd WHERE id=" + id;
			ResultSet rs = statement.executeQuery(sql);
			float res = 0;
			if (rs.next()) {
				res = rs.getFloat("takeProfit");
			}
			return res;
		});

		this.genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns initial investment from specified ICFD.
	 * @param id of ICFD.
	 */
	public Future<Float> getValorInvestidoCFD(String id) {
		FutureTask<Float> futureTask = new FutureTask<>(() -> {
			Statement statement = conn.createStatement();
			String sql = "SELECT valorInvestido FROM cfd WHERE id=" + id;
			ResultSet rs = statement.executeQuery(sql);
			float res = 0;
			if (rs.next()) {
				res = rs.getFloat("valorInvestido");
			}
			return res;
		});

		this.genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns total number of stocks from specified ICFD.
	 * @param id of ICFD.
	 */
	public Future<Integer> getNumeroDeAtivosCFD(String id) {
		FutureTask<Integer> futureTask = new FutureTask<>(() -> {
			Statement statement = conn.createStatement();
			String sql = "SELECT numeroDeAtivos FROM cfd WHERE id=" + id;
			ResultSet rs = statement.executeQuery(sql);
			int res = 0;
			if (rs.next()) {
				res = rs.getInt("numeroDeAtivos");
			}
			return res;
		});

		this.genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns true if ICFD exists in database, otherwise returns false.
	 * @param id id of ICFD.
	 */
	public Future<Boolean> contains(String id) {
		FutureTask<Boolean> futureTask = new FutureTask<>(() -> {
			Statement statement = conn.createStatement();
			String sql = "SELECT EXISTS(SELECT id FROM cfd WHERE id=" + id + ") as contains";
			ResultSet rs = statement.executeQuery(sql);
			boolean res = false;
			if (rs.next()) {
				res = rs.getBoolean("contains");
			}
			return res;
		});

		this.genericActiveObject.submit(futureTask);
		return futureTask;
	}
}