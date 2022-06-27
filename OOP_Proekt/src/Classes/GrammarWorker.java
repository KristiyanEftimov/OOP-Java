package Classes;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class GrammarWorker {
    private ArrayList<Grammar> grammars;

    public GrammarWorker(ArrayList<Grammar> grammars) {
        this.grammars = grammars;
    }

    public ArrayList<Grammar> getGrammars() {
        return grammars;
    }

    public void addGrammar() {
        System.out.println("Please enter amount of grammars");
        int grammarAmount;
        Scanner scanner = new Scanner(System.in);
        grammarAmount = parseInt(scanner.nextLine());
        try {
            for (int i = 0; i < grammarAmount; i++) {
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    String[] words = input.split(" ");
                    grammars.add(new Grammar(grammars.size(), new ArrayList<>(Arrays.asList(words))));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Integer> list() {
        ArrayList<Integer> ids = new ArrayList<>();
        grammars.forEach(grammar -> ids.add(grammar.getId()));
        return ids;
    }

    public void saveGrammar(int id, String filename) throws IOException {
            FileOutputStream file = new FileOutputStream(filename);
            XMLEncoder encoder = new XMLEncoder(file);
            encoder.writeObject(String.valueOf(grammars.get(id)));
            encoder.close();
            file.close();
            System.out.println("Grammar saved in " + filename);
    }

    public void addRule(int id, String rule) {
        grammars.get(id).addRule(rule);
    }

    public void removeRule(int id, int ruleIndex) {
        grammars.get(id).removeRule(ruleIndex);
    }

    public boolean empty(int id) {
        return grammars.get(id).empty();
    }

    public void union(int id1, int id2) {
        ArrayList<String> words = new ArrayList<>();
        words.addAll(grammars.get(id1).getWords());
        words.addAll(grammars.get(id2).getWords());
        int id = grammars.size();
        Grammar newGrammar = new Grammar(id, words);
        grammars.add(newGrammar);
    }

    public void concat(int id1, int id2) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> words1 = grammars.get(id1).getWords();
        ArrayList<String> words2 = grammars.get(id2).getWords();
        for (int i = 0; i < words1.size(); i++) {
            for (int j = 0; j < words2.size(); j++) {
                String word = words1.get(i) + words2.get(j);
                words.add(word);
            }
        }
        int id = grammars.size();
        Grammar newGrammar = new Grammar(id, words);
        grammars.add(newGrammar);
    }

    public int iter(int id) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> originalWords = grammars.get(id).getWords();
        Grammar newGrammar = new Grammar(grammars.size(), words);
        StringBuilder allWords = new StringBuilder();
        newGrammar.addRule("");
        for (int i = 0; i < originalWords.size(); i++) {
            newGrammar.addRule(originalWords.get(i));
            allWords.append(originalWords.get(i));
        }
        newGrammar.addRule(allWords.toString());
        for (int i = 0; i < originalWords.size(); i++) {
            newGrammar.addRule(originalWords.get(i) + originalWords.get(i));
        }
        grammars.add(newGrammar);
        return grammars.size() - 1;
    }
}
