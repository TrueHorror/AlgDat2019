package no.hiof.fredrivo;

public class Node {
    private Node left;
    private Node right;
    private int teller = 0;
    private String word;

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setTeller(int teller) {
        this.teller = teller;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getTeller() {
        return teller;
    }

    public String getWord() {
        return word;
    }

    public Node(String word) {
        this.left = null;
        this.right = null;
        this.teller = 1;
        this.word = word;
    }

    public void count(){
        teller++;
    }
}
