package tests;

import tradingsystem.TradingSystem;
import tradingsystem.business.*;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class PortfolioTeste {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ExecutionException, InterruptedException, IOException, StockIdNotExistsException, CFDNotExistsException, CFDTypeNotValidException, NoFundsToCFDException, AtorNotExistsException {

        TradingSystem model = TradingSystem.getInstance();

        String username = "t_ricardo";
        model.business.abrirCFD("SNAP", "t_ricardo", 1, -50f, 50f, 10); // BUY
        model.business.abrirCFD("SNAP", "t_ricardo", 1, -50f, 50f, 10); // BUY

        long totalBefore;
        long before, after;

        totalBefore = before = System.currentTimeMillis();

        after = System.currentTimeMillis();
        //System.err.println("getPortfolio() => " + (after - before));
        before = System.currentTimeMillis();

        int maxSpaces1 = 8; // Id ... Org.
        int maxSpaces2 = maxSpaces1; // Org. ... Type
        int maxSpaces3 = maxSpaces2; // Type ... Units
        int maxSpaces4 = 10; // Units ... Init. Prc.
        int maxSpaces5 = 15; //  Init. Prc. ... Curr. Prc.
        int maxSpaces6 = maxSpaces5; //  Curr. Prc. ... Invested
        int maxSpaces7 = maxSpaces5; // Invested ... Balance
        int maxSpaces8 = maxSpaces5; // Balance ... Total

        StringBuilder sb = new StringBuilder();


        while(true) {

            float total = 0f;
            float totalBalance = 0f;
            float totalInvested = 0f;
            float valor = 0f;

            // DRAW IDENTIFIERS  ----------------------------------------------
            String str = "Id";
            sb.append(str).append(" ".repeat(maxSpaces1-str.length()));
            str = "Org.";
            sb.append(str).append(" ".repeat(maxSpaces2-str.length()));
            str = "Type";
            sb.append(str).append(" ".repeat(maxSpaces3-str.length()));
            str = "Units";
            sb.append(str).append(" ".repeat(maxSpaces4-str.length()));
            str = "Init. Prc.";
            sb.append(str).append(" ".repeat(maxSpaces5-str.length()));
            str = "Curr. Prc.";
            sb.append(str).append(" ".repeat(maxSpaces6-str.length()));
            str = "Invested";
            sb.append(str).append(" ".repeat(maxSpaces7-str.length()));
            str = "Balance";
            sb.append(str).append(" ".repeat(maxSpaces8-str.length()));
            str = "Total";
            sb.append(str);
            sb.append("\n");
            // --------------------------------------------------------------

            Collection<ICFD> cfds = model.business.getPortfolio(username);
            for (ICFD cfd : cfds) {

                if (cfd.getDataEncerramento() != null) continue; // já foi encerrado

                // Id
                String idCFD = cfd.getId();
                sb.append(idCFD);
                sb.append(" ".repeat(maxSpaces1 - idCFD.length()));

                // Org
                String idAtivo = cfd.getIdAtivo();
                sb.append(idAtivo);
                sb.append(" ".repeat(maxSpaces2 - idAtivo.length()));

                // Type
                if (cfd.getTipo() == 0) { // 0 - SELL
                    sb.append("Sell");
                    sb.append(" ".repeat(maxSpaces3 - 4));
                } else if (cfd.getTipo() == 1) { // 1 - BUY
                    sb.append("Buy");
                    sb.append(" ".repeat(maxSpaces3 - 3));
                }

                // Units
                String numeroDeAtivos = Integer.toString(cfd.getNumeroDeAtivos());
                sb.append(numeroDeAtivos);
                sb.append(" ".repeat(maxSpaces4 - numeroDeAtivos.length()));

                // Initial Price
                if (cfd.getTipo() == 0) { // 0 - SELL
                    valor = cfd.getValorInicial();
                    String valorInicial = String.format("%.2f", valor) + " (S)";
                    sb.append(valorInicial);
                    sb.append(" ".repeat(maxSpaces5 - valorInicial.length()));
                } else if (cfd.getTipo() == 1) { // 1 - BUY
                    valor = cfd.getValorInicial();
                    String valorInicial = String.format("%.2f", valor) + " (B)";
                    sb.append(valorInicial);
                    sb.append(" ".repeat(maxSpaces5 - valorInicial.length()));
                }

                // Current Price
                if (cfd.getTipo() == 0) { // 0 - SELL
                    valor = model.business.getValorAtualAtivo(cfd.getIdAtivo(), 1);
                    String valorAtual = String.format("%.2f", valor) + " (B)";
                    sb.append(valorAtual);
                    sb.append(" ".repeat(maxSpaces6 - valorAtual.length()));
                } else if (cfd.getTipo() == 1) { // 1 - BUY
                    valor = model.business.getValorAtualAtivo(cfd.getIdAtivo(), 0);
                    String valorAtual = String.format("%.2f", valor) + " (S)";
                    sb.append(valorAtual);
                    sb.append(" ".repeat(maxSpaces6 - valorAtual.length()));
                }

                float valorAtualDoAtivo = valor; // guardar o valor atual do ativo

                // Invested
                valor = cfd.getValorInvestido();
                totalInvested += valor;
                String invested = String.format("%.2f", valor);
                sb.append(invested);
                sb.append(" ".repeat(maxSpaces7 - invested.length()));

                // Balance
                valor = cfd.getBalanco(valorAtualDoAtivo);
                totalBalance += valor;
                String balance = String.format("%.2f", valor);
                sb.append(balance);
                sb.append(" ".repeat(maxSpaces8 - balance.length()));

                // Total
                valor = cfd.getValorInvestido() + valor; // este valor mais á esquerda é o balance
                total += valor;
                sb.append(String.format("%.2f", valor));
                sb.append("\n");
            }

            sb.append("Total invested = ").append(String.format("%.2f", totalInvested)).append("\n");
            sb.append("Total balance = ").append(String.format("%.2f", totalBalance)).append("\n");
            sb.append("Plafond: 200 + ").append(String.format("%.2f", total)); //TODO implementar o getPlafond(String username);
            total += 200;
            sb.append(" = ").append(String.format("%.2f", total)).append("\n\n");

            /*after = System.currentTimeMillis();
            System.err.println("for() => " + (after - before));
            System.err.println("total => " + (after - totalBefore));*/

            System.out.print(sb.toString());
            sb.setLength(0); // resets String Builder

            Thread.sleep(1000 * 3);
        }

        //System.exit(1);
    }
}
