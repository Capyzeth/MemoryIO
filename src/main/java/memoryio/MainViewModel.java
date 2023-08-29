package memoryio;

import sprouts.Var;
import swingtree.UI;

import javax.swing.*;
import java.util.Optional;

public class MainViewModel {
    private static final int MAX_NUMBER_OF_TILES = 24*2; //todo: read contents of the image folder and calculate accordingly
    public static final int MIN_DIM_SIZE = 1;

    private final Var<Integer> width = Var.of(4).onAct(it -> validate());
    private final Var<Integer> height = Var.of(4).onAct(it -> validate());
    private final Var<String> feedback = Var.of("");
    private boolean isPlayable = true;

    public Var<Integer> getWidth() {    return width;    }
    public Var<Integer> getHeight() {   return height;   }
    public Var<String> getFeedback() {  return feedback; }

    private void validate(){
        isPlayable = true;
        int numberOfTiles = Math.max(0, width.get()) * Math.max(0, height.get());
        boolean isEven = (numberOfTiles % 2 == 0);
        String problems = "";
        if(!isEven){
            problems += "The number of tiles must be even!";
            isPlayable = false;
        }
        if (numberOfTiles > MAX_NUMBER_OF_TILES){
            problems += "<br>Upper limit of available tiles reached!";
            isPlayable = false;
        }
        if (( width.get() < MIN_DIM_SIZE ) || ( height.get() < MIN_DIM_SIZE )){
            problems += "<br>Not possible in this universe, sorry. (Negative dimensions for the board aren't valid.)";
            isPlayable = false;
        }
        feedback.set("<html><p style=\"color:red\">" + problems + "</p></html>");
    }

    public Optional<GameViewModel> startGame(){
        if(this.isPlayable){
            return Optional.of(new GameViewModel(width.get(), height.get()));
        }else{
            this.feedback.set("Requirements for start aren't met.");
            return Optional.empty();
        }
    }
}
