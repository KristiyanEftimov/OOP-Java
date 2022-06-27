package Classes;

import java.util.ArrayList;

public class Rules extends GrammarWorker {

    public Rules(ArrayList<Grammar> grammars) {
        super(grammars);
    }

    public void addRule(int id, String rule) {
        getGrammars().get(id).addRule(rule);
    }

    public void removeRule(int id, int ruleIndex) {
        getGrammars().get(id).removeRule(ruleIndex);
    }
}
