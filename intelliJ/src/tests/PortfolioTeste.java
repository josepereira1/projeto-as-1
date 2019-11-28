package tests;

import tradingsystem.TradingSystem;
import tradingsystem.business.*;
import tradingsystem.business.recursoshumanos.Trader;
import tradingsystem.presentation.ConsultPortfolioView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class PortfolioTeste {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ExecutionException, InterruptedException, IOException, StockIdNotExistsException, CFDNotExistsException, CFDTypeNotValidException, NoFundsToCFDException, AtorNotExistsException {

        TradingSystem model = TradingSystem.getInstance();

        model.ator = new Trader();
        model.ator.setUsername("t_ricardo");
        //model.business.abrirCFD("SNAP", "t_ricardo", 1, -50f, 50f, 10); // BUY
        //model.business.abrirCFD("SNAP", "t_ricardo", 1, -50f, 50f, 10); // BUY

        ConsultPortfolioView view = new ConsultPortfolioView();
        view.displayPortfolio(model);
    }
}
