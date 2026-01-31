package upei.project;

/*
 * This is a basic card parent class for all cards in the game.
 * It provides a common property - name, for all cards.
 */

public abstract class Card {

    // The name of the card
    private String name;

    // Constructor for the Card class
    // Initializes the card with a name
    public Card(String name){
        this.name = name;
    }

    // Method to get the name of the card
    // Returns the name of the card as a String
    String getName(){
        return name;
    }
}