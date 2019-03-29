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
                //regex sjekker sammensatte bokstaver, men tar bort spesialtegn.
                //Derfor i texten står det feks. 10,000, da vill den fjerne ","
                //og heller telle 10 og 000 som to seperate ord
                String[] words = line.toUpperCase().split("(?U)\\W+");
                for (String word: words){
                    if (!word.isEmpty()) {
                        tree.insert(word);
                    }

                }

            }
            System.out.println("Første 'ord' er rar.");
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
