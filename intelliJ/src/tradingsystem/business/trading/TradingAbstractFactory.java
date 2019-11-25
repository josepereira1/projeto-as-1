package tradingsystem.business.trading;

public class TradingAbstractFactory implements ITradingAbstractFactory {

	private static ITradingAbstractFactory abstractFactory;

	/**
	 * Create an Ativo
	 * @param type type of Ativo (Acao or Commoditie)
	 */
	public IAtivo createAtivo(String type) {
		if(type.equals("Acao"))return new Acao();
		else if(type.equals("Commoditie")) return new Commoditie();
		return null;
	}

	/**
	 * Create an CFD.
	 * @param type type of CFD (CFD)
	 */
	public ICFD createCFD(String type) {
		return new CFD();
	}

	/**
	 * Return this object if exists otherwise create.
	 * @return Return this object if exists otherwise create
	 */
	public static ITradingAbstractFactory getInstance() {
		if(abstractFactory != null)return abstractFactory;
		else return new TradingAbstractFactory();
	}

}