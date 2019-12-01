package tradingsystem.business;

public class CFDNotExistsException extends Exception {
    public CFDNotExistsException(){
        super();
    }

    public CFDNotExistsException(String msg){
        super(msg);
    }
}
