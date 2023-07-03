package memoryio;

import java.awt.image.BufferedImage;
import java.util.*;

public class GameViewModel {
    private static final int SEED = new Random().nextInt(100);

    private final int numberOfTiles;
    private final List<TileViewModel> tileViewModels = new ArrayList<>();
    private final List<Integer> visibleImages = new ArrayList<>();

    private TileViewModel first = null;
    private TileViewModel second = null;

    public GameViewModel(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;

        for( int i = 0; i < numberOfTiles / 2; i++ ){
            var filename = "/platypussies/p" + (i+1) + ".png";
            tileViewModels.add(new TileViewModel(i * 2, filename));
            tileViewModels.add(new TileViewModel(i * 2 + 1, filename));
        }
        Collections.shuffle(tileViewModels, new Random(SEED));
    }

    public List<TileViewModel> getTileViewModels(){
        return this.tileViewModels;
    }

    public void clickedTile(int i){
        if(first == null){
            first = tileViewModels.get(i);
            first.setVisible(true);
            if(first.getIsSolved()){
                first = null;
                return;
            }
        }else if(second == null){
            second = tileViewModels.get(i);
            second.setVisible(true);
            if(second.getIsSolved()){
                second = null;
                return;
            }
            if(Objects.equals(first.getSource(), second.getSource())){
                first.setSolved(true);
                second.setSolved(true);
                first = null;
                second = null;
            }else{
                first.setVisible(false);
                second.setVisible(false);
            }
        }else{

        }
    }
}
