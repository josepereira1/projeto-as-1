package tradingsystem.data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import tradingsystem.business.StockTypeNotValidException;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.TradingAbstractFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public class AtivoDAO {

	/** Token used to access REST API server. */
	private static final String APIToken = "demo";
	//private static String APIToken = "IwkmgAv406p5lc2mJ3vXkww56P6cw9QIjPmtpW4I4e4weBztvCsji44H9NLr";

	/**
	 * Returns a JSONObject containing information obtained by method GET.
	 * @param url url in String.
	 */
	private static JSONObject RESTGet(String url) throws IOException {
		return new JSONObject(new JSONTokener(new URL(url).openStream()));
	}

	/**
	 * Returns a Collection containing all IAtivos from REST server.
	 */
	public Collection<IAtivo> values() throws IOException, StockTypeNotValidException {
		Collection<IAtivo> res = new ArrayList<>();
		String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=SNAP,TWTR,VOD.L&api_token=" + APIToken;
		JSONArray arr = RESTGet(url).getJSONArray("data");
		for(int i=0; i<arr.length(); i++) {
			JSONObject o = (JSONObject) arr.get(i); // gets() each IAtivo

			String id = o.getString("symbol");
			String designacao = o.getString("name");
			float valorVenda = o.getFloat("price");
			float min=0.97f, max=0.99f;
			float underPercentage = min + new Random().nextFloat() * (max - min);
			float valorCompra = valorVenda*underPercentage;

			IAtivo ativo = null;
			// TODO se conseguir na API saber o tipo meter o if() aqui
			if (true) ativo = TradingAbstractFactory.getInstance().createAtivo("ACAO");
			else throw new StockTypeNotValidException();

			ativo.setId(id);
			ativo.setDesignacao(designacao);
			ativo.setValorVenda(valorVenda);
			ativo.setValorCompra(valorCompra);
			res.add(ativo);
		}
		return res;
	}


	/**
	 * Returns the current price of specified stock.
	 * @param id id of IAtivo.
	 */
	public float getValorAtual(String id) throws IOException {
		//TODO quando utilizar o token "real" meter apenas o symbol=id (remover as restantes empresas)
		//String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=" + id + "&api_token=" + APIToken;
		String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=SNAP,TWTR,VOD.L&api_token=" + APIToken;
		return ((JSONObject) RESTGet(url).getJSONArray("data").get(0)).getFloat("price");
	}

}