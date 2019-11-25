package tests;

import tradingsystem.business.recursoshumanos.FactoryAtor;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.business.recursoshumanos.Trader;
import tradingsystem.data.UtilizadorDAO;

import java.sql.SQLException;

public class UtilizadorDAOTests {
    public static void main(String[] args){
        FactoryAtor factoryAtor = new FactoryAtor();
        UtilizadorDAO utilizadorDAO = new UtilizadorDAO(factoryAtor);

        //IAtor user = factoryAtor.createAtor("Administrador");
        /*user.setUsername("paulo");
        user.setPassword("password");*/

        /*IAtor ator = null;

        try {
            ator = utilizadorDAO.get("josepereira", "Trader");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(ator);
        */
        /*try {
            System.out.println(utilizadorDAO.contains("elon musk", "administrador"));
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {
            utilizadorDAO.addFundos("josepereira", 25);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
