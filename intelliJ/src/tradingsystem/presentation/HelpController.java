package tradingsystem.presentation;

public class HelpController {
    private HelpView helpView;

    //TODO ESTE CONTROLLER NÃO ESTÁ NO VPP

    public HelpController(){
        helpView = new HelpView();
    }

    public void run(){
        helpView.displayHelp();
    }
}
