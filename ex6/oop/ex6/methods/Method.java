package oop.ex6.methods;

import oop.ex6.variables.Variable;
import oop.ex6.variables.Variables;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * extends variables
 * represents a method class.
 */
public class Method extends Variables {
    private Variable methodReturn;
    private List<Variable> methodVar;
    private LinkedList<Line> methodLines;

    /**
     * a constructor
     * @param methodName this method's name.
     * @param methodReturn this method's return var.
     * @param methodVar all of the vars in the method brackets()
     */
    public Method(String methodName, Variable methodReturn, List<Variable> methodVar){
        super(methodName);
        this.methodReturn = methodReturn;
        this.methodLines = new LinkedList<>();
        this.methodVar = methodVar;
    }

    /**
     * @return all of the vars that where in the method brackets
     */
    public List<Variable> getMethodVar() { return this.methodVar;}

    /**
     * @return the return var of the method
     */
    public Variable getMethodReturn() { return this.methodReturn; }

    /**
     * adding lines to the method
     * @param line the line we want to add
     */
    public void addLines(String line){this.methodLines.add(new Line(line));}

    /**
     * an iterator
     * @return the method line iterator
     */
    public Iterator<Line> methodLinesIterator(){return this.methodLines.iterator();}
}
