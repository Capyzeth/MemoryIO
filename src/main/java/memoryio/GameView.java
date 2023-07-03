package memoryio;
import swingtree.style.Layer;

import java.awt.Color;

import static swingtree.UI.*;

public class GameView extends Panel {

    public GameView(){
        of(this).withLayout("fill, wrap 1").withPrefSize(500,500)
        .add(
            panel("fill")
            .add(label("MemoryIO"))
            .add(label("Level"))
            .add(label("Score"))
            .add(label("Tries"))
        )
        .add("grow",
            panel("fill, wrap 3") //" + width + "
            .withStyle(it -> it
                .backgroundColor(Color.green)
                .border(3, "black")
                .borderRadius(12)
                .painter(Layer.CONTENT, g2d -> {
                    g2d.setColor(Color.black);
                    g2d.fillRect(20,20,50,50);
                })
            )
            .apply(p -> {
                for(int i = 0; i < 5; i++){
                    p.add(label("index: "+i));
                }
            })
        );
    }
}
