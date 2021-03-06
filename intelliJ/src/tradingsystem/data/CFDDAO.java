package tradingsystem.data;

import tradingsystem.business.CFDNotExistsException;
import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.TradingAbstractFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CFDDAO implements SubjectCFD {

	private static final String schema = "trading";
	private static final String username = "root";
	private static final String password = "password";
	private static final boolean verifyServerCertificate = false;
	private static final boolean userSSL = false;
	private static final String url = "jdbc:mysql://localhost/" + schema + "?verifyServerCertificate=" + verifyServerCertificate + "&user=" + username + "&password=" + password + "&useSSL=" + userSSL;

	private Connection conn;
	private GenericActiveObject genericActiveObject;

	private Collection<Observer> observers;

	/** Constructs a Data Access Object to establish connection to database.
	 * @throws ClassNotFoundException if jbdc driver can not be loaded.
	 * @throws SQLException if connection can not be established.
	 * */
	public CFDDAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.conn =  DriverManager.getConnection(url);
		this.genericActiveObject = new GenericActiveObject(); //	starts Active Object Scheduler
		this.observers = new ArrayList<>();
	}

	/** Given a ResultSet creates an ICFD.
	 * Note: before calling this method make sure to evoke rs.next().
	 * @param rs ResultSet previous instantiated.
	 * @throws SQLException if connection can not be established.
	 */
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
	 * Inserts an ICFD into database.
	 * @param value ICFD.
	 */
	public Future<ICFD> put(ICFD value) {
	    FutureTask<ICFD> futureTask = new FutureTask<>(() -> {

	    	Statement statement = conn.createStatement();

			String sql = "INSERT INTO CFD (id, idAtivo, tipo, username, stopLess, takeProfit, dataAbertura, dataEncerramento, numeroDeAtivos, valorInicial, valorInvestido) VALUES (";
			sql += value.getId()  + ",";
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
	 *	Returns the last id being used by an ICFD.
	 */
	public Future<String> getNextId() {

		FutureTask<String> futureTask = new FutureTask<>(() ->{

			String lastId = "";
			Statement statement = conn.createStatement();
			String sql = "SELECT MAX(CFD.id) as id FROM CFD";
			ResultSet rs = statement.executeQuery(sql);

			if(rs.next()) {
				lastId = rs.getString("id");
			}

			if(lastId == null)return "0";

			int res = Integer.parseInt(lastId) + 1;
			return Integer.toString(res);
		});

		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns a Collection containing all open ICFDs associated to the specified user.
	 * @param username id of user.
	 */
	public Future<Collection<ICFD>> getCFDsOpen(String username) {

		FutureTask<Collection<ICFD>> futureTask = new FutureTask<>(() -> {

			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM CFD WHERE (username = " + "'" + username + "' AND dataEncerramento IS NULL)";
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
	public Future<Void> setLimits(float TP, float SL, String id) {
		FutureTask<Void> futuretask = new FutureTask<>(() -> {
			Statement statement = conn.createStatement();
			String sql = "SET SQL_SAFE_UPDATES = 0";
			statement.executeQuery(sql);

			sql = "UPDATE cfd SET stopLess=" + SL + ", takeprofit=" + TP + " WHERE id =" + id;
			statement.executeUpdate(sql);

			return null;
		});

		this.genericActiveObject.submit(futuretask);
		return futuretask;
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


	/**
	 * Sets ICFD end date.
	 * Also notify ConsultPortfolioController so it display open ICFD contracts.
	 * @param id id of CFD.
	 * @param endDate end date.
	 */
	public void updateEndDateCFD(String id, LocalDateTime endDate) {

		try {
			FutureTask<String> futureTask = new FutureTask<>(() -> {
				Statement statement = conn.createStatement();

				statement.executeUpdate("SET SQL_SAFE_UPDATES = 0");
				statement.executeUpdate("UPDATE CFD SET dataEncerramento='" + Timestamp.valueOf(endDate) + "' WHERE id='" + id + "'");

				String sql = "SELECT username FROM cfd WHERE id =" + id;
				ResultSet resultSet = statement.executeQuery(sql);

				if (resultSet.next()) return resultSet.getString("username");
				else return null;
			});
			genericActiveObject.submit(futureTask);
			String username = futureTask.get(); // execute update

			Collection<ICFD> cfds = getCFDsOpen(username).get(); // gets open CFD contrats
			notifyObservers(new Object[]{id, cfds}).get(); // notify ConsultPortfolioController

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns stock if from specified ICFD.
	 * @param idCFD id of ICFD.
	 */
	public Future<String> getIdAtivoDoCFD(String idCFD){
		FutureTask<String> futureTask = new FutureTask<>(() -> {

			Statement statement = conn.createStatement();

			String sql = "SELECT idAtivo FROM CFD WHERE idCFD=" + idCFD;

			ResultSet resultSet = statement.executeQuery("SELECT idAtivo FROM CFD WHERE id=" + idCFD);

			if(resultSet.next())
				return resultSet.getString("idAtivo");
			else
				throw new CFDNotExistsException(idCFD);
		});

		this.genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns type of ICFD.
	 * @param id if of ICFD.
	 */
	public Future<Integer> getTipoCFD(String id){
		FutureTask<Integer> futureTask = new FutureTask<>(() ->{
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT tipo FROM CFD WHERE id=" + id);
			if(resultSet.next()){
				return resultSet.getInt("tipo");
			}else throw new CFDNotExistsException(id);
		});
		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Register an Observer.
	 * @param observer observer being registered.
	 */
	@Override
	public Future<Void> registerObserver(Observer observer) {
		FutureTask<Void> futureTask = new FutureTask<>(()->{
			observers.add(observer);
			return null;
		});

		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Notify all registered observers.
	 * @param arg argument to be notified.
	 */
	@Override
	public Future<Void> notifyObservers(Object arg) {
		FutureTask<Void> futureTask = new FutureTask<>(() -> {
			for (Observer observer : observers)observer.update(arg);
			return null;
		});
		genericActiveObject.submit(futureTask);
		return futureTask;
	}

	/**
	 * Returns this object as a Subject.
	 */
	public Future<SubjectCFD> getCFDSubject(){
		FutureTask<SubjectCFD> futureTask = new FutureTask<>(() -> this);
		genericActiveObject.submit(futureTask);
		return futureTask;
	}
}