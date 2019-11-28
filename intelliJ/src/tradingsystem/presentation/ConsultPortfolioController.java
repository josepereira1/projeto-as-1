package tradingsystem.presentation;

import tradingsystem.Observer;
import tradingsystem.Subject;
import tradingsystem.TradingSystem;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockIdNotExistsException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class ConsultPortfolioController implements Runnable, Observer {

	private ConsultPortfolioView consultPortfolioView;
	private TradingSystem model;

	private Subject subject;	//	objeto observado

	public ConsultPortfolioController(Subject subject) throws SQLException, ClassNotFoundException {
		this.consultPortfolioView = new ConsultPortfolioView();
		this.model = TradingSystem.getInstance();
		this.subject = subject;
		this.subject.registerObserver(this);
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
					consultPortfolioView.displayPortfolio(model);
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
	public void update() {
		try {
			System.err.println("ENTREI NO UPDATE!");
			consultPortfolioView.displayPortfolio(model);
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