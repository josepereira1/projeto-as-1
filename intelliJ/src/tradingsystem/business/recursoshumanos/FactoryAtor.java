package tradingsystem.business.recursoshumanos;


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
	public IAtor createAtor(String type) {
		if(type.equalsIgnoreCase("Trader"))return new Trader();
		else if(type.equalsIgnoreCase("Administrador")) return new Administrador();
		return null;
	}

	public static FactoryAtor getInstance() {
		if (factoryAtor == null) factoryAtor = new FactoryAtor();
		return factoryAtor;
	}
}