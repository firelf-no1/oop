package oop.ex4.data_structures;
import java.util.*;

/**
 * FILE : AvlTree.java
 * WRITER : Itai shopen firelf 021982038
 *          Asaf yehudai asaf305612939 305612939
 * DESCRIPTION:
 * An implementation of the AVL tree data structure.
 */

public class AvlTree extends java.lang.Object implements  Iterable<Integer> {

    private boolean resetFreeNode = true;

    /* size represent the tree size */
    private int size = 0;

    private static int MAX_HEIGHT = 31, MIN_HEIGHT = 90, ERROR_NUMBER = -1;

    private final int AVL_BALANCE_HIGHT = 2, EQUAL_HEIGHT = 1, ROOT_HEIGHT = 0;

    private static double GOLDEN_RATIO = ((1 + Math.sqrt(5)) / 2);

    private AvlNode root, freeNode; // freeNode is a leaf in the tree


    /**
     * The default constructor.
     */
    public AvlTree() {

    }

    /**
     * A constructor that builds a new AVL tree containing all unique values in the input array.
     *
     * @param data - the values to add to tree.
     */
    public AvlTree(int[] data) {
        try{
            for (int value : data) {
                add(value);
            }
        }
        catch (NullPointerException e){
            new AvlTree();
        }
    }

    /**
     * A copy constructor that creates a deep copy of the given AvlTree. The new tree contains all the values
     * of the given tree, but not necessarily in the same structure.
     *
     * @param avltree - The AVL tree to be copied.
     */
    public AvlTree(AvlTree avltree) {
        try{
            for (int value : avltree) {
                add(value);
            }
        }
        catch (NullPointerException e){
            new AvlTree();
        }
    }

    /**
     * Add a new node with the given key to the tree.
     * ∗
     * ∗ @param newValue the value of the new node to add.
     * ∗ @return true if the value to add is not already in the tree and it was successfully added,
     * ∗ false otherwise.
     */
    public boolean add(int newValue) {
        resetFreeNode = true;
        if (root == null) {
            root = new AvlNode(newValue, null);
            size++;
            return true;
        } else {
            if (contains(newValue) == -1) {
                AvlNode newNode = new AvlNode(newValue, freeNode);
                if (newValue > freeNode.getData()) {
                    freeNode.setRightNode(newNode);
                    newNode.setParent(freeNode);
                } else {
                    freeNode.setLeftNode(newNode);
                    newNode.setParent(freeNode);
                }
                size++;
                checkTreeBalance();
                return true;
            }
            return false;
        }

    }

    /**
     * Check whether the tree contains the given input value.
     * ∗
     * ∗ @param searchVal the value to search for.
     * ∗ @return the depth of the node (0 for the root) with the given value if it was found in
     * ∗ the tree, −1 otherwise.
     */
    public int contains(int searchVal) {
        AvlNode parent = null;
        AvlNode current = root;
        int height = 0;
        while (current != null) {
            if (current.getData() == searchVal) {
                changeFreeNode(current);
                return height;
            }
            height++;
            if (searchVal > current.getData()) {
                parent = current;
                current = current.getRightNode();
            } else {
                parent = current;
                current = current.getLeftNode();
            }

        }
        changeFreeNode(parent);
        return ERROR_NUMBER;
    }

    /**
     * Removes the node with the given value from the tree, if it exists.
     * ∗
     * ∗ @param toDelete the value to remove from the tree.
     * ∗ @return true if the given value was found and deleted, false otherwise.
     */
    public boolean delete(int toDelete) {
        resetFreeNode = true;
        //checking how many children does the node have
        if (contains(toDelete) != -1) {
            //no children
            if (freeNode.getLeftNode() == null && freeNode.getRightNode() == null) {
                deleteHelper("none");
            } else {
                // one child
                if ((freeNode.getLeftNode() != null && freeNode.getRightNode() == null) ||
                        ((freeNode.getLeftNode() == null && freeNode.getRightNode() != null))) {
                    deleteHelper("one");
                }
                //two children
                else {
                    deleteHelper("two");
                }
            }
            size--;
            checkTreeBalance();
            return true;
        }
        return false;
    }

    /**
     * @return the number of nodes in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * @return an iterator for the Avl Tree. The returned iterator iterates over the tree nodes
     * ∗ in an ascending order, and does NOT implement the remove() method.
     */
    public java.util.Iterator<Integer> iterator() {
        class iterator implements Iterator<Integer>  {
            private Stack<AvlNode> stack;
            private AvlNode localNode = root;

            private iterator(){
                stack = new Stack<AvlNode>();
                while (localNode != null) {
                    stack.push(localNode);
                    localNode = localNode.getLeftNode();
                }
            }

            public boolean hasNext() {
                return !stack.isEmpty();
            }

            public Integer next() {
                AvlNode node = stack.pop();
                int result = node.getData();
                try {
                    node = node.getRightNode();
                    while (node != null) {
                        stack.push(node);
                        node = node.getLeftNode();
                    }
                }
                catch (NullPointerException e){
                    return result;
                }
                return result;
            }


            public void remove() {
                throw new UnsupportedOperationException();

            }

        }
        return new iterator();
    }




