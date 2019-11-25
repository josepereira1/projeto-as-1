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
	public IAtor put(IAtor value) throws SQLException {
		// TODO - implement UtilizadorDAO.put
		Statement statement = conn.createStatement();
		String sql = "";

		if(value instanceof Trader)sql = queryTrader(value);
		else if (value instanceof Administrador)sql = queryAdministrador(value);

		statement.executeUpdate(sql);
		return null;
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

		if(userType.equalsIgnoreCase("Trader")) user = getQueryTrader(statement, userType);
		else if(userType.equalsIgnoreCase("Administrador"))  user = getQueryAdmnistrador(statement, userType);

		return user;
	}

	private IAtor getQueryTrader(Statement statement, String userType) throws SQLException {
		String sql = "SELECT * FROM TRADER WHERE username='" + username + "'";

		ResultSet resultSet = statement.executeQuery(sql);

		IAtor user = factoryAtor.createAtor(userType);

		while(resultSet.next()){
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			((Trader)user).setPlafond(resultSet.getFloat("plafond"));
		}

		return user;
	}

	private IAtor getQueryAdmnistrador(Statement statement, String userType) throws SQLException {
		String sql = "SELECT * FROM ADMINISTRADOR WHERE username='" + username + "'";

		ResultSet resultSet = statement.executeQuery(sql);

		IAtor user = factoryAtor.createAtor(userType);

		while(resultSet.next()){
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
		}
		return user;
	}

	/**
	 * 
	 * @param username
	 * @param valor
	 */
	public void addFundos(String username, float valor) {
		// TODO - implement UtilizadorDAO.addFundos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public boolean contains(String username) {
		// TODO - implement UtilizadorDAO.contains
		throw new UnsupportedOperationException();
	}

}