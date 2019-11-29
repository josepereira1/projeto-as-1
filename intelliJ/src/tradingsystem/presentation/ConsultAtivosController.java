package tradingsystem.presentation;

import tradingsystem.TradingSystem;
import tradingsystem.business.*;

import java.io.IOException;
import java.sql.SQLException;

public class ConsultAtivosController implements Runnable {

    private static ConsultAtivosController consultAtivosController;

    public ConsultAtivosView consultAtivosView;
    public TradingSystem model;

    private ConsultAtivosController() throws SQLException, ClassNotFoundException {
        this.consultAtivosView = new ConsultAtivosView();
        this.model = TradingSystem.getInstance();
    }

    public static ConsultAtivosController getInstance() throws SQLException, ClassNotFoundException {
        if (consultAtivosController == null) consultAtivosController = new ConsultAtivosController();
        return consultAtivosController;
    }

    @Override
    public void run() {

        try {

            consultAtivosView.informAvaiblableOptions();
            consultAtivosView.promptOption();

            switch (consultAtivosView.option) {
                case "\\b":
                    HomeController.getInstance().run();
                    break;
                case "\\u":
                    consultAtivosView.displayAtivos(model.business.getAtivos());
                    this.run();
                    break;
                default:
                    consultAtivosView.informInvalidAction();
                    this.run();
                    break;
            }

            //TODO falta meter mensagens de erro para cada Exception

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AtorTypeNotValidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (StockTypeNotValidException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
