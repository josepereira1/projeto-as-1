package tradingsystem.presentation;

public class HelpView {
    public static String helpOptions;

    public HelpView(){
        helpOptions = helpOption();
    }

    public static String helpOption(){
        StringBuilder sb = new StringBuilder("\\?  ou \\help                open this frame\n");
        sb.append("\\login                           login frame\n");
        sb.append("\\shutdown                 shut down application\n");
        sb.append("\\cancel                        cancel any process (register, buy, sell, …)\n");
        sb.append("\\q ou \\quit ou \\logout ou CTRL-D     sair da app\n");
        sb.append("\\register                      register frame\n");
        sb.append("\\portfolio                    show portfolio\n");
        sb.append("\\b ou \\buy                   buy/long active\n");
        sb.append("\\s ou \\sell                   sell/short active\n");
        sb.append("\\limits                          set limits to active\n");
        return sb.toString();
    }

    public void displayHelp(){
        System.out.println(helpOptions);
    }
}
