package oop.ex4.data_structures;

/**
 * FILE : Node.java
 * WRITERS : Itai shopen firelf 021982038
 *           Asaf yehudai asaf305612939 305612939
 * DESCRIPTION:
 * An implementation of a node in avl tree
 */

public class AvlNode {

    /* the balance factor of a the node */
    private int balanceFactor = -1;

    /* the key of node */
    private int data, hight;

    /* the connected nodes to our node */
    private AvlNode childRightNode, childLeftNode, parent;


    //------------- CONSTRUCTORS ---------------//

    /**
     * The default constructor
     * @param value - The value of the key
     * @param parent - The parent of the node
     */
    public AvlNode(int value, AvlNode parent) {
        this.data = value;
        this.parent = parent;

    }


    //---------------- SETTERS ------------------//


    /**
     * sets the parent of the node
     * @param parent - the node we set as node parent
     */
    public void setParent(AvlNode parent) {
        this.parent = parent;
    }

    /**
     * sets a node as a right child of the node
     * and sets the node as the parent of the right child
     * @param right - the node we set as the node right child
     */
    public void setRightNode(AvlNode right) {
        this.childRightNode = right;

    }

    /**
     * sets a node as a left child of the node
     * and sets the node as the parent of the left child
     * @param left - the node we set as the node left child
     */
    public void setLeftNode(AvlNode left) {
        this.childLeftNode = left;
    }


    /**
     * sets the data of a node
     * @param data - the value that we set as the node key
     */
    public void setData(int data) {
        this.data = data;
    }

    public void setHelperRight(AvlNode node){
        this.getRightNode().setParent(this.getParent());
        this.getParent().setLeftNode(this.getRightNode());
        this.setRightNode(node.getRightNode());
        node.getRightNode().setParent(this);
        this.setLeftNode(node.getLeftNode());
        node.getLeftNode().setParent(this);
    }
    public void setHelperLeft(AvlNode node){
        this.getParent().setLeftNode(null);
        this.setLeftNode(node.getLeftNode());
        node.getLeftNode().setParent(this);
        this.setRightNode(node.getRightNode());
        node.getRightNode().setParent(this);
    }
    public void setLeftAll(AvlNode node){
        this.setLeftNode(node);
        node.setParent(this);

    }
    public void setRightAll(AvlNode node){
        this.setRightNode(node);
        node.setParent(this);
    }

    public void setTheParent(AvlNode node){
        this.setParent(node.getParent());
        if (node.getData() > node.getParent().getData()){
            node.getParent().setRightNode(this);
        }
        else{
            node.getParent().setLeftNode(this);
        }
    }


    /**
     * sets the balance factor of the node
     * @param balanceValue -  the value we set as balance factor of the node
     */
    public void setBalanceFactor(int balanceValue) {
        this.balanceFactor = balanceValue;
    }


    //---------------- GETTERS ------------------//


    /**
     * gets the parent of the node
     * @return parent - the parent of the node
     */
    public AvlNode getParent() {
        return this.parent;
    }

    /**
     * gets right chiled of the node.
     * @return the right chiled of the node
     */
    public AvlNode getRightNode() {
        return this.childRightNode;
    }

    /**
     * gets the left child of the node
     * @return the left child of the node
     */
    public AvlNode getLeftNode() {
        return this.childLeftNode;
    }

    /**
     * gets the node data
     * @return the node data
     */
    public int getData() {
        return this.data;
    }

    /**
     * gets the balance factor
     * @return balance factor
     */
    public int getBalanceFactor() {
        return this.balanceFactor;
    }


}


