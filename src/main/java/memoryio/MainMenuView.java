package memoryio;
import sprouts.Var;
import swingtree.UI;

import javax.swing.*;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

import static swingtree.UI.*;

public class MainMenuView extends Panel{
    public MainMenuView(MainMenuViewModel vm){
        of(this).withLayout("fill").add(
            panel("fill, wrap 2")
            .withPrefSize(300,220)
            .withBackground(Color.WHITE)
            .add( "top",
                panel("wrap 1")
                .withStyle(it -> it
                    .border(2,"black")
                    .padding(12)
                    .margin(12)
                    .borderRadius(16)
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
                .add(
                    label("Welcome to MemoryIO")
                    .withForeground(new Color(55, 20, 180))
                    .withFontSize(14)
                    .withTooltip("<html>MemoryIO is a memory game that utilizes AI generated pictures of platypi as" +
                            " images. <br>Please select your desired game size below. <br> The achievable highscore" +
                            " is dependant on the size of the game and is calculated accordingly.</html>")
                )
                .add( "grow",
                    panel("wrap 2, fill", "[][grow]")
                    .add(label("Board width:"))
                    .add("pushx, grow",
                        spinner(vm.getWidth())
                        .withStyle(it -> it
                            .margin(0,0,0,25)
                            .padding(2,2,2,2)
                        )
                    )
                    .add(label("Board height:"))
                    .add("pushx, grow",
                        spinner(vm.getHeight())
                        .withStyle(it -> it
                            .margin(0,0,0,25)
                            .padding(2,2,2,2)
                        )
                    )
                )
            )
            .add(
                button("Play")
                .withStyle(it -> it
                    .border(1, Color.BLACK)
                    .padding(6)
                    .margin(3)
                    .borderRadius(16)
                    .backgroundColor(determineBackgroundColorFor(it.component()))
                    .fontColor(it.component().getModel().isRollover() ? Color.BLACK : Color.WHITE)
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
                .onClick(it -> vm.startGame().ifPresent(game -> {
                    UI.show(f-> {
                        f.setTitle("Memory IO - " + game.getWidth() + "x" + game.getHeight());
                        f.setIconImage(UI.findIcon("/platypussies/p1.png").map(i -> i.getImage()).orElse(null));
                        return new GameView(game);
                    });
                }))
                .onMouseClick( it -> it.animateFor(2, TimeUnit.SECONDS, state -> {
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

    private Color determineBackgroundColorFor(JButton component) {
        ButtonModel model = component.getModel();
        var referenceColor = new Color(0,150,90);
        if(model.isRollover()){
            return Color.YELLOW;
        }

        return referenceColor;
    }
}
