package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockIdNotExistsException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class ConsultPortfolioController implements Runnable {

	private ConsultPortfolioView consultPortfolioView;
	private TradingSystem model;

	public ConsultPortfolioController() throws SQLException, ClassNotFoundException {
		this.consultPortfolioView = new ConsultPortfolioView();
		this.model = TradingSystem.getInstance();
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
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (StockIdNotExistsException e) {
			e.printStackTrace();
		} catch (CFDTypeNotValidException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (AtorNotExistsException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}