package memoryio;

import swingtree.SwingTreeContext;
import swingtree.threading.EventProcessor;
import swingtree.UI;

public class Main {
    public static void main(String... args){
        UI.show( f -> {
            SwingTreeContext.get().setEventProcessor(EventProcessor.DECOUPLED);
            return UI.use(EventProcessor.DECOUPLED, () -> new MainView(new MainViewModel()));
        });
        EventProcessor.DECOUPLED.join();
    }
}
