package tests;

import tradingsystem.business.recursoshumanos.FactoryAtor;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.data.AtorDAO;
import tradingsystem.business.IAtorTypeNotValidException;

import java.sql.SQLException;

public class AtorDAOTests {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IAtorTypeNotValidException {

        IAtor admin = FactoryAtor.getInstance().createAtor("Administrador");
        admin.setUsername("paulo");
        admin.setPassword("password");

        AtorDAO AtorDAO = new AtorDAO();
        if (AtorDAO.contains("paulo", "Administrador") == false) AtorDAO.put(admin);
        System.out.println(AtorDAO.contains("paulo", "Administrador"));
        System.out.println(AtorDAO.get("paulo", "Administrador"));
    }
}
