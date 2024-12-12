package ooplab02;

import java.util.ArrayList;
import java.util.Scanner;

// Player Class - Manages player details, health, items, and turn actions

public class Player {
    
    private String name;
    private Character character;
    private int health = 100; // Initial player health
    private int totalItemsUsed = 0; // Tracks number of items used by player
    private ArrayList <Item> items; // List of items available to player

    // Constructor initializes player name, character, and item list
    public Player(String name, Character character) {
        this.name = name;
        this.character = character;
        this.items = new ArrayList<>();
        items.add(new Item("Healing"));
        items.add(new Item("Double Damage"));
        items.add(new Item("Skip Coin Flip"));
        items.add(new Item("Random Type Change"));
    }
    
    public static Character chooseCharacter(int choice) {
        switch (choice) {
            case 1: return new FireCharacter();
            case 2: return new WaterCharacter();
            case 3: return new GrassCharacter();
            case 4: return new NormalCharacter();
            default: throw new IllegalArgumentException("Invalid choice!");
        }
    }

    public String getName() {
        return name;
    }

    public Character getCharacter() {
        return character;
    }

    public int getHealth() {
        return health;
    }

    // Reduce health by a specified damage amount, ensuring health doesn't go below 0
    public void reduceHealth(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    // Handles a player's turn, allowing them to choose an action
    public boolean takeTurn(Player opponent, Scanner input) {
        // Change type at the beginning of the turn if Random Type Change is active
        if (character.isRandomTypeChange()) {
            character.changeToRandomType();
            System.out.println(name + "'s character type has changed to: " + character.getType());
            character.setRandomTypeChange(false); // Reset flag
        }

        System.out.println("\n" + name + "'s turn!");

        // Display action options to the player
        System.out.println("Choose an action: (1) Attack (2) Use Item (3) Run");
        int actionChoice = input.nextInt();

        // Perform action based on player choice
        switch (actionChoice) {
            case 1: // Attack opponent
                character.attack(opponent, input);
                break;
            case 2: // Use item
                useItem(opponent, input);
                break;
            case 3: // Resign (player loses)
                System.out.println(name + " has chosen to run. " + opponent.getName() + " wins!");
                return true; 
            default:
                System.out.println("Invalid choice! Turn forfeited.");
                break;
        }

        // Check if opponent has been defeated
        if (opponent.getHealth() <= 0) {
            System.out.println(name + " wins!");
            return true;
        }
        return false;
    }

    // Allows player to use an item, with a limit of 3 uses per game
    public void useItem(Player opponent, Scanner input) {
        if (totalItemsUsed >= 3) {
            System.out.println(name + " has already used the maximum of 3 items.");
            return;
        }

        // Display available items for selection
        System.out.println("Choose an item to use:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }

        int itemChoice = input.nextInt() - 1;
        Item chosenItem = items.get(itemChoice);

        // Use item based on item type
        
        switch (chosenItem.getName()) {
            case "Healing": // Healing
                health += 20;
                if (health > 100) health = 100; 
                System.out.println(name + " used a Healing item and gained 20 health.");
                break;
                
            case "Double Damage": // Double Damage
                System.out.println(name + " will deal double damage on their next move!");
                character.setDoubleDamage(true);
                break;
                
            case "Skip Coin Flip": // Skip Coin Flip
                System.out.println(name + " will skip the coin flip and go first next round!");
                character.setSkipCoinFlip(true);
                break;
                
            case "Random Type Change": // Random Type Change
                character.setRandomTypeChange(true);
                System.out.println(name + " will change their character type on their next turn.");
                break;
                
            default:
                System.out.println("Invalid item choice.");
                return;
        }

        // Increment total item usage count
        totalItemsUsed++;
        System.out.println(name + " has " + (3 - totalItemsUsed) + " item(s) left.");
    }
}


    