    /**
     * Calculates the minimum number of nodes in an AVL tree of height h.
     * @param h - the height of the tree (a non-negative number) in question.
     * @return the minimum number of nodes in an AVL tree of the given height.
     */
    public static int findMinNodes(int h)
    {
        if (h < MIN_HEIGHT) {
            return (int)(Math.round(((Math.sqrt(5) + 2) / Math.sqrt(5)) * Math.pow((GOLDEN_RATIO), h) - 1));
        }
        else {
            System.out.println("I'm sorry but I can't calculate that so I'll guss it's 42");
            return ERROR_NUMBER;
        }
    }

    /**
     * Calculates the maximum number of nodes in an AVL tree of height h.
     * @param h the height of the tree (a non−negative number) in question.
     * @return the maximum number of nodes in an AVL tree of the given height.
     */
    public static int findMaxNodes(int h) {
        if (h < MAX_HEIGHT) {
            return (int) Math.round(Math.pow(2, h + 1) - 1);
        }
        else {
            System.out.println("I'm sorry but I can't calculate that so I'll guss it's 42");
            return ERROR_NUMBER;
        }
    }



    private void deleteHelper(String noOfChild) {
        switch (noOfChild)
        {
            case "none":
                // No children
                if (freeNode.getParent() != null)
                {
                    if (freeNode.getData() > freeNode.getParent().getData())
                    {
                        freeNode.getParent().setRightNode(null);
                    }
                    else
                    {
                        freeNode.getParent().setLeftNode(null);
                    }
                }
                else
                {
                    // want to delete root.
                    root = null;
                }
                break;
            case "one":
                // One child
                //left is not null
                if (freeNode.getLeftNode() != null)
                {
                    if (freeNode.getParent() != null){
                        if (freeNode.getData() > freeNode.getParent().getData())
                        {
                            (freeNode.getParent()).setRightAll(freeNode.getLeftNode());
                        }
                        else
                        {
                            (freeNode.getParent()).setLeftAll(freeNode.getLeftNode());
                        }
                    }
                    else{
                        root = freeNode.getLeftNode();
                        root.setParent(null);
                    }

                }
                //right is not null
                else
                {
                    if (freeNode.getParent() != null) {
                        if (freeNode.getData() > freeNode.getParent().getData())
                        {
                            freeNode.getParent().setRightAll(freeNode.getRightNode());
                        }
                        else
                            {
                            freeNode.getParent().setLeftAll(freeNode.getRightNode());

                        }
                    }
                    else{
                        root = freeNode.getRightNode();
                        root.setParent(null);
                    }
                }
                break;
            case "two":
                // Two children
                AvlNode tmp = freeNode.getRightNode();
                // right node has children on the left
                while (tmp.getLeftNode() != null)
                    {
                        tmp = tmp.getLeftNode();
                    }
                if (tmp != freeNode.getRightNode()){
                    //right is not null
                    if (tmp.getRightNode() != null){
                        tmp.setHelperRight(freeNode);
                        if (freeNode.getParent() != null) {
                            tmp.setTheParent(freeNode);
                        }
                        else{
                            tmp.setParent(null);
                            root = tmp;
                        }
                    }
                    else{
                        tmp.setHelperLeft(freeNode);
                        if (freeNode.getParent() != null) {
                            tmp.setTheParent(freeNode);
                        }
                        else{
                            tmp.setParent(null);
                            root = tmp;
                        }
                    }
                }
                else{
                    tmp.setLeftAll(freeNode.getLeftNode());
                    if (freeNode.getParent() != null) {
                        tmp.setTheParent(freeNode);
                    }
                    else{
                        tmp.setParent(null);
                        root = tmp;
                    }
                }
                break;
            default:
                System.out.println("Value does not excise: " + noOfChild);
                break;
        }
    }



    /**
     * allows the changing of the value of available just once.
     * @param node the node we want to replace the variable available.
     */
    private void changeFreeNode(AvlNode node)
    {
        if (resetFreeNode)
        {
            resetFreeNode = false;
            freeNode = node;
        }
    }

    private void checkTreeBalance()
    {
        freeNode = null;
        resetFreeNode = true;
        setTreeBalanceFactor(root);
        if (freeNode != null)
        {
            selectAlgorithm(freeNode);
            rootUpdate(freeNode);
        }
    }

