package no.hiof.fredrivo;

import java.io.*;

public class Main {

    private static BinaryTree tree = new BinaryTree();
    private static BufferedReader r;


    public static void main(String[] args) {
        try {
            r = new BufferedReader(new FileReader("churchill.txt"));
        }
        catch (FileNotFoundException e){
            System.out.println("Oops: " + e);
        }

        try {
            StreamTokenizer input = new StreamTokenizer(r);
            int x = input.nextToken();
            while (x != input.TT_EOF)
            {
                if (input.ttype == input.TT_WORD)
                    tree.insert(input.sval.replace(".", ""));
                x = input.nextToken ();
            }
            //finn på en finurlig måte å skrive ut treet.
            printTree(BinaryTree.getRoot());
        }
        catch (IOException e) {
            System.out.println("Something went oopsie: " + e);

        }

    }

    public static void printTree(Node node){
        if (node != null){
            printTree(node.getLeft());
            System.out.println(node.getWordAndAmount());
            printTree(node.getRight());
        }
    }

}
