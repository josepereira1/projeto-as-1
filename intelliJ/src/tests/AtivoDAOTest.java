package tests;


import tradingsystem.data.IAtivoDAO;

import java.io.IOException;

public class AtivoDAOTest {

    public static void main(String[] args) throws IOException {
        IAtivoDAO IAtivoDAO = new IAtivoDAO();
        System.out.println(IAtivoDAO.values());
        System.out.println(IAtivoDAO.getValorAtual("SNAP"));
    }
}
