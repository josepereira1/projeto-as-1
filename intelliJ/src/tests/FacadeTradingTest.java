package tests;

import tradingsystem.business.CFDNotExistsException;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockIdNotExistsException;
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

        /*try {
            facadeTrading.abrirCFD("SNAP","josepereira",0, 100,200, 1000);
        } catch (StockIdNotExistsException e) {
            e.printStackTrace();
        }*/

        //System.out.println(facadeTrading.getPortfolio("josepereira"));

        /*try {
            facadeTrading.setCFDlimits("9999", 1.5f, 9.9f);
        } catch (CFDNotExistsException e) {
            e.printStackTrace();
        }*/

        /*try {
            System.out.println(facadeTrading.getValorAtualAtivo("SNAP") + "$");
            System.out.println(facadeTrading.getValorAtualAtivo("LOJA DO MESTRE ANDRÉ") + "$");
        } catch (StockIdNotExistsException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(facadeTrading.getBalanco("0"));
        } catch (CFDNotExistsException e) {
            e.printStackTrace();
        }*/



        /*try {
            System.out.println(facadeTrading.encerrarCFD("1"));
        } catch (CFDNotExistsException e) {
            e.printStackTrace();
        }*/


        /*try {
            System.out.println(facadeTrading.encerrarCFD("0"));
        } catch (CFDNotExistsException e) {
            e.printStackTrace();
        }*/

        try {
            System.out.println(facadeTrading.getBalanco("99999"));
        } catch (CFDNotExistsException e) {
            e.printStackTrace();
        } catch (CFDTypeNotValidException e) {
            e.printStackTrace();
        }
    }
}
