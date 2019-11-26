package tests;

import tradingsystem.business.recursoshumanos.FactoryAtor;
import tradingsystem.business.recursoshumanos.IAtor;
import tradingsystem.data.IAtorDAO;
import tradingsystem.data.IAtorTypeNotValid;

import java.sql.SQLException;

public class IAtorDAOTests {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IAtorTypeNotValid {

        IAtor admin = FactoryAtor.getInstance().createAtor("Administrador");
        admin.setUsername("paulo");
        admin.setPassword("password");

        IAtorDAO IAtorDAO = new IAtorDAO();
        // atorDAO.put(admin);
        System.out.println(IAtorDAO.contains("paulo", "Adrador"));
        System.out.println(IAtorDAO.get("paulo", "Administrador"));
    }
}
