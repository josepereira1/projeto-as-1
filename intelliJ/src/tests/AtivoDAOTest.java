package tests;


import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockTypeNotValidException;
import tradingsystem.data.AtivoDAO;
import tradingsystem.business.AtorTypeNotValidException;

import java.io.IOException;

public class AtivoDAOTest {

    public static void main(String[] args) throws IOException, StockTypeNotValidException {
        AtivoDAO AtivoDAO = new AtivoDAO();
        /*System.out.println(AtivoDAO.values());
        System.out.println(AtivoDAO.getValorAtual("SNAP"));*/
        //System.out.println(AtivoDAO.values());
        //System.out.println(AtivoDAO.contains("CONA"));

        try {
            System.out.println(AtivoDAO.getValorAtual("SNAP", 1));
        } catch (CFDTypeNotValidException e) {
            e.printStackTrace();
        }
    }
}
