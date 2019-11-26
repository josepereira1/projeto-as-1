package tests;


import tradingsystem.data.AtivoDAO;

import java.io.IOException;

public class AtivoDAOTest {

    public static void main(String[] args) throws IOException {
        AtivoDAO ativoDAO = new AtivoDAO();
        System.out.println(ativoDAO.values());
        System.out.println(ativoDAO.getValorAtual("SNAP"));
    }
}
