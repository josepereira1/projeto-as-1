package tradingsystem.business;

public class NoFundsToCFDException extends Exception {
    public NoFundsToCFDException(){
        super();
    }

    public NoFundsToCFDException(String msg){
        super(msg);
    }
}
