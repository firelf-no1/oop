package oop.ex6.variables;

import oop.ex6.regex.Regex;

/**
 * a class that holds all of the Double variables
 * extends DoubleVar
 * */
class DoubleVariable extends DoubleVar {

    /**
     * a constructor
     * @param varName the name of the variable
     * @param varFinal is the file declared final
     */
    DoubleVariable(String varName, boolean varFinal){
        super(varName, varFinal, Regex.TYPE_DOUBLE, Regex.INPUT_DOUBLE);
    }
}