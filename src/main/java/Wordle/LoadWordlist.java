package Wordle;

/** This is the class that loads and deals with the wordlist
 *
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadWordlist{

    private ArrayList<String> wordlist = new ArrayList<String>();

    public LoadWordlist(String fileName){

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordlist.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }

    public ArrayList<String> getWordlist(){
        return wordlist;
    }

    public void add(String s){
        wordlist.add(s);
    }

    public void remove(String s){
        wordlist.remove(s); // not thoroughly tested
    }


    public static void main(String[] args){
        LoadWordlist lw = new LoadWordlist("data/valid-wordle-words.txt");
        ArrayList<String> w = lw.getWordlist();

        System.out.println(w.size());

        LoadWordlist answers = new LoadWordlist("data/wordle-solutions.txt");
        System.out.println(answers.getWordlist().size());
    }
}