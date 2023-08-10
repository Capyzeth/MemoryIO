package memoryio;

import swingtree.SwingTree;
import swingtree.threading.EventProcessor;
import swingtree.UI;

public class Main {
    public static void main(String... args){
        UI.show( f -> {
            f.setTitle("Memory IO");
            f.setIconImage(UI.icon("/platypussies/p1.png").getImage());
            SwingTree.get().setEventProcessor(EventProcessor.DECOUPLED);
            return UI.use(EventProcessor.DECOUPLED, () -> new MainView(new MainViewModel()));
        });
        EventProcessor.DECOUPLED.join();
    }
}
