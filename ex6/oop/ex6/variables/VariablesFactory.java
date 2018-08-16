package oop.ex6.variables;

import oop.ex6.ex6Exception.*;
import oop.ex6.regex.Regex;

/**a factory that generate the correct var*/
public class VariablesFactory {

    /**
     * a constructor
     */
    public VariablesFactory(){}

    /**
     * checks which type an expression matches and return the initialized var
     * @param varType the type of the variable
     * @param varName the name of the variable
     * @param initialized does the file have a value
     * @return the initialized var
     * @throws Ex6Exception if the file ir illegal
     */
    public static Variable createType(String varType,String varName,boolean initialized) throws Ex6Exception{
        Object tempVar = null;
        if (varType.matches(Regex.TYPE)){
            if(varType.matches(Regex.TYPE_INT)){tempVar = new IntVariable(varName, initialized);}
            else{if(varType.matches(Regex.TYPE_BOOLEAN)){tempVar = new BooleanVariable(varName, initialized);}
            else{if(varType.matches(Regex.CHAR_TYPE)){tempVar = new CharVariable(varName, initialized);}
            else{if(varType.matches(Regex.TYPE_DOUBLE)){tempVar = new DoubleVariable(varName, initialized);}
            else{if(varType.matches(Regex.STRING_TYPE)){tempVar = new StringVariable(varName, initialized);}
        }}}}}
        else {throw new Ex6Exception(String.format(Messages.TYPE_IS_NOT_VALID, varType.trim()));}
        return (Variable)tempVar;
    }
}