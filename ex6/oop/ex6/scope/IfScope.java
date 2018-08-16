package oop.ex6.scope;

import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.Variable;

import java.util.Map;

/**
 * extends StipulationScope
 * represents an 'if' scope
 */
public class IfScope extends StipulationScope {

    /**
     * a constructor
     * @param innerVars a map of all of the vars in a a method
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     */
    public IfScope(Map<String, Variable> innerVars, Map<String, Variable> outerVars, Map<String, Method>
            innerMethods){
        super(innerVars, outerVars, innerMethods);
        this.pattern = Regex.IF_REGEX;
    }
}