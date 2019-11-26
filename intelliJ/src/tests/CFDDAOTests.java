package tests;

import tradingsystem.business.trading.ICFD;
import tradingsystem.business.trading.TradingAbstractFactory;
import tradingsystem.data.CFDDAO;

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

        CFDDAO cfddao = new CFDDAO();
        cfddao.remove("1");
        System.out.println(cfddao.put(cfd).get());
        System.out.println(cfddao.getCFDs("josepereira").get());
        System.out.println(cfddao.get("1").get());
        System.out.println(cfddao.getLastId().get());
        cfddao.setLimits(100f, -100f, "1");
        System.out.println(cfddao.values().get());
        System.out.println(cfddao.contains("1").get());
        System.out.println(cfddao.getStopLess("1").get());
        System.out.println(cfddao.getTakeProfit("1").get());
        System.out.println(cfddao.getNumeroDeAtivosCFD("1").get());
        System.out.println(cfddao.getValorInvestidoCFD("1").get());
    }
}
