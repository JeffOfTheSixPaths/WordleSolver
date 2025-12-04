package Wordle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadWordlist {

    private static LoadWordlist instance;

    private List<String> wordList = new ArrayList<>();
    private static final String WORDLIST_PATH = "/data/valid-wordle-words.txt";

    public static LoadWordlist getInstance() {
        if (instance == null) {
            instance = new LoadWordlist();
        }
        return instance;
    }

    public LoadWordlist() {
        loadWords();
    }

    public void loadWords() {
        wordList.clear();

        try (InputStream is = getClass().getResourceAsStream(WORDLIST_PATH)) {

            if (is == null) {
                System.out.println("WORD LIST NOT FOUND: " + WORDLIST_PATH);
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    wordList.add(line.trim().toUpperCase());
                }
            }

            System.out.println("Loaded " + wordList.size() + " valid words.");
        }
        catch (Exception e) {
            System.out.println("Error loading word list: " + e.getMessage());
        }
    }


    public List<String> getWords() {
        return wordList;
    }

    public String getWordlistFileName() {
        return WORDLIST_PATH;
    }

    public boolean findWord(String word) {
        if (word == null) return false;
        return wordList.contains(word.toUpperCase());
    }

    public boolean isWordExists(String word) {
        return findWord(word);
    }

    public boolean addWord(String word) {
        if (word.length() != 5) return false;
        if (wordList.contains(word.toUpperCase())) return false;

        wordList.add(word.toUpperCase());
        Collections.sort(wordList);
        saveWords();
        return true;
    }

    public boolean removeWord(String word) {
        boolean removed = wordList.remove(word.toUpperCase());
        Collections.sort(wordList);
        if (removed) saveWords();
        return removed;
    }

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

