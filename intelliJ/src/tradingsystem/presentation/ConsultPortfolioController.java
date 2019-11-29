package tradingsystem.presentation;

import tradingsystem.business.Observer;
import tradingsystem.business.SubjectAtivo;
import tradingsystem.business.SubjectCFD;
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

	private static ConsultPortfolioController consultPortfolioController;

	private ConsultPortfolioView consultPortfolioView;
	private TradingSystem model;

	private SubjectCFD subjectCFD;	//	objeto observado
	private SubjectAtivo subjectAtivo;	//	objeto observado

	private ConsultPortfolioController() throws SQLException, ClassNotFoundException, ExecutionException, InterruptedException {
		this.consultPortfolioView = new ConsultPortfolioView();
		this.model = TradingSystem.getInstance();

		this.subjectCFD = model.business.getCFDSubject().get();
		this.subjectCFD.registerObserver(this);
		this.subjectAtivo = model.business.getSubjectAtivo();
		this.subjectAtivo.registerObserver(this);
	}

	public static ConsultPortfolioController getInstance() throws ClassNotFoundException, SQLException, InterruptedException, ExecutionException {
		if (consultPortfolioController == null) consultPortfolioController = new ConsultPortfolioController();
		return consultPortfolioController;
	}

	@Override
	public void run() {
		try {

			consultPortfolioView.informAvailableOptions();
			consultPortfolioView.promptOption();

			switch (consultPortfolioView.option) {
				case "\\b":
					HomeController.getInstance().run();
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

			if(arg instanceof Object[]) {
				Object[] arr = (Object[]) arg;

				// Ativo alterou o preço
				if (arr[0] instanceof String && arr[1] instanceof Float){
					String id = (String) arr[0];
					float price = (float) arr[1];
					consultPortfolioView.notification(id, price);
				}

				// CFD encerrado automaticamento devido ao SL ou TP
				else if (arr[0] instanceof String && arr[1] instanceof Collection) {
					String id = (String) arr[0];
					Collection<ICFD> cfds = (Collection<ICFD>) arr[1];
					consultPortfolioView.informClosedCFD((String) arr[0]);
					consultPortfolioView.displayPortfolio(model, cfds);
				}
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