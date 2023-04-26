package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;

public class DANI extends PApplet {
	ArrayList<Word> model = new ArrayList<>();
	String[] sonnet;

	// method to set the size of the window
	public void settings() {
		size(1000, 1000);
	}

	// method to display the sonnet on the screen
	public void draw() {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
		textAlign(CENTER, CENTER);
	
		float lineHeight = height / 18;
		float yOffset = (height - lineHeight * sonnet.length) / 2;
	
		for (int i = 0; i < sonnet.length; i++) {
			text(sonnet[i], width / 2, yOffset + (i * lineHeight));
		}
	}
	
	// makes a new sonnet using the model
	public String[] writeSonnet() {
		String[] sonnet = new String[14];
		for (int i = 0; i < 14; i++) {
			sonnet[i] = writeLine();
		}
		return sonnet;
	}

	public void printModel() {
		for (Word wordObj : model) {
			println(wordObj.toString());
		}
	}

	// makes a single line of the sonnet using the model
	public String writeLine() {
		StringBuilder sb = new StringBuilder();
		int wordCount = 0;
		Word currentWord = model.get((int) random(model.size()));

		while (currentWord != null && wordCount < 8) {
			sb.append(currentWord.getWord()).append(" ");
			currentWord = pickRandomFollow(currentWord);
			wordCount++;
		}

		return sb.toString().trim();
	}

	// method for writeLine that chooses random next word for given word
	public Word pickRandomFollow(Word word) {
		if (word.getFollows().size() == 0) {
			return null;
		}

		String randomFollowWord = word.getFollows().get((int) random(word.getFollows().size())).getWord();
		return findWord(randomFollowWord);
	}
	
	// reads in a text file and creates a model of word frequencies and relationships
	public void initializeModel(String filename) {
		loadFile(filename);
	}

	public void setup() {
		colorMode(HSB);
		initializeModel("shakespere.txt");
		sonnet = writeSonnet();
		for (String line : sonnet) {
			println(line);
		}
	}

	// method called whenever a key is pressed making a new sonnet when the spacebar is pressed
	public void keyPressed() {
		if (key == ' ') {
			sonnet = writeSonnet();
		}
	}	

	float off = 0;
	
	// method for initializeModel that reads in the text file and updates the model
	public void loadFile(String filename) {
		String[] lines = loadStrings(filename);
		String prevWord = null;

		for (String line : lines) {
			String[] words = split(line, ' ');

			for (String w : words) {
				w = w.replaceAll("[^\\w\\s]", "").toLowerCase();

				if (prevWord != null) {
					Word wordObj = findWord(prevWord);
					if (wordObj == null) {
						wordObj = new Word(prevWord);
						model.add(wordObj);
					}

					Follow followObj = wordObj.findFollow(w);
					if (followObj == null) {
						wordObj.addFollow(new Follow(w, 1));
					} else {
						followObj.incrementCount();
					}
				}
				prevWord = w;
			}
		}
	}

	//  method that searches the model for a specific word and returns Word object
	public Word findWord(String str) {
		for (Word wordObj : model) {
			if (wordObj.getWord().equals(str)) {
				return wordObj;
			}
		}
		return null;
	}

}