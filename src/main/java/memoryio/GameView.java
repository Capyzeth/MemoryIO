package memoryio;
import swingtree.UI;
import swingtree.style.Layer;

import java.awt.Color;
import java.util.List;

import static swingtree.UI.*;

public class GameView extends Panel {

    public GameView(GameViewModel vm){
        of(this).withLayout("fill, wrap 1").withPrefSize((90 * vm.getWidth() + 120),(90 * vm.getHeight() + 120))
        .add(
            panel("fill")
            .add(label("MemoryIO"))
            .add(label("Level"))
            .add(label("Score"))
            .add(label("Tries"))
        )
        .add("center",
            panel("center, wrap " + vm.getWidth())
            .withRepaintIf(vm.getMadeMistake())
            .withStyle(it -> it
                .backgroundColor(vm.getMadeMistake().is(true) ? Color.RED : Color.GREEN )
                .border(3, "black")
                .borderRadius(12)
                .margin(0,0,50,0)
            )
            .apply(p -> {
                List<TileViewModel> tiles = vm.getTileViewModels();
                for(int i = 0; i < tiles.size(); i++){
                    int finalI = i;
                    var tileViewModel = tiles.get(i);
                    p.add(
                        label(90,90,UI.icon(tileViewModel.getSource())).withRepaintIf(tileViewModel.getRepaintEvent())
                        .onMouseClick(it -> vm.clickedTile(finalI))
                        .withStyle(it -> it
                            .painter(Layer.FOREGROUND, g -> {
                                boolean isVisible = tiles.get(finalI).getIsVisible();
                                if(!isVisible){
                                    g.setColor(Color.darkGray);
                                    g.fillRoundRect(0,0,it.component().getWidth(),it.component().getHeight(),20,20);
                                }
                            })
                        )
                    );
                }
            })
        );
    }
}
