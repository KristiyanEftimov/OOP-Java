package Classes;

import java.util.ArrayList;

public class LogicalOperations extends GrammarWorker {

    public LogicalOperations(ArrayList<Grammar> grammars) {
        super(grammars);
    }

    public void union(int id1, int id2) {
        ArrayList<String> words = new ArrayList<>();
        words.addAll(getGrammars().get(id1).getWords());
        words.addAll(getGrammars().get(id2).getWords());
        int id = getGrammars().size();
        Grammar newGrammar = new Grammar(id, words);
        getGrammars().add(newGrammar);
    }

    public void concat(int id1, int id2) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> words1 = getGrammars().get(id1).getWords();
        ArrayList<String> words2 = getGrammars().get(id2).getWords();
        for (int i = 0; i < words1.size(); i++) {
            for (int j = 0; j < words2.size(); j++) {
                String word = words1.get(i) + words2.get(j);
                words.add(word);
            }
        }
        int id = getGrammars().size();
        Grammar newGrammar = new Grammar(id, words);
        getGrammars().add(newGrammar);
    }

    public int iter(int id) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> originalWords = getGrammars().get(id).getWords();
        Grammar newGrammar = new Grammar(getGrammars().size(), words);
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
        getGrammars().add(newGrammar);
        return getGrammars().size() - 1;
    }
}
