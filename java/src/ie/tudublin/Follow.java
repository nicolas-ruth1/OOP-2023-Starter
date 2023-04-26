package ie.tudublin;

// class that holds a word and its count 
public class Follow {
    private String word; // the word 
    private int count; // how many times it appears

    // constructs object with given word and count
    public Follow(String word, int count) {
        this.word = word;
        this.count = count;
    }

    // returns word 
    public String getWord() {
        return word;
    }

    // returns amount of times it appears
    public int getCount() {
        return count;
    }

    // sets the next word 
    public void Word(String word) {
        this.word = word;
    }

    // sets the count of the word 
    public void CountWord(int count) {
        this.count = count;
    }

    // increases the count by 1 
    public void incrementCount() {
        this.count++;
    }


}