package tradingsystem.data;

import tradingsystem.business.trading.IAtivo;

import java.io.IOException;
import java.util.Collection;

public class AtivoDAOTeste {

    public static void main(String[] args) throws IOException {
        AtivoDAO ativoDAO = new AtivoDAO();
        Collection<IAtivo> ativos = ativoDAO.values();
        System.out.println(ativos);
        float valorAtualSNAP = ativoDAO.getValorAtual("SNAP");
        System.out.println(valorAtualSNAP);
    }
}
