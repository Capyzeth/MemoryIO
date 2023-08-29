package memoryio;
import swingtree.UI;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import static swingtree.UI.*;

public class GameView extends Panel {
    private static final int TILE_SPACING = 5;

    public GameView(GameViewModel vm){
        of(this).withLayout("fill, wrap 1")
        .withPrefSize(((90 + TILE_SPACING) * vm.getWidth() + 120), ((90 + TILE_SPACING) * vm.getHeight() + 140))
        .withBackground(Color.LIGHT_GRAY)
        .add("center",
            panel("fill")
            .withStyle(it -> it
                .borderRadius(10)
                .border(0,0,2,0, Color.GRAY)
                .padding(0,7,0,7)
            )
            .add(
                html(vm.getScore().viewAsString( s -> "<p>Your Score: &nbsp<b style=\"font-size:16\">" + s + "/" + vm.getMaxScore() + "</b></p>" ))
                .withStyle(it -> it.margin(0,10,0,0))
            )
            .add(
                html(vm.getTries().viewAsString( s -> "<p>Number of Tries: &nbsp<b style=\"font-size:16\">" + s + "</b></p>" ))
            )
        )
        .add("center",
            panel("center, wrap " + vm.getWidth() + ", gap 0, ins 0")
            .withRepaintIf(vm.getMadeMistake())
            .withStyle(it -> it
                .backgroundColor(vm.getMadeMistake().is(true) ? Color.RED : new Color(0,150,90) )
                .padding(3)
                .border(2, "black")
                .borderRadius(12)
                .margin(10,10,80,10)
                .shadow("dark", s -> s
                    .color(new Color(0, 0.1f, 0.2f, 0.70f))
                    .offset(+3)
                    .blurRadius(6)
                    .spreadRadius(0)
                )
                .shadow("bright", s -> s
                    .color(new Color(1.0f, 1.0f, 1.0f, 0.70f))
                    .offset(-3)
                    .blurRadius(6)
                    .spreadRadius(0)
                )
            )
            .apply(p -> {
                var selectedCard = new Random(1 + vm.getTileViewModels().size()).nextInt(10)+1;
                var cover = UI.findIcon("/cards/c" + selectedCard + ".png").orElseThrow();
                List<TileViewModel> tiles = vm.getTileViewModels();
                for(int i = 0; i < tiles.size(); i++){
                    int finalI = i;
                    var tileViewModel = tiles.get(i);
                    p.add(
                        label(90,90,UI.findIcon(tileViewModel.getSource()).orElseThrow())
                        .withRepaintIf(tileViewModel.getRepaintEvent())
                        .onMousePress(it -> vm.clickedTile(finalI))
                        .withStyle(it -> it
                            .painter(Layer.FOREGROUND, g -> {
                                boolean isVisible = tiles.get(finalI).getIsVisible();
                                if(!isVisible){
                                    g.drawImage(cover.getImage(),0,0, it.component().getWidth(), it.component().getHeight(),null);
                                }
                            })
                            .margin(TILE_SPACING)
                            .shadow("dark", s -> s
                                .color(new Color(0, 0.1f, 0.2f, 0.70f))
                                .offset(+1)
                                .blurRadius(2)
                                .spreadRadius(0)
                            )
                            .shadow("bright", s -> s
                                .color(new Color(1.0f, 1.0f, 1.0f, 0.70f))
                                .offset(-1)
                                .blurRadius(2)
                                .spreadRadius(0)
                            )
                        )
                    );
                }
            })
        );
    }
}
