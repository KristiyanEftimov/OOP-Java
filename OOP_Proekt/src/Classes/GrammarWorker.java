package Classes;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
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
        int grammarAmount = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            while(grammarAmount == 0) {
                grammarAmount = parseInt(scanner.nextLine());
            }
        } catch (Exception e){
            System.out.println("Incorrect input " + e.getMessage());
        }
        while (grammars.size() < grammarAmount) {
            try {
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    String[] words = input.split(" ");
                    grammars.add(new Grammar(grammars.size(), new ArrayList<>(Arrays.asList(words))));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public ArrayList<Integer> list() {
        ArrayList<Integer> ids = new ArrayList<>();
        grammars.forEach(grammar -> ids.add(grammar.getId()));
        return ids;
    }

    public void saveGrammar(int id, String filename) throws IOException {
            grammars.get(id);
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(String.valueOf(grammars.get(id)));
            fileWriter.close();
            File myObj = new File(filename);
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

    public int iter(int id) {
        ArrayList<String> words = new ArrayList<String>();
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

    public Grammar open( String fileName) throws IOException {
        ArrayList<Grammar> grammarOpen = new ArrayList<>();
        Grammar grammar = new Grammar();
        File file = new File(fileName);
        if(file.exists()) {
            FileInputStream fileOpen = new FileInputStream(fileName);
            if(fileOpen.available() != 0){
                XMLDecoder decoder = new XMLDecoder(fileOpen);
                grammarOpen.add((Grammar) decoder.readObject());
                decoder.close();
                fileOpen.close();
            }
            System.out.println("Successfully opened " + fileName);
        }
        else {
            boolean newFile = file.createNewFile();
            System.out.println("Successfully created " + fileName);
        }
        return grammar;
    }

    public void close(String fileName) {
        System.out.println("Successfully closed " + fileName);
    }

    public void save(String filename) throws IOException {
        FileOutputStream file= new FileOutputStream(filename);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(String.valueOf(grammars));
        encoder.close();
        file.close();
        System.out.println("Successfully saved " + filename);
    }

    public void saveas(String filename) throws IOException {
        FileOutputStream file= new FileOutputStream(filename);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(String.valueOf(grammars));
        encoder.close();
        file.close();
        System.out.println("Successfully saved " + filename);
  }

    public void help(){
        System.out.println("The following commands are supported:");
        System.out.println("open <file> opens <file> " );
        System.out.println("close closes currently opened file");
        System.out.println("saveas <file> saves the currently open file in <file>");
        System.out.println("help prints this information");
        System.out.println("exit  exists the program");
    }

    public void exit(){
        System.out.println("Exiting the program");
        System.exit(0);
    }
}
