package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word; // private field that stores the word as a string
    private ArrayList<Follow> follows; // private field that stores the follow words as an ArrayList of Follow objects

    // constructor for the Word class that takes a string as input
    public Word(String word) {
        this.word = word;
        follows = new ArrayList<>();
    }

    // method that returns the word as a string
    public String getWord() {
        return word;
    }

    //method that sets the word to a given string
    public void SetWord(String word) {
        this.word = word;
    }

    // method that returns the follow words as an ArrayList of Follow objects
    public ArrayList<Follow> getFollows() {
        return follows;
    }

    // method that adds a follow word to the follows ArrayList
    public void addFollow(Follow follow) {
        follows.add(follow);
    }

    // method that finds and returns the Follow object associated with a given follow word string
    public Follow findFollow(String str) {
        for (Follow follow : follows) {
            if (follow.getWord().equals(str)) { // if the current follow word matches the given string
                return follow; // return follow object
            }
        }
        return null; // return null if the given string is not found
    }

 
}