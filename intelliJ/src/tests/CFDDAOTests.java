package tests;

import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.TradingAbstractFactory;
import tradingsystem.data.ICFDDAO;

import java.time.LocalDateTime;

public class CFDDAOTests {
    public static void main(String[] args) throws Exception {

        ICFD cfd = TradingAbstractFactory.getInstance().createCFD("CFD");
        cfd.setId("1");
        cfd.setIdAtivo("1");
        cfd.setNumeroDeAtivos(0);
        cfd.setUsername("josepereira");
        cfd.setStopLess(0f);
        cfd.setTakeProfit(0f);
        cfd.setDataAbertura(LocalDateTime.now());
        cfd.setDataEncerramento(null);
        cfd.setNumeroDeAtivos(10);
        cfd.setValorInicial(100f);
        cfd.setValorInvestido(cfd.getNumeroDeAtivos()*cfd.getValorInicial());

        ICFDDAO ICFDDAO = new ICFDDAO();
        ICFDDAO.remove("1");
        System.out.println(ICFDDAO.put(cfd).get());
        System.out.println(ICFDDAO.getCFDs("josepereira").get());
        System.out.println(ICFDDAO.get("1").get());
        System.out.println(ICFDDAO.getLastId().get());
        ICFDDAO.setLimits(100f, -100f, "1");
        System.out.println(ICFDDAO.values().get());
        System.out.println(ICFDDAO.contains("1").get());
        System.out.println(ICFDDAO.getStopLess("1").get());
        System.out.println(ICFDDAO.getTakeProfit("1").get());
        System.out.println(ICFDDAO.getNumeroDeAtivosCFD("1").get());
        System.out.println(ICFDDAO.getValorInvestidoCFD("1").get());
    }
}
