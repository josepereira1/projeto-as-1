package tests;

import tradingsystem.business.trading.CFD;
import tradingsystem.business.trading.ICFD;
import tradingsystem.data.CFDDAO;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CFDDAOTests {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CFDDAO cfddao = new CFDDAO();
        //ITradingAbstractFactory iTradingAbstractFactory = TradingAbstractFactory.getInstance();
        ICFD cfd = new CFD("1","1",0, "josepereira", 0,0, LocalDateTime.now(),  LocalDateTime.now(),10, 100, 1000);

        Future<ICFD> futureTask = cfddao.put("1", cfd);

        System.out.println("Executei a query, e obtive um future, por isso é que este print já apareceu mas ainda não obtive o valor da quey!!!");

        System.out.println(futureTask.get());
    }
}
