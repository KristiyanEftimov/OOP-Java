package Classes;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class FileOperations extends GrammarWorker {

    public FileOperations(ArrayList<Grammar> grammars) {
        super(grammars);
    }

    public void open(String fileName) throws IOException {
        File file = new File(fileName);
        if(file.exists()) {
            XMLDecoder d = new XMLDecoder(
                new BufferedInputStream (
                        new FileInputStream(fileName)));
            Object result = d.readObject();
            d.close();
            System.out.println("Successfully opened " + fileName);
            System.out.println(result);
        }
        else {
            boolean newFile = file.createNewFile();
            if(newFile){
                System.out.println("Successfully created " + fileName);
            }
        }
    }

    public void close(String fileName) {
        System.out.println("Successfully closed " + fileName);
    }

    public void saveGrammar(int id, String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(String.valueOf(getGrammars().get(id)));
        encoder.close();
        file.close();
        System.out.println("Grammar saved in " + filename);
    }

    public void save(String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(String.valueOf(getGrammars()));
        encoder.close();
        file.close();
        System.out.println("Successfully saved " + filename);
    }

    public void saveas(String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(String.valueOf(getGrammars()));
        encoder.close();
        file.close();
        System.out.println("Successfully saved " + filename);
    }

    public void help() {
        System.out.println("The following commands are supported:");
        System.out.println("open <file> opens <file>");
        System.out.println("close  closes currently opened file");
        System.out.println("save  saves the currently open file");
        System.out.println("saveas <file>  saves the currently open file in <file>");
        System.out.println("help prints this information");
        System.out.println("list  List of all grammar ids");
        System.out.println("print <id>  Prints grammars in appropriate format");
        System.out.println("saveGrammar <id> <filename>  saves chosen grammar in a file");
        System.out.println("addRule <id> <rule>  adds rule to a grammar");
        System.out.println("removeRule <id> <n>  remove rule from a grammar");
        System.out.println("union <id1> <id2>  unions two grammars and creates new one with new id");
        System.out.println("concat <id1> <id2>  concat two grammars and creates new one with new id");
        System.out.println("iter <id>  operation Kleene star from a grammar and creates new one with new id");
        System.out.println("empty <id>  checks if language in the grammar is empty");
    }

    public void exit() {
        System.out.println("Exiting the program");
        System.exit(0);
    }
}
