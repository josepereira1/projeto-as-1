package diagrama_de_classes;

public class FacadeTrading implements IFacadeTrading {

	private FacadeData data;

	public Collection<Ativo> getAtivos() {
		// TODO - implement FacadeTrading.getAtivos
		throw new UnsupportedOperationException();
	}

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
	public void abrirCFD(String idAtivo, String usernameVendedor, String usernameComprador, int tipo, float stopLess, float takeProfit, int numeroDeAtivos) {
		// TODO - implement FacadeTrading.abrirCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void encerrarCFD(String id) {
		// TODO - implement FacadeTrading.encerrarCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 */
	public Collection<CFD> getPortfolio(String username) {
		// TODO - implement FacadeTrading.getPortfolio
		throw new UnsupportedOperationException();
	}

}