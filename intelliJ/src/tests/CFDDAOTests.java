package tests;

import tradingsystem.business.trading.CFD;
import tradingsystem.business.trading.ICFD;
import tradingsystem.data.CFDDAO;

import java.time.LocalDateTime;

public class CFDDAOTests {
    public static void main(String[] args) throws Exception {
        CFDDAO cfddao = new CFDDAO();
        cfddao.remove("1");
        //ICFD cfd = new CFD("1","1",0, "josepereira", 0,0, LocalDateTime.now(),  null,10, 100, 1000);
        //System.out.println(cfddao.put(cfd).get());
        System.out.println(cfddao.getCFDs("josepereira").get());
        System.out.println(cfddao.get("1").get());
        System.out.println(cfddao.getLastId().get());
        cfddao.setLimits(100, -100, "1");
        System.out.println(cfddao.values().get());
        System.out.println(cfddao.contains("1").get());
        System.out.println(cfddao.getStopLess("1").get());
        System.out.println(cfddao.getTakeProfit("1").get());
        System.out.println(cfddao.getNumeroDeAtivosCFD("1").get());
        System.out.println(cfddao.getValorInvestidoCFD("1").get());
    }
}
