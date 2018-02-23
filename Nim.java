
import objectdraw.*;
import java.awt.*;
import java.util.Random; 

/**
 * Erika Sklaver 
 * Nim 
 * 04/28/15
 *
 * This creates the Nim class. This creates a number of "stones" from 10 to 20, which are filled ovals with their corresponding 
 * number labels. The object of the game is to remove the last stone before the computer does. Helpful text messages
 * are displayed on the screen to indicate how the game is played. Only 1 or 2 stones may be removed at a time. 
 *
 */
public class Nim  {
    
    //constants for the text display, stone size, and stone number display
    private static final int TEXT_X = 50;
    private static final int TEXT_Y = 5; 
    private static final int X = 100; 
    private static final int DIAMETER = 25;
    private static final int STONE_TEXT_XA = 109; 
    private static final int STONE_TEXT_X = 105;
    private static final int SPACING = 5;
    private static final int TWO_DIGITS = 10;
    
    //the initial y location of the first stone
    private int y = 500; 
    
    //the text object that changes according to the user and computer moves
    private Text text;
    
    //the random object that generates a random number of stones to create and how many stones
    //the computer randomly removes
    private Random generator; 
    
    //creates the oval array and the text array
    private FramedOval[] stoneArray; 
    private Text[] stoneNumberArray; 
    
    //the number of stones present
    private int stoneNumber; 
    
    //the number of stones the computer will remove 
    private int computer; 
    
    //remembers whether the user has made a move yet
    private boolean userMoveMade = false; 
    
    
    //the constructor
    public Nim(DrawingCanvas canvas) {
       
        //the text at the beginning of the game
        text = new Text ("Your move! Click on a stone to remove that stone and all above it.", TEXT_X, TEXT_Y, canvas); 
        
        //creates the random object
        generator = new Random(); 
        
        //generates a random number from 10 to 20
        stoneNumber = generator.nextInt(11) + 10;
       
        //creates arrays with the randomly generated amount of spaces
        stoneArray = new FramedOval[stoneNumber];
        stoneNumberArray = new Text[stoneNumber];
        
        
        for (int counter = 0; counter < stoneNumber; counter++){
            //assigns a new framed oval to the stone array until the counter is greater than the randomly generated number
            stoneArray[counter] = new FramedOval (X, y, DIAMETER, DIAMETER, canvas);
           
            //creates the numbers within the ovals
            if (counter + 1 >= TWO_DIGITS){
                //if it is a 2 digit number it will be centered differenly 
                stoneNumberArray[counter] = new Text(counter+1, STONE_TEXT_X, y+SPACING, canvas); 
            }else{
                //a 1 digit number will be centered differently that a 2 digit number
                stoneNumberArray[counter] = new Text(counter+1, STONE_TEXT_XA, y+SPACING, canvas); 
            }
            
            //each time we create a new oval, it needs a new y location 
            y = y - DIAMETER;
            
            
        }
        
    
    }

    // The executeRound method allows for the user to make a move and then the computer to make a move 
    public void executeRound(Location point) {
        
        //the user move
        if (stoneArray[stoneNumber-1].contains(point)){
            //if the highest stone is clicked then it is hidden and the stone number decreases by one 
          
            stoneArray[stoneNumber - 1].hide(); 
            stoneNumberArray[stoneNumber - 1].hide();
            stoneNumber--; 
            
            userMoveMade = true; 
           
       
            if (stoneNumber == 0){
                //if the last stone is clicked then the user wins and the game is over
                 text.setText("You are the winner!"); 
                userMoveMade = false; 
            }
            
        } else if (stoneArray[stoneNumber-2].contains(point)){
            //if the second highest stone is clicked then both that stone and the stone above it are
            //hidden and the stone number decreases by 2
            stoneArray[stoneNumber - 1].hide(); 
            stoneNumberArray[stoneNumber - 1].hide();
            
            stoneArray[stoneNumber - 2].hide(); 
            stoneNumberArray[stoneNumber - 2].hide();
            
            stoneNumber = stoneNumber - 2; 
            
            userMoveMade = true; 
            
            if (stoneNumber == 0){
                //if the there are only 2 stones left and the bottom stone is clicked then the user
                //wins and the game is over 
                 text.setText("You are the winner!"); 
                userMoveMade = false; 
            }
            
            
        }
        else {
            //if the user clicked on a stone below the top 2, the computer will not make a move until the user does
            text.setText("You tried to remove too many stones!"); 
            
            userMoveMade = false; 
        }
        
        //computer move
        
        //generates a random number, either 0 or 1
        computer = generator.nextInt(2); 
        if (userMoveMade){
            //if the user has successfully moved 1 or 2 stones 
        if (computer == 0){
            //if the randomly generated number is 0
            if (stoneNumber == 1){
                //if there is only one stone left then the computer removes the last stone and the user loses
                text.setText("The computer removed 1 stone. You lost!"); 
                stoneArray[stoneNumber - 1].hide(); 
                stoneNumberArray[stoneNumber - 1].hide();
            }
            
            if (stoneNumber >1){
                //the computer will remove one stone from the stack and the stone number decreases by 1
                stoneArray[stoneNumber - 1].hide(); 
                stoneNumberArray[stoneNumber - 1].hide();
                stoneNumber--; 
                text.setText("The computer has removed 1 stone.");
             
            }
            
             
        }else {
            //if the randomly generated number is 1
             
            if (stoneNumber == 2){
                //if there are only 2 stones left then the computer removes the last 2 and the user loses
                text.setText("The computer removed 2 stones. You lost!"); 
                stoneArray[stoneNumber - 1].hide(); 
                stoneNumberArray[stoneNumber - 1].hide();
                stoneArray[stoneNumber - 2].hide();
                stoneNumberArray[stoneNumber - 2].hide();
                stoneNumber = stoneNumber - 2;
            }
            
           
            if (stoneNumber == 1){
                //if there is only 1 stone left, the computer will remove it and the user loses
                text.setText("The computer removed 1 stone. You lost!"); 
                stoneArray[stoneNumber - 1].hide(); 
                stoneNumberArray[stoneNumber - 1].hide();
            }
           
            if (stoneNumber >2){
                //the computer will remove 2 stones from the stack and the stone number decreases by 2 
                stoneArray[stoneNumber - 1].hide(); 
                stoneNumberArray[stoneNumber - 1].hide();
                stoneArray[stoneNumber - 2].hide();
                stoneNumberArray[stoneNumber - 2].hide();
                stoneNumber = stoneNumber - 2; 
                text.setText("The computer has removed 2 stones."); 
            }
            
            
        }
       }
    

    }
    
    // The contains method checks to see if any of the ovals contains the point     
    public boolean contains(Location point) {

        //checks each oval in the array 
        for (int stoneIndex = 0; stoneIndex < stoneNumber; stoneIndex++){
            //starts off with the index at 0 and increases until the index is greater than the number of stones
            if (stoneArray[stoneIndex].contains(point)){
                //if any of the stones contains the point then this is true
                return true;
            }
        }
        //if none of the stones contains the point then this is false
        return false;

    }
    
}
