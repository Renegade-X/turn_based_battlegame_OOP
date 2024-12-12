package ooplab02;
import java.util.Random;
import java.util.Scanner;

public class CombatManager {
    
    private Player player1;
    private Player player2;
    private Scanner input;
    private int round;

    public CombatManager(Player player1, Player player2, Scanner input) {
        this.player1 = player1;
        this.player2 = player2;
        this.input = input;
        this.round = 1;
    }

    public void startGame() {
        System.out.println("Starting the battle!");
        
        while (player1.getHealth() > 0 && player2.getHealth() > 0) {
            System.out.println("\n--- Round " + round + " ---");

            // Coin flip to determine turn order
            Player firstPlayer = determineTurnOrder(player1, player2);
            Player secondPlayer = (firstPlayer == player1) ? player2 : player1;
            
            System.out.println(firstPlayer.getName() + " goes first!");

            // Each player takes their turn
            if (firstPlayer.takeTurn(secondPlayer, input)) break; // End if a player wins
            if (secondPlayer.takeTurn(firstPlayer, input)) break; // End if a player wins
            
            // Display health of both players at the end of each round
            System.out.println("\nEnd of Round " + round + " - Health Status:");
            System.out.println(player1.getName() + " health: " + player1.getHealth());
            System.out.println(player2.getName() + " health: " + player2.getHealth());

            round++;
        }
    }

    // Determine turn order with an interactive coin flip
    private Player determineTurnOrder(Player player1, Player player2) {
        System.out.println("Coin Flip! " + player1.getName() + ", call 'heads' or 'tails':");
        String player1Call = input.next().toLowerCase();
        
        // Error Handling: Validate input
        while (!player1Call.equals("heads") && !player1Call.equals("tails")) {
            System.out.println("Invalid choice! Please enter 'heads' or 'tails':");
            player1Call = input.next().toLowerCase();
        }

        boolean flipResult = new Random().nextBoolean(); //Built-In Method for Random Class
        String flipOutcome = flipResult ? "heads" : "tails";
        System.out.println("The coin landed on " + flipOutcome + "!");
        
        return player1Call.equals(flipOutcome) ? player1 : player2;
    }
}
