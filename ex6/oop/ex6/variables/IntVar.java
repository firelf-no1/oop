package oop.ex6.variables;

import oop.ex6.regex.Regex;

/**
 * a abstract class that holds all of the integrable type variables
 * extends Variable
 * */
public abstract class IntVar extends Variable {
    private static final String INT_TYPE = Regex.TYPE_INT;

    /**
     * a constructor
     * @param varName the name of the variable
     * @param varFinal is the file declared final
     * @param varType the type of the variable
     * @param varRegex the regex of the variable
     */
    IntVar(String varName, boolean varFinal, String varType, String varRegex){
        super(varName, varFinal, varType, varRegex);
    }

    /**
     * checking if the type is the correct type
     * @param varType the type we want to check
     * @return true if its not the same type false otherwise
     */
    public boolean checkType(String varType) { return super.checkType(varType) && !varType.equals(INT_TYPE);}
}