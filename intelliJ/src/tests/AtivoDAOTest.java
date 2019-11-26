package tests;


import tradingsystem.data.AtivoDAO;
import tradingsystem.business.AtorTypeNotValidException;

import java.io.IOException;

public class AtivoDAOTest {

    public static void main(String[] args) throws IOException, AtorTypeNotValidException {
        AtivoDAO AtivoDAO = new AtivoDAO();
        System.out.println(AtivoDAO.values());
        System.out.println(AtivoDAO.getValorAtual("SNAP"));
    }
}
