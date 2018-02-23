
import objectdraw.*;
import java.awt.*;

/**
 * This program creates a new game of Nim every time the mouse enters the wondow, 
 * i.e. when the window first appears it is blank. When the mouse exits the window, 
 * the canvas is cleared and, upon reentry, another game appears.  Note that the  
 * program checks, when the user presses the mouse, to see if they pressed on any  
 * of the stones, in which case the executeRound method is called to execute a user  
 * move followed by a computer move.
 */
public class Events extends FrameWindowController {

    private static final int WINDOW_SIZE = 600;
    
    private Nim nimGame;
    
    public void begin() {
       resize(WINDOW_SIZE, WINDOW_SIZE);
    }

    // if the user pressed on a stone in the Nim game, execute 
    // a round of moves
    public void onMousePress(Location point) {
        if (nimGame.contains(point)) {
            nimGame.executeRound(point);
        }
    }

    // get rid of the current game on exit
    public void onMouseExit(Location point) {
        canvas.clear();
    }

    // create a new Nim game on entry
    public void onMouseEnter(Location point) {
        nimGame = new Nim(canvas);
    }

}
