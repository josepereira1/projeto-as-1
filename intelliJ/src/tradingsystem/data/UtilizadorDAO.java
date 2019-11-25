package tradingsystem.data;

import tradingsystem.business.recursoshumanos.Administrador;
import tradingsystem.business.recursoshumanos.FactoryAtor;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.recursoshumanos.Trader;

import java.sql.*;

public class UtilizadorDAO {

	private Connection conn;
	private static final String schema = "trading";
	private static final String username = "root";
	private static final String password = "password";
	private static final boolean verifyServerCertificate = false;
	private static final boolean userSSL = false;

	private static final String url = "jdbc:mysql://localhost/" + schema + "?verifyServerCertificate=" + verifyServerCertificate + "&user=" + username + "&password=" + password + "&useSSL=" + userSSL;

	private FactoryAtor factoryAtor;

	public UtilizadorDAO(FactoryAtor factoryAtor){
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
		this.factoryAtor = factoryAtor;
	}

	/**
	 * 	Insert user to database.
	 * @param value user
	 */
	public void put(IAtor value) throws SQLException {
		// TODO - implement UtilizadorDAO.put
		Statement statement = conn.createStatement();
		String sql = "";

		if(value instanceof Trader)sql = queryTrader(value);
		else if (value instanceof Administrador)sql = queryAdministrador(value);

		if(sql.equalsIgnoreCase("")) statement.executeUpdate(sql);
	}

	private static String queryTrader(IAtor value){
		String sql = "INSERT INTO Trader (username, password, plafond) VALUES (" ;
		sql += "'"+value.getUsername()+"',";
		sql += "'"+value.getPassword()+"',";
		sql += ((Trader) value).getPlafond() + ")";
		return sql;
	}

	private static String queryAdministrador(IAtor value){
		String sql = "INSERT INTO Administrador (username, password) VALUES (";
		sql += "'" + value.getUsername() + "',";
		sql += "'" + value.getPassword() + "')";
		return sql;
	}

	/**
	 * Return User with specific username.
	 * @param username username
	 * @param userType user type (Trader or Administrador)
	 * @return Return User with specific username
	 * @throws SQLException SQLException
	 */
	public IAtor get(String username, String userType) throws SQLException {
		// TODO - implement UtilizadorDAO.get
		Statement statement = conn.createStatement();
		IAtor user = null;

		if(userType.equalsIgnoreCase("Trader")) user = getQueryTrader(statement, username);
		else if(userType.equalsIgnoreCase("Administrador"))  user = getQueryAdmnistrador(statement, username);

		return user;
	}

	/**
	 * Return Trader user.
	 * @param statement statement to execute queries
	 * @param username username
	 * @return Return Trader user
	 * @throws SQLException SQLException
	 */
	private IAtor getQueryTrader(Statement statement, String username) throws SQLException {
		String sql = "SELECT * FROM TRADER WHERE username='" + username + "'";

		ResultSet resultSet = statement.executeQuery(sql);

		IAtor user = factoryAtor.createAtor("Trader");

		while(resultSet.next()){
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			((Trader)user).setPlafond(resultSet.getFloat("plafond"));
		}

		return user;
	}

	/**
	 * Return Admnistrador user.
	 * @param statement statement to execute queries
	 * @param username username
	 * @return Return Admnistrador user
	 * @throws SQLException SQLException
	 */
	private IAtor getQueryAdmnistrador(Statement statement, String username) throws SQLException {
		String sql = "SELECT * FROM ADMINISTRADOR WHERE username='" + username + "'";

		ResultSet resultSet = statement.executeQuery(sql);

		IAtor user = factoryAtor.createAtor("Administrador");

		while(resultSet.next()){
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
		}
		return user;
	}

	/**
	 * Add funds to plafond of user.
	 * @param username username
	 * @param valor value of funds
	 */
	public void addFundos(String username, float valor) throws SQLException {
		Statement statement = conn.createStatement();
		float plafond = 0;

		ResultSet resultSet = statement.executeQuery("SELECT plafond FROM TRADER WHERE username=" + "'" + username + "'");
		while (resultSet.next()) plafond = resultSet.getFloat("plafond");

		statement.executeUpdate("UPDATE TRADER SET plafond = " + (plafond + valor) + "WHERE username=" + "'" + username + "'");
	}

	/**
	 * Returns true if Utilizador exists in database, otherwise returns false.
	 * @param username username
	 */
	public boolean contains(String username, String userType) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = "";

		if(userType.equalsIgnoreCase("Trader"))sql = "SELECT EXISTS(SELECT username FROM TRADER WHERE username='" + username + "') as contains";
		else if(userType.equalsIgnoreCase("Administrador")) sql = "SELECT EXISTS(SELECT username FROM ADMINISTRADOR WHERE username='" + username + "') as contains";

		ResultSet rs = null;

		if(!sql.equalsIgnoreCase("")) rs = statement.executeQuery(sql);
		boolean res = false;
		if (rs != null && rs.next()) {
			res = rs.getBoolean("contains");
		}
		return res;
	}

}