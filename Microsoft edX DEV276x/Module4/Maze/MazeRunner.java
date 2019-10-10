package Module4.Maze;

import java.util.Scanner;
import java.util.Locale;

/*Main class of the game "Maze Runner". Creates map, and provides player interface.
*/
public class MazeRunner {
    Maze myMap;
    Scanner input;
    int movesLeft;

    public MazeRunner (){
        myMap = new Maze();
        input = new Scanner (System.in);
        input.useLocale(Locale.US);
        movesLeft=100;
    }

    public static void main(String[] args){
        MazeRunner game = new MazeRunner();

        game.intro();

        while (game.myMap.didIWin()==false && game.movesLeft>0){
            game.userMove();
            game.movesMessages();
        }

        if(game.myMap.didIWin()){
            System.out.println("Yoy made it out of the maze, and you did it in " + (100-game.movesLeft) +" moves");
        }
    }

    //First printing of map
    public void intro(){
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");
        this.myMap.printMap();
    }

    //Ask player in which direction he/she wants to move, and check if move is possible
    public void userMove(){
        boolean isMoveComplete = false;

        System.out.println("Where would you like to move? (R, L, U, D)");
        String direction=this.input.nextLine();

        while (direction.equalsIgnoreCase("R")==false&&direction.equalsIgnoreCase("L")==false&&
                direction.equalsIgnoreCase("U")==false&&direction.equalsIgnoreCase("D")==false){
            System.out.println("Please enter single letter: R, L, U or D");
            direction=this.input.nextLine();
        }

        //Check if you can move in given direction
        direction=direction.toUpperCase();
        char dir = direction.charAt(0);

        if(this.myMap.isThereAPit(direction)){
            this.navigatePit(direction);
            isMoveComplete=true;
        }
        else {
            switch (dir) {
                case 'R': {
                    if (this.myMap.canIMoveRight()) {
                        this.myMap.moveRight();
                        isMoveComplete = true;
                    }
                    break;
                }
                case 'L': {
                    if (this.myMap.canIMoveLeft()) {
                        this.myMap.moveLeft();
                        isMoveComplete = true;
                    }
                    break;
                }
                case 'U': {
                    if (this.myMap.canIMoveUp()) {
                        this.myMap.moveUp();
                        isMoveComplete = true;
                    }
                    break;
                }
                case 'D': {
                    if (this.myMap.canIMoveDown()) {
                        this.myMap.moveDown();
                        isMoveComplete = true;
                    }
                    break;
                }
            }
        }


        if(isMoveComplete){
            this.myMap.printMap();
            this.movesLeft--;
            if(this.myMap.didIWin()){
                this.movesMessages();
            }
        }
        else {
            System.out.println("Sorry, you've had hit a wall.");
        }


    }

    public void movesMessages(){
        switch (this.movesLeft){
            case 50:{
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
                break;
            }
            case 25:{
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
                break;
            }
            case 10:{
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                break;
            }
            case 0:{
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                break;
            }
        }
    }
    private void navigatePit(String direction){
        System.out.println("Watch out! There's a pit ahead, jump it? Y for yes");
        String answer = this.input.nextLine();
        if(answer.equalsIgnoreCase("Y")){
            this.myMap.jumpOverPit(direction);
        }
    }
}
