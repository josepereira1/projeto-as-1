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

        CFDDAO CFDDAO = new CFDDAO();
        CFDDAO.remove("1");
        System.out.println(CFDDAO.put(cfd).get());
        //System.out.println(CFDDAO.getCFDs("josepereira").get());
        System.out.println(CFDDAO.get("1").get());
        System.out.println(CFDDAO.getNextId().get());
        CFDDAO.setLimits(100f, -100f, "1");
        //System.out.println(CFDDAO.values().get());
        System.out.println(CFDDAO.contains("1").get());
        System.out.println(CFDDAO.getStopLess("1").get());
        System.out.println(CFDDAO.getTakeProfit("1").get());
        System.out.println(CFDDAO.getNumeroDeAtivosCFD("1").get());
        System.out.println(CFDDAO.getValorInvestidoCFD("1").get());
    }
}
