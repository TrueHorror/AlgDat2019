package no.hiof.fredrivo;

import java.io.*;

public class Main {

    private static BinaryTree tree = new BinaryTree();
    private static BufferedReader r;


    public static void main(String[] args) {
        try {
            r = new BufferedReader(new FileReader("churchill.txt"));
            String line = null;
            while((line = r.readLine()) != null){
                String[] words = line.toUpperCase().replaceAll("[^A-Z ]", "").split(" ");
                for (String word: words){
                    tree.insert(word);
                }

            }
            tree.printInOrder();
        }
        catch (FileNotFoundException e){
            System.out.println("Oops: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }



}
