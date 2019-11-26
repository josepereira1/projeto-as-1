package tests;

import tradingsystem.business.trading.FacadeTrading;
import tradingsystem.business.trading.IFacadeTrading;
import tradingsystem.data.FacadeData;
import tradingsystem.data.IFacadeData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class FacadeTradingTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ExecutionException, InterruptedException, IOException {
        IFacadeData data = FacadeData.getInstance();

        IFacadeTrading facadeTrading = FacadeTrading.getInstance();

        facadeTrading.abrirCFD("1","josepereira",0, 100,200, 1000);
    }
}
