package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class CloseCFDSController implements Runnable {

    private static CloseCFDSController closeCFDSController;

    private CloseCFDSView closeCFDSView;
    private TradingSystem model;

    private CloseCFDSController() throws SQLException, ClassNotFoundException {
        closeCFDSView = new CloseCFDSView();
        model = TradingSystem.getInstance();
    }

    public static CloseCFDSController getInstance() throws SQLException, ClassNotFoundException {
        if (closeCFDSController == null) closeCFDSController = new CloseCFDSController();
        return closeCFDSController;
    }

    @Override
    public void run() {
        try {
            closeCFDSView.closeCFD();
            model.business.encerrarCFD(closeCFDSView.CFDId, model.ator.getUsername());
            closeCFDSView.sucess();
            HomeController.getInstance().run();

        } catch (InvalidInputException e) {
            //e.printStackTrace();
            closeCFDSView.inputError();
            this.run();
        } catch (CFDNotExistsException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (AtorNotExistsException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (CFDTypeNotValidException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (ExecutionException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (SQLException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (IOException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (AtorTypeNotValidException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            closeCFDSView.error();
            System.exit(1);
        }
    }
}
