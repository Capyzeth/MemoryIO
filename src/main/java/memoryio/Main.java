package memoryio;

import swingtree.UI;

public class Main {
    public static void main(String... args){
        UI.show( f -> new MainView(new MainViewModel()));
    }
}
