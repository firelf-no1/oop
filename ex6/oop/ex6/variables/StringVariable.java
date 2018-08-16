package oop.ex6.variables;

import oop.ex6.regex.Regex;

/**
 * a class that holds all of the String variables
 * extends Variable
 * */
public class StringVariable extends Variable {
    private String string;

    /**
     * a constructor
     * @param varName the name of the variable
     * @param varFinal is the file declared final
     */
    StringVariable(String varName, boolean varFinal){
        super(varName, varFinal, Regex.STRING_TYPE, Regex.INPUT_STRING);
    }

    /**
     * a geter
     * @return the string
     */
    public String getString() { return this.string; }

    /**
     * a seter
     * @param string the string we want to set
     */
    public void setString(String string) { this.string = string; }
}