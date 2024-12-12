package ooplab02;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Turn-Based Battle Simulator!");

        // Set up Player 1
        System.out.println("Player 1, choose your character type: (1) Fire (2) Water (3) Grass (4) Normal");
        Character player1Character = Player.chooseCharacter(input.nextInt());
        Player player1 = new Player("Player 1", player1Character);

        // Set up Player 2
        System.out.println("Player 2, choose your character type: (1) Fire (2) Water (3) Grass (4) Normal");
        Character player2Character = Player.chooseCharacter(input.nextInt());
        Player player2 = new Player("Player 2", player2Character);

        // Create CombatManager and start the game
        CombatManager combatManager = new CombatManager(player1, player2, input);
        combatManager.startGame();

        input.close();
    }
}
