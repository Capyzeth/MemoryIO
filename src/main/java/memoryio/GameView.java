package memoryio;
import swingtree.UI;
import swingtree.style.Layer;

import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import static swingtree.UI.*;

public class GameView extends Panel {

    public GameView(GameViewModel vm){
        of(this).withLayout("fill, wrap 1").withPrefSize(1000,1000)
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
                List<TileViewModel> images = vm.getTileViewModels();
                for(int i = 0; i < images.size(); i++){
                    int finalI = i;
                    var tileViewModel = images.get(i);
                    p.add(
                        label(UI.icon(tileViewModel.getSource())).withRepaintIf(tileViewModel.getRepaintEvent())
                        .onMouseClick(it -> vm.clickedTile(finalI))
                        .withStyle(it -> it.painter(Layer.FOREGROUND, g -> {
                            boolean isVisible = images.get(finalI).getIsVisible();
                            if(!isVisible){
                                g.setColor(Color.darkGray);
                                g.fillRoundRect(0,0,it.component().getWidth(),it.component().getHeight(),20,20);
                            }
                        }))
                    );
                }
            })
        );
    }
}
