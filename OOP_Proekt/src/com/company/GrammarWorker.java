package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GrammarWorker {

    private ArrayList<Grammar> grammars;

    public GrammarWorker(ArrayList<Grammar> grammars) {
        this.grammars = grammars;
    }

    public ArrayList<Grammar> getGrammars() {
        return grammars;
    }

    public ArrayList<Integer> list() {
        ArrayList<Integer> ids = new ArrayList<>();
        grammars.forEach(grammar -> ids.add(grammar.getId()));
        return ids;
    }

    public void save(int id, String filename)  throws IOException  {
        try {
            grammars.get(id);
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(String.valueOf(grammars.get(id)));
            fileWriter.close();
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else  {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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

    public int union(int id1, int id2) {
        ArrayList<String> words = new ArrayList<String>();
        words.addAll(grammars.get(id1).getWords());
        words.addAll(grammars.get(id2).getWords());
        int id = grammars.size();
        Grammar newGrammar = new Grammar(id, words);
        grammars.add(newGrammar);
        return id;
    }

    public int concat(int id1, int id2) {
        ArrayList<String> words = new ArrayList<String>();
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
        return id;
    }

    public void chomsky(int id) {

    }

    public void chomskify(int id) {

    }

    public void cyk(int id) {

    }

    public void iter(int id) {

        // gramatika [a , b]
        // " ",a,b,ab,aa,bb
    }

}
