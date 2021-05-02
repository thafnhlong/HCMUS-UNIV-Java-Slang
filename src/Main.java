import Menu.Menu;

public class Main {
    public static void main(String[] args) throws Exception {
        Menu dmenu = new Menu();
        while(true){
            dmenu.showMenu(0);
            int v = dmenu.readNumberInput(0, 10);
            if (!dmenu.process(0, v))
                break;
        }
    }
}