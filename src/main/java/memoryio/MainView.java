package memoryio;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

import static swingtree.UI.*;

public class MainView extends Panel{
    public MainView(MainViewModel vm){
        of(this).add(
            panel("wrap 2").withPrefSize(300,200)
            .add(
                panel("wrap 1")
                .withStyle(it -> it.border(3,"blue").padding(12).margin(12).borderRadius(16))
                .add(label("Welcome to MemoryIO"))
                .add(
                    panel("wrap 2")
                    .add(label("Board width:"))
                    .add(spinner(vm.getWidth()))
                    .add(label("Board height:"))
                    .add(spinner(vm.getHeight()))
                )
            )
            .add(
                button("Play")
                .onClick(it -> vm.startGame())
                .onMouseClick( it -> it.animateOnce(2, TimeUnit.SECONDS, state -> {
                    it.paint(state, g -> {
                        g.setColor(new Color(0.1f, 0.25f, 0.5f, (float) state.fadeOut()));
                        for ( int i = 0; i < 5; i++ ) {
                            double r = 300 * state.fadeIn() * ( 1 - i * 0.2 );
                            double x = it.getEvent().getX() - r / 2;
                            double y = it.getEvent().getY() - r / 2;
                            g.drawOval((int) x, (int) y, (int) r, (int) r);
                        }
                    });
                }))
            )
            .add("span, center", label(vm.getFeedback()))
        );
    }
}
