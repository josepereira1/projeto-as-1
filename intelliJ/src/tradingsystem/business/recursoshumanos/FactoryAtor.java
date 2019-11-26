package tradingsystem.business.recursoshumanos;


import tradingsystem.business.IAtorTypeNotValidException;

public class FactoryAtor {

	private static FactoryAtor factoryAtor;

	/**
	 *
	 */
	private FactoryAtor() {

	}

	/**
	 * 
	 * @param type
	 */
	public IAtor createAtor(String type) throws IAtorTypeNotValidException {
		if(type.equalsIgnoreCase("Trader")) return new Trader();
		else if(type.equalsIgnoreCase("Administrador")) return new Administrador();
		else throw new IAtorTypeNotValidException(type);
	}

	public static FactoryAtor getInstance() {
		if (factoryAtor == null) factoryAtor = new FactoryAtor();
		return factoryAtor;
	}
}