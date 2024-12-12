package ooplab02;

import java.util.Random;
import java.util.Scanner;

// Character and Subclasses
// Character class defines character type, moves, and special effects like double damage

public class Character {
    
    private String type;
    private Move[] moves;
    
    private boolean doubleDamage = false;
    private boolean skipCoinFlip = false;
    private boolean randomTypeChange = false;

    // Constructor allowing type and move customization
    public Character(String type, Move[] moves) {
        this.type = type;
        this.moves = moves;
    }

    // Default constructor with Normal type moves
    public Character() {
        this.type = "Normal";
        this.moves = new Move[] {
            new Move("Tackle", 10, "Normal"),
            new Move("Punch", 20, "Normal"),
            new Move("Slam", 15, "Normal")
        };
    }

    public String getType() {
        return type;
    }
    
    public boolean isRandomTypeChange() {
        return randomTypeChange;
    }
    
    public void setRandomTypeChange(boolean randomTypeChange) {
        this.randomTypeChange = randomTypeChange;
    }
    
    // Changes character type to a random one for Random Type Change effect
    public void changeToRandomType() {
        String[] types = {"Fire", "Water", "Grass", "Normal"};
        this.type = types[new Random().nextInt(types.length)];
    }

    // Setter and getter for double damage effect
    public void setDoubleDamage(boolean doubleDamage) {
        this.doubleDamage = doubleDamage;
    }

    public boolean isDoubleDamage() {
        return doubleDamage;
    }

    // Set and check for skip coin flip status
    public void setSkipCoinFlip(boolean skipCoinFlip) {
        this.skipCoinFlip = skipCoinFlip;
    }

    public boolean isSkipCoinFlip() {
        return skipCoinFlip;
    }

    // Attack method for inflicting damage with type-based advantage system
    public void attack(Player opponent, Scanner input) {
        System.out.println("Choose a move to attack:");
        for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + ". " + moves[i].getName() + " (" + moves[i].getDamage() + " damage, " + moves[i].getType() + " type)");
        }

        int moveChoice = input.nextInt() - 1;
        Move chosenMove = moves[moveChoice];

        // Calculate damage with type advantage and double damage
        int damage = chosenMove.getDamage();
        if (doubleDamage) {
            damage *= 2;
            doubleDamage = false; // Reset double damage
            System.out.println("Double Damage activated! Damage is doubled to " + damage);
        }

        if (hasTypeAdvantage(chosenMove.getType(), opponent.getCharacter().getType())) {
            damage *= 1.5;
            System.out.println("Type advantage! Damage is increased by 50% to " + damage);
        }
        
        if (hasTypeDisadvantage(chosenMove.getType(), opponent.getCharacter().getType())) {
            damage *= 0.5;
            System.out.println("Type Disadvantage! Damage is decreased by 50% to " + damage);
        }
        
        opponent.reduceHealth(damage);
        System.out.println("Dealt " + damage + " damage to " + opponent.getName() + "!");
    }

    // Type advantage logic: determines if moveType has advantage over opponentType
    private boolean hasTypeAdvantage(String moveType, String opponentType) {
        return (moveType.equals("Fire") && opponentType.equals("Grass")) ||
               (moveType.equals("Water") && opponentType.equals("Fire")) ||
               (moveType.equals("Grass") && opponentType.equals("Water"));
    }
        
        private boolean hasTypeDisadvantage(String moveType, String opponentType) {
        return (moveType.equals("Grass") && opponentType.equals("Fire")) ||
               (moveType.equals("Fire") && opponentType.equals("Water")) ||
               (moveType.equals("Water") && opponentType.equals("Grass"));
    }
}

// FireCharacter, WaterCharacter, GrassCharacter and NormalCharacter subclasses with unique moves
class FireCharacter extends Character {
    public FireCharacter() {
        super("Fire", new Move[] {
            new Move("Flame Thrower", 20, "Fire"),
            new Move("Burn", 15, "Fire"),
            new Move("Ember", 10, "Fire")
        });
    }
}

class WaterCharacter extends Character {
    public WaterCharacter() {
        super("Water", new Move[] {
            new Move("Water Gun", 20, "Water"),
            new Move("Splash", 15, "Water"),
            new Move("Aqua Tail", 10, "Water")
        });
    }
}

class GrassCharacter extends Character {
    public GrassCharacter() {
        super("Grass", new Move[] {
            new Move("Vine Whip", 20, "Grass"),
            new Move("Razor Leaf", 15, "Grass"),
            new Move("Seed Bomb", 10, "Grass")
        });
    }
}

class NormalCharacter extends Character {
    public NormalCharacter() {
        super("Normal", new Move[] {
            new Move("Tackle", 10, "Normal"),
            new Move("Punch", 20, "Normal"),
            new Move("Slam", 15, "Normal")
        });
    }
}
    

