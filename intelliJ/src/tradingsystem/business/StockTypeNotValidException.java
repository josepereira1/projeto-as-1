package tradingsystem.business;

public class StockTypeNotValidException extends Exception {
    public StockTypeNotValidException(){
        super();
    }

    public StockTypeNotValidException(String msg){
        super(msg);
    }
}
