package oop.ex6.variables;

import oop.ex6.regex.Regex;

/**
 * a class that holds all of the char variables
 * extends Variable
 * */
class CharVariable extends Variable {

    /**
     * a constructor
     * @param varName the name of the variable
     * @param varFinal is the file declared final
     */
    CharVariable(String varName,boolean varFinal){super(varName,varFinal,Regex.CHAR_TYPE,Regex.INPUT_CHAR);}
}