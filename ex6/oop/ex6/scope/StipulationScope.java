package oop.ex6.scope;

import oop.ex6.ex6Exception.Ex6Exception;
import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.BooleanVariable;
import oop.ex6.variables.Variable;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * extends scope
 * represents a stipulation scope (if/while) abstract class
 */
public abstract class StipulationScope extends Scope {
    private final static BooleanVariable test = new BooleanVariable("test", true);

    /**
     * a constructor
     * @param innerVars a map of all of the vars in a a method
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     */
    StipulationScope(Map<String, Variable> innerVars, Map<String, Variable> outerVars,
                     Map<String, Method> innerMethods){
        super(innerVars, outerVars, innerMethods);
    }

    /**
     * Analyzes the line and checks whether or not the line is legal according to the text.
     * @param line - the line to analyze
     * @return true for a good line false for a bad one
     * @throws Ex6Exception all the relevant exceptions
     */
    public boolean lineAnalyzer(String line) throws Ex6Exception {
        Matcher matcher = Pattern.compile(this.pattern).matcher(line);
        if (!matcher.matches()){return false;}
        else {
            String group = matcher.group(1);
            String[] vars = group.split(Regex.SPLIT);
            InnerBractVars innerVars = new InnerBractVars(this.innerVars, this.outerVars, this.innerMethods,
                    test);
            for (String var : vars) {if (!innerVars.lineAnalyzer(var.trim())){return false;}}
            return true;
        }
    }
}