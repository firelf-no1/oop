package oop.ex6.scope;

import oop.ex6.ex6Exception.Ex6Exception;
import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.Variable;

import java.util.Map;

/**
 * This class is a father class for all other scope types in the program. This
 * class is an abstract class
 * This class represents and holds all the joint properties of all scopes in
 * the program.
 */
public abstract class Scope {
    String pattern;
    final String  VAR_INITIALIZE = Regex.VAR_INITIALIZE_LINE;
    Map<String, Variable> innerVars, outerVars;
    protected Map<String, Method> innerMethods;

    /**
     * a constructor
     * @param innerVars a map of all of the vars in a a method
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     */
    public Scope(Map<String,Variable>innerVars,Map<String,Variable>outerVars,Map<String,Method>innerMethods){
        this.innerVars = innerVars;
        this.outerVars = outerVars;
        this.innerMethods = innerMethods;
    }

    /**
     * Analyzes the line if it is a operation line that is  Variable related,
     * or method call related. this method checks exactly what type of line is
     * it and checks whether or not the line is legal according to the text.
     * @param line - the line to analyze
     * @return true for a good line false for a bad one
     * @throws Ex6Exception all the relevant exceptions
     */
    public boolean lineAnalyzer(String line) throws Ex6Exception {return line.matches(this.pattern);}
}