package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.CFDTypeNotValidException;
import tradingsystem.business.StockIdNotExistsException;
import tradingsystem.business.trading.ICFD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ConsultPortfolioView {

	private static final int maxSpaces1 = 8; // Id ... Org.
	private static final int maxSpaces2 = maxSpaces1; // Org. ... Type
	private static final int maxSpaces3 = maxSpaces2; // Type ... Units
	private static final int maxSpaces4 = 10; // Units ... Init. Prc.
	private static final int maxSpaces5 = 15; //  Init. Prc. ... Curr. Prc.
	private static final int maxSpaces6 = maxSpaces5; //  Curr. Prc. ... Invested
	private static final int maxSpaces7 = 14; // Invested ... Balance
	private static final int maxSpaces8 = maxSpaces7; // Balance ... Total
	private static final int maxSpaces9 = maxSpaces7; // Total ... SL
	private static final int maxSpaces10 = maxSpaces7; // SL ... TP
	private static String header;

	public String option;

	public ConsultPortfolioView() {
		// Constructs Header Line -------------------------------------------
		// Como esta String nunca vai mudar pode ser gerada logo ao inicio
		StringBuilder sb = new StringBuilder();
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
		sb.append(str).append(" ".repeat(maxSpaces9-str.length()));
		str = "SL (-)";
		sb.append(str).append(" ".repeat(maxSpaces10-str.length()));
		str = "TP (+)";
		sb.append(str);
		sb.append("\n");
		header = sb.toString();
		// -------------------------------------------------------------------

		System.out.println("Type \\b to go back.");
		System.out.println("Type \\u to update portfolio.");
	}

	public void promptOption() {
		Scanner sc = new Scanner(System.in);
		System.out.print(">> ");
		if (sc.hasNextLine()) {
			option = sc.nextLine();
		}
	}

	public void informInvalidAction() {
		System.out.println("Please type a valid option.");
	}

	public void displayPortfolio(TradingSystem model) throws ExecutionException, InterruptedException, StockIdNotExistsException, CFDTypeNotValidException, IOException, AtorNotExistsException, SQLException {

		float total = 0f;
		float totalBalance = 0f;
		float totalInvested = 0f;
		float valor = 0f; // tmp variable

		Collection<ICFD> cfds =  model.business.getPortfolio(model.ator.getUsername());

		boolean skip = false;

		if (cfds.isEmpty()) {
			System.err.println("You haven't got any open CFD contract yet.");
			skip = true;
		}

		StringBuilder sb = new StringBuilder();

		if (skip == false) {

			sb.append(header); // fst line with identifiers


			for (ICFD cfd : cfds) {

				//TODO implementar o método Opened and Closed getCFDs()
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
				valor = cfd.getValorInvestido() + valor; // este <<valor>> mais á esquerda é o balance (assim aproveita-se a variável temporária)
				total += valor;
				String totalC = String.format("%.2f", valor);
				sb.append(totalC);
				sb.append(" ".repeat(maxSpaces9 - totalC.length()));

				// Stop Loss
				valor = cfd.getStopLess();
				if (valor == -1f) {
					sb.append("null");
					sb.append(" ".repeat(maxSpaces10 - 4));
				} else {
					String sl = String.format("%.2f", valor);
					sb.append(sl);
					sb.append(" ".repeat(maxSpaces10 - sl.length()));
				}

				// Take Profit
				valor = cfd.getTakeProfit();
				if (valor == -1f) {
					sb.append("null");
				} else {
					String tp = String.format("%.2f", valor);
					sb.append(tp);
				}
				sb.append("\n");
			}
		}

		// print Footer
		float plafond = model.business.getPlafond(model.ator.getUsername());
		sb.append("Total invested = ").append(String.format("%.2f", totalInvested)).append("\n");
		sb.append("Total balance = ").append(String.format("%.2f", totalBalance)).append("\n");
		sb.append("Plafond: ").append(String.format("%.2f", plafond)).append(" + ").append(String.format("%.2f", total));
		total += plafond;
		sb.append(" = ").append(String.format("%.2f", total)).append("\n");

		System.out.print(sb.toString());
	}


}