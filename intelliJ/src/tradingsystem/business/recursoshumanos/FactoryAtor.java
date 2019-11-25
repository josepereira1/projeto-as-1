package tradingsystem.business.recursoshumanos;

public class FactoryAtor {
	/**
	 * 
	 * @param type
	 */
	public IAtor createAtor(String type) {
		if(type.equalsIgnoreCase("Trader"))return new Trader();
		else if(type.equalsIgnoreCase("Administrador")) return new Administrador();
		return null;
	}
}