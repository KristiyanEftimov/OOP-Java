package Classes;

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

    public boolean empty(int id) {
        return getGrammars().get(id).empty();
    }
}
