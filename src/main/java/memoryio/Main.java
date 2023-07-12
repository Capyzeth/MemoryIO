package memoryio;

import swingtree.SwingTreeContext;
import swingtree.threading.EventProcessor;
import swingtree.UI;

public class Main {
    public static void main(String... args){
        SwingTreeContext.get().setEventProcessor(EventProcessor.DECOUPLED);
        UI.show( f ->
                UI.use(EventProcessor.DECOUPLED, ()->new MainView(new MainViewModel()))
        );
        EventProcessor.DECOUPLED.join();
    }
}
