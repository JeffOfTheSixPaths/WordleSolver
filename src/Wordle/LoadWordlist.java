package Wordle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadWordlist {


    // This class is used to load the wordlist from a file.
    // Naturally it also handles whatever error might show up.


    private static LoadWordlist instance; // don't want multiple versions of this.

    private List<String> wordList = new ArrayList<>(); // just the wordlist from the file

    private static final String WORDLIST_PATH = "/data/valid-wordle-words.txt"; // default path.


    // makes sure there is only one loader.
    public static LoadWordlist getInstance() {
        if (instance == null) {
            instance = new LoadWordlist();
        }
        return instance;
    }

    // loads the words
    public LoadWordlist() {
        loadWords();
    }

    // checks to see that the wordlist is avaiable
    // also checks to see if the path exists + is readable.
    // Then just reads all the words in upper case into the wordlist.
    public void loadWords() {
        wordList.clear();

        try (InputStream is = getClass().getResourceAsStream(WORDLIST_PATH)) { // gets the wordlist path and stores it into is.

            if (is == null) { // making sure it exists
                System.out.println("WORD LIST NOT FOUND: " + WORDLIST_PATH);
                return;
            }

            // reads it into the wordlist while trimming everything.
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    wordList.add(line.trim().toUpperCase());
                }
            }

            System.out.println("Loaded " + wordList.size() + " valid words."); // shows the amount of words in the list.
        }
        catch (Exception e) {
            System.out.println("Error loading word list: " + e.getMessage());
        }
    }

    // returns the wordlist
    public List<String> getWords() {
        return wordList;
    }


    // returns the path
    public String getWordlistFileName() {
        return WORDLIST_PATH;
    }


    // sees if we have a certain word in the wordlist already.
    public boolean findWord(String word) {
        if (word == null) return false;
        return wordList.contains(word.toUpperCase());
    }

    // same thing as findWord, just helps with coding.
    public boolean isWordExists(String word) {
        return findWord(word);
    }

    // adds a word to the wordlist.
    // returns whether or not it successfully added the word
    // false if it already exists
    // true if it was correctly added.
    public boolean addWord(String word) {
        if (word.length() != 5) return false;
        if (wordList.contains(word.toUpperCase())) return false;

        wordList.add(word.toUpperCase());
        Collections.sort(wordList);
        saveWords();
        return true;
    }


    //finds and removes a word from the wordlist.
    public boolean removeWord(String word) {
        boolean removed = wordList.remove(word.toUpperCase());
        Collections.sort(wordList);
        if (removed) saveWords();
        return removed;
    }

    // saves the current wordlist by writing it to a file.
    private void saveWords() {
        // Saving cannot use resources — save next to original txt
        try (PrintWriter pw = new PrintWriter(
                new FileWriter("src/main/resources/data/valid-wordle-words.txt"))) {

            for (String word : wordList) {
                pw.println(word);
            }
        } catch (IOException e) {
            System.out.println("Error saving word list: " + e.getMessage());
        }
    }
}

