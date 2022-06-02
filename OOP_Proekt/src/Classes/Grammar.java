package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Grammar extends ArrayList<Grammar> {
    private int id;
    private ArrayList<String> words;

    public Grammar(int id, ArrayList words) {
        this.id = id;
        this.words = words;
    }

    public Grammar() {

    }

    public ArrayList<String> getWords() {
        return words;
    }

    public int getId() {
        return id;
    }

    public void addRule(String rule) {
        words.add(rule);
    }

    public void removeRule(int n) {
        words.remove(n);
    }

    public boolean empty() {
        return words.isEmpty();
    }

    @Override
    public String toString() {
        return "id=" + id +
                ": words=" + words.toString();
    }
}
