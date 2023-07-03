package memoryio;

import sprouts.Var;
import swingtree.UI;

public class MainViewModel {
    private static final int MAX_NUMBER_OF_TILES = 24*2; //todo: read contents of the image folder and calculate accordingly

    private final Var<Integer> width = Var.of(4).onAct(it -> validate());
    private final Var<Integer> height = Var.of(4).onAct(it -> validate());
    private final Var<String> feedback = Var.of("");
    private boolean isPlayable = true;

    public Var<Integer> getWidth() {    return width;    }
    public Var<Integer> getHeight() {   return height;   }
    public Var<String> getFeedback() {  return feedback; }

    private void validate(){
        isPlayable = true;
        int numberOfTiles = width.get() * height.get();
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
        feedback.set("<html><p style=\"color:red\">" + problems + "</p></html>");
    }

    public void startGame(){
        if(this.isPlayable){
            UI.show(new GameView(new GameViewModel(width.get() * height.get())));
        }else{
            this.feedback.set("Requirements for start aren't met.");
        }
    }
}
