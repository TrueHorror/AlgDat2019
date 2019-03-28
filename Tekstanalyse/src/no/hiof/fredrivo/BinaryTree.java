package no.hiof.fredrivo;

public class BinaryTree {
    private static Node root;

    public static Node getRoot() {
        return root;
    }

    public void insert(String word) {
        if (root == null){
            root = new Node(word);
            return;
        }

        Node currentNode = root;

        while(true){
            if (word.compareTo(currentNode.getWord()) < 0){
                if (currentNode.getLeft() == null){
                    currentNode.setLeft(new Node(word));
                    return;
                }

                currentNode = currentNode.getLeft();


            }

            else if(word.compareTo(currentNode.getWord()) > 0){
                if (currentNode.getRight() == null){
                    currentNode.setRight(new Node(word));
                    return;
                }

                currentNode = currentNode.getRight();


            }
            else {
                currentNode.count();
                return;
            }
        }

    }

    public void printInOrder(){
        printInOrder(root);
    }

    private void printInOrder(Node node){
        if (node != null){
            printInOrder(node.getLeft());
            System.out.println(node.write());
            printInOrder(node.getRight());
        }
    }


}
