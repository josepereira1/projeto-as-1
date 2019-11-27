package tradingsystem.business;

public class CFDTypeNotValidException extends Exception {

    public CFDTypeNotValidException(){
        super();
    }

    public CFDTypeNotValidException(String msg){
        super(msg);
    }
}
