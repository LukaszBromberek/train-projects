package Module2.oddsOrEven;
import java.util.Random;
import java.util.Scanner;
import java.util.Locale;

public class OddsAndEvens {
    String name;
    boolean playerPlaysEven;
    String playerSideName;
    String computerSideName;
    int bet;
    boolean win;
    int computerBet;
    int betsSum;

    public int getBetsSum() {
        return betsSum;
    }

    public void setBetsSum(int betsSum) {
        this.betsSum = betsSum;
    }

    public int getComputerBet() {
        return computerBet;
    }

    public void setComputerBet(int computerBet) {
        this.computerBet = computerBet;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlayerPlaysEven() {
        return playerPlaysEven;
    }

    public void setPlayerPlaysEven(boolean playerPlaysEven) {
        this.playerPlaysEven = playerPlaysEven;
    }

    public String getPlayerSideName() {
        return playerSideName;
    }

    public void setPlayerSideName(String playerSideName) {
        this.playerSideName = playerSideName;
    }

    public String getComputerSideName() {
        return computerSideName;
    }

    public void setComputerSideName(String computerSideName) {
        this.computerSideName = computerSideName;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public static void main (String[] args){
        //Initialize global objects
        Scanner input = new Scanner (System.in);
        input.useLocale(Locale.US);
        OddsAndEvens player = new OddsAndEvens();
        Random rand = new Random();

        //Greetings and getting players name
        System.out.println("Let’s play a game called “Odds and Evens”");
        System.out.print("What is your name? ");
        player.name = input.nextLine();

        //Choosing side
        System.out.print("Hi " + player.getName() + ", Which do you choose? (O)dds or (E)vens ");
        String oddOrEven = input.nextLine();

        while (!oddOrEven.equals("O")&&!oddOrEven.equals("E")){
            System.out.print("Please enter 'O' or 'E' ");
            oddOrEven = input.nextLine();
        }
        if (oddOrEven.equals("E")) {
            player.setPlayerSideName("even");
            player.setComputerSideName("odd");
            player.setPlayerPlaysEven(true);
        }
        else {
            player.setPlayerSideName("odd");
            player.setComputerSideName("even");
            player.setPlayerPlaysEven(false);
        }

        System.out.println(player.getName() + " has picked " + player.getPlayerSideName() + "s! The computer will be " + player.getComputerSideName() + "s.");
        System.out.println("-------------------\n");

        //Get players bet
        System.out.print("How many \"fingers\" do you put out? ");
        player.setBet(input.nextInt());
        while(player.getBet()<0 && player.getBet()>6){
            System.out.print("Please insert number from 1 to 5. ");
            player.setBet(input.nextInt());
        }

        //Set computer rand and compute win/lose
        player.setComputerBet(rand.nextInt(5)+1);
        player.setBetsSum(player.getBet()+player.getComputerBet());

        if(player.getBetsSum()%2==0 && player.getPlayerSideName().equals("even")){
            player.setWin(true);
        } else if (player.getBetsSum()%2==1 && player.getPlayerSideName().equals("odd")) {
            player.setWin(true);
        } else {
            player.setWin(false);
        }

        //Announce result
        System.out.println("\n\n" + player.getBet() + " + " + player.getComputerBet() + " = " + player.getBetsSum());
        System.out.print(player.getBetsSum() + " is ...");

        if (player.getBetsSum()%2==0) { System.out.println("even");}
        else { System.out.println("odd");}

        System.out.println("This means " + player.getName() + " ");

        if (player.isWin()) { System.out.println("wins!");}
        else { System.out.println("loses!");}
        System.out.println("-------------------");
    }
}
