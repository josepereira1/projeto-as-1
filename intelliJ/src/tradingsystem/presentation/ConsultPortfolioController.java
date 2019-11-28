package tradingsystem.presentation;

import tradingsystem.Observer;
import tradingsystem.SubjectAtivo;
import tradingsystem.SubjectCFD;
import tradingsystem.TradingSystem;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockIdNotExistsException;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;


public class ConsultPortfolioController implements Runnable, Observer {

	private ConsultPortfolioView consultPortfolioView;
	private TradingSystem model;

	private SubjectCFD subjectCFD;	//	objeto observado
	private SubjectAtivo subjectAtivo;	//	objeto observado

	public ConsultPortfolioController() throws SQLException, ClassNotFoundException, ExecutionException, InterruptedException {
		this.consultPortfolioView = new ConsultPortfolioView();
		this.model = TradingSystem.getInstance();
		this.subjectCFD = model.business.getCFDSubject().get();
		this.subjectCFD.registerObserver(this);
		this.subjectAtivo = model.business.getSubjectAtivo();
		this.subjectAtivo.registerObserver(this);
	}

	@Override
	public void run() {
		try {

			consultPortfolioView.promptOption();

			switch (consultPortfolioView.option) {
				case "\\b":
					new HomeController().run();
					break;
				case "\\u":
					consultPortfolioView.displayPortfolio(model, model.business.getPortfolio(model.ator.getUsername()));
					this.run();
					break;
				default:
					consultPortfolioView.informInvalidAction();
					this.run();
					break;
			}

			//TODO falta meter mensagens de erro para cada Exception

		} catch (ExecutionException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (StockIdNotExistsException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (CFDTypeNotValidException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (IOException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (SQLException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (AtorNotExistsException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		}
	}

	@Override
	public void update(Object arg) {
		try {

			if (arg instanceof Collection) {
				consultPortfolioView.displayPortfolio(model, (Collection<ICFD>) arg);
			}

			if(arg instanceof Object[]){
				String id = (String) ((Object[])arg)[0];
				float price = (float) ((Object[])arg)[1];
				consultPortfolioView.notification(id, price);
			}
		} catch (StockIdNotExistsException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (CFDTypeNotValidException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (IOException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (AtorNotExistsException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		} catch (SQLException e) {
			//e.printStackTrace();
			consultPortfolioView.error();
			System.exit(1);
		}
	}
}