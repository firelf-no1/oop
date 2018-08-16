package oop.ex6.scope;

import oop.ex6.ex6Exception.*;
import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.Variable;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a class that's hold all of the return scopes
 * extends scope
 */
public class ReturnScope extends Scope {
    private final Method method;

    /**
     * a constructor
     * @param innerVars a map of all of the vars in a a method
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     * @param method the method the return line is in
     */
    public ReturnScope(Map<String, Variable> innerVars, Map<String, Variable> outerVars,
                       Map<String, Method> innerMethods, Method method){
        super(innerVars, outerVars, innerMethods);
        this.method = method;
        this.pattern = Regex.TYPE_RETURN;
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
            String varLine = matcher.group(1);
            if(varLine!= null && varLine.length() !=0){
                InnerBractVars innerBractVars = new InnerBractVars(this.innerVars, this.outerVars,
                        this.innerMethods, this.method.getMethodReturn());
                if(!innerBractVars.lineAnalyzer(varLine)){
                    throw new Ex6Exception(String.format(Messages.METHOD_NOT_SUITABLE_RETURN,
                            this.method.getVarName()));}}
            return true;}
    }
}