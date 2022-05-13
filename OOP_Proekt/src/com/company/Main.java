package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter amount of grammars");

        int grammarAmount = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            while(grammarAmount == 0) {
                grammarAmount = parseInt(scanner.nextLine());
            }
        } catch (Exception e){
            System.out.println("Incorrect input " + e.getMessage());
            return;
        }
        ArrayList<Grammar> grammars = new ArrayList<>();

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

        GrammarWorker worker = new GrammarWorker(grammars);



        int choice = -1;
        while (choice != 0) {
            System.out.println("Please select a method. To exit enter 0");

            String[] methods = {"ID List", "Print Grammar", "Save in file", "Add Rule", "Remove Rule", "Union", "Concat", "Empty", "Chomsky", "Chomskify", "Cyk"};
            for (int i = 0; i < methods.length; i++) {
                System.out.println(i+1 + ": " + methods[i]);
            }

            choice = parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("List of IDs for grammars");
                    for (int id : worker.list()) {
                        System.out.println(id);
                    }
                    break;
                case 2:
                    ShowGrammarMenu(worker);
                    break;
                case 3:
                    //worker.save();
                    break;
                case 4: {
                    ShowGrammarMenu(worker);
                    int id = parseInt(scanner.nextLine());
                    System.out.println("Please enter new rule");
                    String rule = scanner.nextLine();
                    worker.addRule(id, rule);
                }
                    break;
                case 5: {
                    ShowGrammarMenu(worker);
                    int id = parseInt(scanner.nextLine());
                    System.out.println("Please enter index of rule to remove");
                    int index = parseInt(scanner.nextLine());
                    worker.removeRule(id, index);
                }
                    break;
                case 6:
                {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter IDs to union");
                    int id1 = parseInt(scanner.nextLine());
                    System.out.println("Please enter IDs to union");
                    int id2= parseInt(scanner.nextLine());
                    worker.union(id1,id2);
                }
                    break;
                case 7:
                {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter IDs to concat");
                    int id1 = parseInt(scanner.nextLine());
                    System.out.println("Please enter IDs to concat");
                    int id2= parseInt(scanner.nextLine());
                    worker.concat(id1,id2);
                }
                    break;
                case 8:
                {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter id to check if empty");
                    int id = parseInt(scanner.nextLine());
                    System.out.println(worker.empty(id));
                }
                    break;
                case 9:
                {
                    // chomsky;
                }
                break;
                case 10:
                {
                  //chomskify;
                }
                break;
                case 11:
                {
                    //    cyk;
                }
                break;
            }
        }


    }

    public static void ShowGrammarMenu(GrammarWorker worker) {
        System.out.println("Please select a grammar");
        ArrayList<Grammar> grammars = worker.getGrammars();
        for (int id: worker.list()) {
            System.out.println(grammars.get(id).toString());
        }
    }
}

