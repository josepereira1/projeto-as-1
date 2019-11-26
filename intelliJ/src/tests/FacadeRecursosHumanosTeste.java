package tests;

import tradingsystem.business.AtorNotExistsException;
import tradingsystem.business.AtorTypeNotValidException;
import tradingsystem.business.recursoshumanos.FacadeRecursosHumanos;
import tradingsystem.business.recursoshumanos.IFacadeRecursosHumanos;

import java.sql.SQLException;

public class FacadeRecursosHumanosTeste {

    public static void main(String[] args) {

        try {
            IFacadeRecursosHumanos rh = FacadeRecursosHumanos.getInstance();
            //rh.registarUtilizador("t_2", "password", 200f);
            System.out.println(rh.autenticarUtilizador("t_1", "password"));
            rh.addFundos("t_1", 10f);
            System.out.println(rh.autenticarUtilizador("t_1", "password"));
        } catch (SQLException e) {
            //e.printStackTrace();
            System.err.println("Could not access database!");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        } catch (AtorTypeNotValidException e) {
            //e.printStackTrace();
            System.err.println("Actor is not a Trader!");
        } catch (AtorNotExistsException e) {
            //e.printStackTrace();
            System.err.println("Actor with specified username does not exist!");
        } /*catch (AtorExistsException e) {
            // e.printStackTrace();
            System.err.println("Actor with specified username already exists!");
        }*/
    }
}
