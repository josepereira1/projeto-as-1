package tradingsystem.presentation;

import tradingsystem.business.trading.IAtivo;

import java.util.Collection;
import java.util.Scanner;

public class ConsultAtivosView {

    private static final int maxSpaces1 = 12; // Id ... Org.
    private static final int maxSpaces2 = 30; // Org. ... Buy Prc.
    private static final int maxSpaces3 = 15; // Buy Prc. ... Sell Prc.
    private static String header;

    String option;

    public ConsultAtivosView() {
        // Constructs Header Line -------------------------------------------
        // Como esta String nunca vai mudar pode ser gerada logo ao inicio
        StringBuilder sb = new StringBuilder();
        String str = "Id";
        sb.append(str).append(" ".repeat(maxSpaces1-str.length()));
        str = "Org.";
        sb.append(str).append(" ".repeat(maxSpaces2-str.length()));
        str = "Buy Prc.";
        sb.append(str).append(" ".repeat(maxSpaces3-str.length()));
        str = "Sell Prc.";
        sb.append(str);
        sb.append("\n");
        header = sb.toString();
        // -------------------------------------------------------------------
    }

    public void informAvaiblableOptions() {
        System.out.println("Type \\b to go back.");
        System.out.println("Type \\u to update frame.");
    }

    public void promptOption() {
        option = "";
        Scanner sc = new Scanner(System.in);
        System.out.print(">> ");
        if (sc.hasNextLine()) {
            option = sc.nextLine();
        }
    }

    public void informInvalidAction() {
        System.out.println("Please type a valid option.");
    }

    public void displayAtivos(Collection<IAtivo> ativos) {

        StringBuilder sb = new StringBuilder();

        sb.append(header); // fst line with identifiers

        for (IAtivo ativo : ativos) {

            float valor;

            // Id
            String idAtivo = ativo.getId();
            sb.append(idAtivo);
            sb.append(" ".repeat(maxSpaces1 - idAtivo.length()));

            // Org
            String org = ativo.getDesignacao();
            sb.append(org);
            sb.append(" ".repeat(maxSpaces2 - org.length()));

            // Buy Price
            valor = ativo.getValorCompra();
            String valorCompra = String.format("%.2f", valor);
            sb.append(valorCompra);
            sb.append(" ".repeat(maxSpaces3 - valorCompra.length()));

            // Sell Price
            valor = ativo.getValorVenda();
            String valorVenda = String.format("%.2f", valor);
            sb.append(valorVenda);
            sb.append(" ".repeat(maxSpaces3 - valorVenda.length()));

            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
