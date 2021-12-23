import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line1 = scanner.nextLine().split(" ");
        int size = Integer.valueOf(line1[0]);
        int k = Integer.valueOf(line1[1]);
        String[] line2 = scanner.nextLine().split(" ");

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < k - 1; i++) {
            Node node = new Node(-Integer.valueOf(line2[i]));
            binarySearchTree.insert(node);
            System.out.print("_" + " ");
        }

        for (int i = k-1; i < size; i++) {
            Node node = new Node(-Integer.valueOf(line2[i]));
            binarySearchTree.insert(node);
            System.out.print(-kElemnt(k,binarySearchTree) + " ");
            binarySearchTree.delete(binarySearchTree.maximum(BinarySearchTree.root));

        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
    }

    public static int kElemnt(int k, BinarySearchTree binarySearchTree){
        Node y = binarySearchTree.minimum(BinarySearchTree.root);
        for(int i=0; i<k-1; i++ ){
            y = binarySearchTree.getSuccessor(y);
        }

        return y.value;
    }
}

class BinarySearchTree {

    public static Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node getSuccessor(Node node1) {

        if(node1.right != null){
            return minimum(node1.right);
        }

        Node parent = node1.parent;

        while (parent != null && node1 == parent.right) {
            node1 = parent;
            parent = parent.parent;
        }

        return parent;

    }

    public Node minimum(Node x){
        while(x.left != null){
            x = x.left;
        }

        return x;
    }

    public Node maximum(Node x){
        while (x.right != null){
            x = x.right;
        }

        return x;
    }

    public void insert(Node z) {
        Node y = null;
        Node x = root;
        while (x != null) {
            y = x;
            if (z.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            root = z;
        } else if (z.value < y.value) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

//    public void deleteMin(Node x){
//
//        if(x.left != null){
//            x.left.parent = x.parent;
//            x.parent.right = x.left;
//        }else{
//            x.parent.right = null;
//        }
//        x = null;
//
//    }

    public static void transplant(Node u, Node v){
        if (u.parent == null)
            BinarySearchTree.root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;

        if(v != null)
            v.parent = u.parent;

    }

    public void delete(Node x){
        if(x.left == null)
            transplant(x,x.right);
        else if(x.right == null)
            transplant(x,x.left);
    }
}

class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
