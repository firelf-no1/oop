package oop.ex6.variables;

import oop.ex6.regex.Regex;

/**a class that holds all of the int variables*/
class IntVariable extends IntVar {

    /**
     * a constructor
     * @param varName the name of the variable
     * @param varFinal is the file declared final
     */
    IntVariable(String varName, boolean varFinal){super(varName, varFinal, Regex.TYPE_INT, Regex.INPUT_INT);}
}
