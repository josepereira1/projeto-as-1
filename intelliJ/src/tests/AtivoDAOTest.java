package tests;

import tradingsystem.business.trading.IAtivo;
import tradingsystem.data.AtivoDAO;

import java.io.IOException;
import java.util.Collection;

public class AtivoDAOTest {

    public static void main(String[] args) throws IOException {
        AtivoDAO ativoDAO = new AtivoDAO();
        Collection<IAtivo> ativos = ativoDAO.values();
        System.out.println(ativos);
        float valorAtualSNAP = ativoDAO.getValorAtual("SNAP");
        System.out.println(valorAtualSNAP);
    }
}
