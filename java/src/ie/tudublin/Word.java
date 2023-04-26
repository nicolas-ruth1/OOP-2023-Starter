package ie.tudublin;
import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows;

    public Word(String word) {
        this.word = word;
        this.follows = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Follow> getFollows() {
        return follows;
    }

    public void addFollow(String word) {
        for (Follow follow : follows) {
            if (follow.getWord().equals(word)) {
                follow.incrementCount();
                return;
            }
        }
        follows.add(new Follow(word, 1));
    }

    @Override
    public String toString() {
        String str = word + ": ";
        for (Follow follow : follows) {
            str += follow.toString() + " ";
        }
        return str;
    }

    public Follow findFollow(String word)
{
    for(Follow f : follows)
    {
        if(f.getWord().equals(word))
        {
            return f;
        }
    }
    return null;
}
}
