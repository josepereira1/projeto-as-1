package tradingsystem.business;

public class StockIdNotExistsException extends Exception {
    public StockIdNotExistsException(){
        super();
    }

    public StockIdNotExistsException(String msg){
        super(msg);
    }
}
