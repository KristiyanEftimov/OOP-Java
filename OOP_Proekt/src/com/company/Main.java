package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException, BadLocationException {
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

        XMLEncoder e = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream("file.xml")));
        e.writeObject(String.valueOf(grammars));
        e.close();

        XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream("file.xml")));
        Object result = d.readObject();
        d.close();


        int choice = -1;
        while (choice != 0) {

            System.out.println("\n"+"Please select a method. To exit enter 0");

            String[] methods = {"ID List", "Print Grammar", "Save in file", "Add Rule", "Remove Rule", "Union", "Concat", "Empty", "Chomsky", "Chomskify",
                    "Cyk", "Kleene operation", "Open", "Close", "Save", "Save as", "Help", "Exit"};
            for (int i = 0; i < methods.length; i++) {
                System.out.println(i+1 + ": " + methods[i]);
            }

            choice = parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                {
                    System.out.println("List of IDs for grammars");
                    for (int id : worker.list()) {
                        System.out.println(id);
                    }
                }
                    break;
                case 2:
                {
                    ShowGrammarMenu(worker);
                }
                    break;
                case 3:
                {
                    ShowGrammarMenu(worker);
                    int id = parseInt(scanner.nextLine());
                    String filename = scanner.nextLine();
                    worker.save(id,filename);
                }
                    break;
                case 4:
                {
                    ShowGrammarMenu(worker);
                    int id = parseInt(scanner.nextLine());
                    System.out.println("Please enter new rule");
                    String rule = scanner.nextLine();
                    worker.addRule(id, rule);
                }
                    break;
                case 5:
                {
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
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter id to check if in Chomsky norm");
                    int id = parseInt(scanner.nextLine());
                    //worker.chomsky(id);
                }
                break;
                case 10:
                {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter id to convert grammar in Chomsky norm");
                    int id = parseInt(scanner.nextLine());
                    //worker.chomskify(id);
                }
                break;
                case 11:
                {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter id to check if in CYK norm");
                    int id = parseInt(scanner.nextLine());
                    //worker.cyk(id);
                }
                case 12:
                {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter id to make Kleene operation");
                    int id = parseInt(scanner.nextLine());
                    //worker.iter(id);
                }
                case 13:
                {
                    //if(choice == 13)
                    //open();
                }
                case 14:
                {
                  //  if(choice == 14)
                   // close("");
                }
                case 15:
                {
                    //if(choice == 15)
                   // save("Test.xml");
                }
                case 16:
                {
                   // if(choice == 16)
                   //saveas("newTest.xml");
                }
                case 17:
                {
                    if(choice == 17)
                   help();
                }
                case 18:
                {
                    if(choice == 18){
                    exit();}
                }
                break;
            }
        }


    }



/*
 public static void open(String filename) throws IOException {
        File file = new File("Test.xml");
        if(file.exists()){
            FileInputStream fileOpen = new FileInputStream(file);
            if(fileOpen.available() !=0){
                XMLDecoder decoder = new XMLDecoder(fileOpen);
                file = (grammar) decoder.readObject();
                decoder.close();
                fileOpen.close();
            }
            System.out.println("Sucessfully opened" + file.getName());
        }
        else{
            boolean newFile = file.createNewFile();
            System.out.println("Sucessfully created" + file.getName());
        }
    }


    public static void close(String filename) throws IOException {
        File myObj = new File(filename);

        //filename == null;

        System.out.println("Sucessfully closed" + filename);
    }

    public static void save(String filename) throws IOException{
        FileOutputStream file = new FileOutputStream(filename);
        XMLEncoder encoder =new XMLEncoder(file);
        encoder.writeObject(file);
        encoder.close();
        file.close();
        System.out.println("Sucessfully saved " + filename);
    }

    public static void saveas(String filename) throws IOException{
    FileOutputStream file = new FileOutputStream(filename);
    XMLEncoder encoder =new XMLEncoder(file);
    encoder.writeObject(file);
    encoder.close();
    file.close();
    System.out.println("Sucessfully saved" + filename);
}
*/
    public static void help(){
        System.out.println("The following commands are supported:");
        System.out.println("open <file> opens <file> " );
        System.out.println("close closes currently opened file");
        System.out.println("saveas <file> saves the currently open file in <file>");
        System.out.println("help prints this information");
        System.out.println("exit  exists the program");
    }

    public static void exit(){
        System.out.println("Exiting the program");
        System.exit(0);
    }


    public static void ShowGrammarMenu(GrammarWorker worker) {
        System.out.println("Please select a grammar");
        ArrayList<Grammar> grammars = worker.getGrammars();
        for (int id: worker.list()) {
            System.out.println(grammars.get(id).toString());
        }
    }
}