    /**
     * run through all the nodes starting from the node given and sets balance factor's to them.
     *
     * @param node root of tree.
     */
    private void setTreeBalanceFactor(AvlNode node)
    {
        if (node != null) {
            //recursion
            setTreeBalanceFactor(node.getLeftNode());
            setTreeBalanceFactor(node.getRightNode());
            //no children
            if (node.getRightNode() == null && node.getLeftNode() == null)
            {
                node.setBalanceFactor(1);
                // one child
            }
            else if (node.getRightNode() == null || node.getLeftNode() == null)
            {
                if (node.getRightNode() != null)
                {
                    node.setBalanceFactor(node.getRightNode().getBalanceFactor() + EQUAL_HEIGHT);
                    //check balance factor for right node with no left son.
                    if (node.getRightNode().getBalanceFactor() >= AVL_BALANCE_HIGHT)
                    {
                        changeFreeNode(node);
                    }
                }
                else
                {
                    node.setBalanceFactor(node.getLeftNode().getBalanceFactor() + EQUAL_HEIGHT);
                    //check balance factor for left node with no right son.
                    if (node.getLeftNode().getBalanceFactor() >= AVL_BALANCE_HIGHT)
                    {
                        changeFreeNode(node);
                    }
                }
                //two children
            }
            else
            {
                node.setBalanceFactor(Math.max(node.getLeftNode().getBalanceFactor(),
                        node.getRightNode().getBalanceFactor()) + EQUAL_HEIGHT);
                if (Math.abs(node.getLeftNode().getBalanceFactor() -
                        node.getRightNode().getBalanceFactor()) > EQUAL_HEIGHT)
                {
                    changeFreeNode(node);
                }
            }
        }
    }

    private void selectAlgorithm(AvlNode node) {
        //see what algorithm to use (RR, LL, RL, LR)
        int left, right, grandLeft, grandRight;

        //check to see if one of the sons is null.
        if (node.getLeftNode() == null) {
            left = 0;
        }
        else {
            left = node.getLeftNode().getBalanceFactor();
        }
        if (node.getRightNode() == null) {
            right = 0;
        }
        else {
            right = node.getRightNode().getBalanceFactor();
        }

        //check the height to see what kind of rotation to use.
        if (left > right) {
            //check to see if one of the grandchildren is  null
            if (node.getLeftNode().getLeftNode() == null) {
                grandLeft = 0;

            }
            else {
                grandLeft = node.getLeftNode().getLeftNode().getBalanceFactor();
            }

            if (node.getLeftNode().getRightNode() == null) {
                grandRight = 0;
            }
            else {
                grandRight = node.getLeftNode().getRightNode().getBalanceFactor();
            }

            if (grandLeft > grandRight) {
                rotationLL(node);
            }
            else {
                rotationLR(node);
            }

        }
        else {
            //check to see if one of the grandchildren is  null
                if (node.getRightNode().getLeftNode() == null) {
                    grandLeft = 0;
                }
                else {
                    grandLeft = node.getRightNode().getLeftNode().getBalanceFactor();
                }
                if (node.getRightNode().getRightNode() == null){
                    grandRight=0;
                }
                else{
                    grandRight = node.getRightNode().getRightNode().getBalanceFactor();
                }


                if (grandLeft > grandRight) {
                    rotationRL(node);
                }
                else {
                    rotationRR(node);
                }
        }

    }

    private void rotationLL (AvlNode node){
        AvlNode parent = node.getParent();
        AvlNode child = node.getLeftNode();

        if (parent != null) {
            if (parent.getLeftNode() == node) {
                parent.setLeftAll(child);

            }
            else {
                parent.setRightAll(child);
            }
        }
        else {
            child.setParent(null);
        }
        node.setLeftNode(child.getRightNode());
        if (child.getRightNode() != null) {
            child.getRightNode().setParent(node);
        }
        child.setRightAll(node);

    }


    private void rotationRR (AvlNode node){
        AvlNode parent = node.getParent();
        AvlNode child = node.getRightNode();

        if (node.getParent() != null) {
            if (parent.getRightNode() == node) {
                parent.setRightAll(child);
            }
            else {
                parent.setLeftAll(child);
            }
        }
        else {
            child.setParent(null);
        }
        node.setRightNode(child.getLeftNode());
        if (child.getLeftNode() != null){
            child.getLeftNode().setParent(node);
        }
        child.setLeftAll(node);
    }

    private void rotationLR (AvlNode node){
        AvlNode child = node.getLeftNode();
        AvlNode grandchild = child.getRightNode();
        node.setLeftNode(grandchild);
        grandchild.setParent(node);
        child.setRightNode(grandchild.getLeftNode());
        if (grandchild.getLeftNode() != null){
            grandchild.getLeftNode().setParent(child);
        }
        grandchild.setLeftAll(child);
        rotationLL(node);
    }


    private void rotationRL (AvlNode node){
        AvlNode child = node.getRightNode();
        AvlNode grandchild = child.getLeftNode();
        node.setRightNode(grandchild);
        grandchild.setParent(node);
        child.setLeftNode(grandchild.getRightNode());
        if (grandchild.getRightNode() != null){
            grandchild.getRightNode().setParent(child);
        }
        grandchild.setRightAll(child);
        rotationRR(node);
    }

    /**
     * updates the root after each rotation.
     * @param node node to start from.
     */
    private void rootUpdate(AvlNode node)
    {
        AvlNode current = node;
        while (current.getParent() != null)
        {
            current = current.getParent();
        }
        root = current;
    }
}
