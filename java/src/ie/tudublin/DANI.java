package ie.tudublin;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;

public class DANI extends PApplet {
    ArrayList<Word> words = new ArrayList<Word>();

    public void settings() {
        size(1000, 1000);
    }

    public void setup() {
        colorMode(HSB);
        loadFile("small.txt");
        printWords();
		printSonnet(); // call the method to print the sonnet
    }

    public void draw() {
        background(0);
        fill(255);
        noStroke();
        textSize(20);
        textAlign(CENTER, CENTER);
		printSonnet();
    }

    private void loadFile(String filename) {
        String[] lines = loadStrings(filename);
        for (String line : lines) {
            String[] tokens = splitTokens(line, " ");
            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i].toLowerCase().replaceAll("[^\\w\\s]", "");
                if (token.length() == 0) {
                    continue;
                }
                Word word = findWord(token);
                if (word == null) {
                    word = new Word(token);
                    words.add(word);
                }
                if (i < tokens.length - 1) {
                    String nextToken = tokens[i + 1].toLowerCase().replaceAll("[^\\w\\s]", "");
                    if (nextToken.length() > 0) {
                        Follow follow = word.findFollow(nextToken);
                        if (follow == null) {
                            follow = new Follow(nextToken);
                            word.getFollows().add(follow);
                        }
                        follow.setCount(follow.getCount() + 1);
                    }
                }
            }
        }
    }

    private Word findWord(String str) {
        for (Word word : words) {
            if (word.getWord().equals(str)) {
                return word;
            }
        }
        return null;
    }

    private void printWords() {
        for (Word word : words) {
            System.out.println(word);
            for (Follow follow : word.getFollows()) {
                System.out.println("\t" + follow);
            }
        }
    }

    public void printModel() {
        for (Word w : words) {
            System.out.print(w.getWord() + ": ");
            ArrayList<Follow> follows = w.getFollows();
            for (Follow f : follows) {
                System.out.print(f.getWord() + "(" + f.getCount() + ") ");
            }
            System.out.println();
        }
    }

    public String[] writeSonnet() {
		ArrayList<Word> words = new ArrayList<Word>(this.words);
		String[] sonnet = new String[14];
		for (int i = 0; i < sonnet.length; i++) {
			StringBuilder line = new StringBuilder();
			Word currentWord = words.get((int) random(words.size()));
			line.append(currentWord.getWord());
			for (int j = 0; j < 7; j++) {
				ArrayList<Follow> follows = currentWord.getFollows();
				if (follows.size() == 0) {
					break;
				}
				Follow randomFollow = follows.get((int) random(follows.size()));
				line.append(" " + randomFollow.getWord());
				currentWord = findWord(randomFollow.getWord());
			}
			sonnet[i] = line.toString();
		}
		return sonnet;
	}	

	public void printSonnet() {
		String[] sonnet = writeSonnet();
		background(0);
		fill(255);
		textSize(20);
		textAlign(CENTER, CENTER);
		float y = height / 2 - sonnet.length * 20;
		for (String line : sonnet) {
			text(line, width / 2, y);
			y += 40;
		}
	}
	
}
