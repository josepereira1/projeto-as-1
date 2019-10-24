package diagrama_de_classes;

public interface IFacadeTrading {

	Collection<Ativo> getAtivos();

	/**
	 * 
	 * @param idAtivo
	 * @param usernameVendedor
	 * @param usernameComprador
	 * @param tipo
	 * @param stopLess
	 * @param takeProfit
	 * @param numeroDeAtivos
	 */
	void abrirCFD(String idAtivo, String usernameVendedor, String usernameComprador, int tipo, float stopLess, float takeProfit, int numeroDeAtivos);

	/**
	 * 
	 * @param id
	 */
	void encerrarCFD(String id);

	/**
	 * 
	 * @param username
	 */
	Collection<CFD> getPortfolio(String username);

	/**
	 * 
	 * @param id
	 * @param TP
	 * @param SL
	 */
	void setCFDlimits(String id, float TP, float SL);

}