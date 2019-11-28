package tradingsystem.data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import tradingsystem.Observer;
import tradingsystem.SubjectAtivo;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockTypeNotValidException;
import tradingsystem.business.trading.IAtivo;
import tradingsystem.business.trading.TradingAbstractFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class AtivoDAO implements SubjectAtivo {

	/** Token used to access REST API server. */
	//private static final String APIToken = "demo";
	private static String APIToken = "IwkmgAv406p5lc2mJ3vXkww56P6cw9QIjPmtpW4I4e4weBztvCsji44H9NLr";
	private Map<String, Float> historicValuesOfStocks;
	private final static float INTERVAL = 5;
	private Collection<Observer> observers;
	private GenericActiveObject genericActiveObject;

	public AtivoDAO(){
		historicValuesOfStocks = new HashMap<>();
		observers = new ArrayList<>();
		genericActiveObject = new GenericActiveObject();

		/*new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(20000);
					historicValuesOfStocks.put("SNAP", 10000f);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();*/
	}

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

		List<String> urls = new ArrayList<>();
		//urls.add("https://api.worldtradingdata.com/api/v1/stock?symbol=AAPL,GOOG,FB,NFLX,TSLA&api_token=" + APIToken);
		//urls.add("https://api.worldtradingdata.com/api/v1/stock?symbol=NVDA,INTC,AMZN,V,MSFT&api_token=" + APIToken);
		//urls.add("https://api.worldtradingdata.com/api/v1/stock?symbol=TWTR,SNAP,VOD.L,BA,DIS&api_token=" + APIToken);
		urls.add("https://api.worldtradingdata.com/api/v1/stock?symbol=SNAP,TWTR,VOD.L&api_token=demo");

		for(String url: urls) {
			JSONArray arr = RESTGet(url).getJSONArray("data");
			for (int i = 0; i < arr.length(); i++) {
				JSONObject o = (JSONObject) arr.get(i); // gets() each IAtivo

				String id = o.getString("symbol");
				String designacao = o.getString("name");
				float valorCompra = o.getFloat("price");
				float valorVenda = valorCompra * 0.975f;

				IAtivo ativo;
				// TODO se conseguir na API saber o tipo meter o if() aqui
				if (true) ativo = TradingAbstractFactory.getInstance().createAtivo("ACAO");
				else throw new StockTypeNotValidException();

				ativo.setId(id);
				ativo.setDesignacao(designacao);
				ativo.setValorVenda(valorCompra);
				ativo.setValorCompra(valorVenda);
				res.add(ativo);
			}
		}

		return res;
	}


	/**
	 * Returns the current price of specified stock.
	 * @param id id of IAtivo.
	 * @param typeOfCFD type of CFD of CFD (Buy or Sell)
	 */
	 public float getValorAtual(String id, int typeOfCFD) throws IOException, CFDTypeNotValidException {
		//TODO quando utilizar o token "real" meter apenas o symbol=id (remover as restantes empresas)
		//String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=" + id + "&api_token=" + APIToken;
		String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=SNAP,TWTR,VOD.L&api_token=demo";

		float res = ((JSONObject) RESTGet(url).getJSONArray("data").get(0)).getFloat("price");

		//System.err.println(historicValuesOfStocks);

		 FutureTask<Void> futureTask = new FutureTask<>(() -> {	//	Isto é para verificar se o preço baixou um valor X
			 float beforeValue;
		 	if(historicValuesOfStocks.containsKey(id)){
				 beforeValue = historicValuesOfStocks.get(id);
				 if(Math.abs(beforeValue - res) >= INTERVAL){
					 historicValuesOfStocks.put(id, res);
					 notifyObservers(new Object[]{id,res});
				 }
			 }else historicValuesOfStocks.put(id, res);
		 	return null;
		 });

		 genericActiveObject.submit(futureTask);

		 try {
			 futureTask.get();
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 } catch (ExecutionException e) {
			 e.printStackTrace();
		 }

		 if(typeOfCFD == 0){	//	SELL
			return res*0.975f;
		}
		else if(typeOfCFD == 1) return res;	//	BUY
		else throw new CFDTypeNotValidException(String.valueOf(typeOfCFD));
	}


	public boolean contains(String id) throws IOException {
		//TODO quando utilizar o token "real" meter apenas o symbol=id (remover as restantes empresas)
		//String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=" + id + "&api_token=" + APIToken;
		String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=SNAP,TWTR,VOD.L&api_token=demo";
		JSONArray arr = RESTGet(url).getJSONArray("data");

		return arr != null;
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers(Object arg) {
		for(Observer observer : observers) observer.update(arg);
	}

	public SubjectAtivo getSubjectAtivo(){
	 	return this;
	}

}