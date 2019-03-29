package no.hiof.fredrivo;

public class Node {
    private Node left;
    private Node right;
    private int counter = 0;
    private String word;

    public String getWord() {
        return word;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setCounter(int counter) {
        this.counter = counter;
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

    public int getCounter() {
        return counter;
    }

    public String write() {
        return word + ": " + counter;
    }

    public Node(String word) {
        this.left = null;
        this.right = null;
        this.counter = 1;
        this.word = word;
    }

    public void count(){
        counter++;
    }
}
