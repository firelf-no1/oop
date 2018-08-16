package oop.ex6.variables;

import oop.ex6.regex.Regex;

/**
 * a class that holds all of the boolean variables
 * extends DoubleVar
 * */
public class BooleanVariable extends DoubleVar {

    /**
     * a constructor
     * @param varName the name of the variable
     * @param varFinal is the file declared final
     */
    public BooleanVariable(String varName, boolean varFinal){
        super(varName, varFinal, Regex.TYPE_BOOLEAN, Regex.INPUT_BOOLEAN);
    }
}