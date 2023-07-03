package memoryio;

import sprouts.Event;

public class TileViewModel {

    private final Event repaintEvent = Event.create();
    private final int id;
    private final String source;

    boolean isSolved = false;
    boolean isVisible = false;

    public TileViewModel(int id, String source) {
        this.id = id;
        this.source = source;
    }

    public int getId() {
        return id;
    }
    public String getSource() {
        return source;
    }
    public boolean getIsSolved(){
        return isSolved;
    }
    public boolean getIsVisible(){
        return isVisible;
    }
    public Event getRepaintEvent(){
        return repaintEvent;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
        if(isSolved){
            isVisible = true;
            repaintEvent.fire();
        }
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
        repaintEvent.fire();
    }
}
