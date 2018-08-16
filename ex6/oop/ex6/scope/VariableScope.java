package oop.ex6.scope;

import oop.ex6.ex6Exception.Ex6Exception;
import oop.ex6.ex6Exception.Messages;
import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariablesFactory;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * extends scope
 * represents a 'variable' scope
 */
public class VariableScope extends Scope{
    private Variable var;
    private String newPattern;

    /**
     * a constructor
     * @param innerVars a map of all of the vars in a a method
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     */
    public VariableScope(Map<String, Variable> innerVars, Map<String, Variable> outerVars,
                         Map<String, Method> innerMethods){
        super(innerVars, outerVars, innerMethods);
        this.pattern = Regex.VAR_INITIALIZED1;
        this.newPattern = Regex.VAR_INITIALIZED2;
    }

    /**
     * get this var
     * @return this variable
     */
    public Variable getVar(){return this.var;}

    /**
     * Analyzes the line and checks whether or not the line is legal according to the text.
     * @param line - the line to analyze
     * @param outerVars a map of all of the vars in a scope
     * @param initialized - true if the var has a value false otherwise
     * @return true for a good line false for a bad one
     * @throws Ex6Exception all the relevant exceptions
     */
    public boolean lineAnalyzer(String line, Map<String, Variable> outerVars, boolean initialized)
            throws Ex6Exception {
        String[] braceVars = line.split(Regex.COMMA);
        String tmpVar = null;
        boolean varFinal = false;
        Matcher matcher ;
        for (int i = 0; i < braceVars.length;i++) {
            if (i == braceVars.length - 1) {
                matcher = Pattern.compile(this.pattern).matcher(braceVars[i]);
                if (!matcher.matches()) {return false;}}
            else {
                matcher = Pattern.compile(this.newPattern).matcher(braceVars[i]);
                if (!matcher.matches()) {return false;}}
            String varType = matcher.group(2);
            if (i == 0) {
                varFinal = matcher.group(1) != null;
                tmpVar = varType;}
            else {
                if (varType != null) {
                    throw new Ex6Exception(String.format(Messages.LINE_DECLARE_VIOLATION, line));}
                varType = tmpVar;}
            Matcher tmpMatcher = (Pattern.compile(VAR_INITIALIZE)).matcher(matcher.group(3));
            if (!tmpMatcher.matches()) {
                throw new Ex6Exception(String.format(Messages.VAR_TASK_NOT_LEGAL, matcher.group(3)));}
            String varName = tmpMatcher.group(1), varValue = tmpMatcher.group(2);
            if (varType != null){
                this.var = VariablesFactory.createType(varType, varName, varFinal);
                if (this.var == null){return false;}
                if (outerVars.containsKey(varName)){
                    throw new Ex6Exception(String.format(Messages.VAR_DOUBLE, varName));}
                if (varFinal && varValue == null){
                    throw new Ex6Exception(String.format(Messages.VAR_USE_OF_FINAL_ON_UNINITIALIZED,
                            varName));}
                this.innerVars.put(varName, this.var);
                outerVars.put(varName, this.var);}
            else {
                if (varValue == null && i ==0){ return false;}
                if (!this.innerVars.containsKey(varName)){
                    if (initialized){throw new Ex6Exception(String.format(Messages.LINE_NOT_LEGAL_MEMBER,line));}
                    throw new Ex6Exception(String.format(Messages.VAR_UNINITIALIZED, varName));}
                if (i != 0){throw new Ex6Exception(String.format(Messages.VAR_DOUBLE, varName));}
                this.var = this.innerVars.get(varName);
                if (this.var.isFinalDeclared()){
                    throw new Ex6Exception(String.format(Messages.VAR_FINAL_NOT_LEGAL_USE, varName));}}
            if (varValue != null) {
                InnerBractVars innerBractVars = new InnerBractVars(this.innerVars, this.outerVars,
                        this.innerMethods, this.var);
                if (!innerBractVars.lineAnalyzer(varValue)) {
                    throw new Ex6Exception(String.format(Messages.VAR_VALUE_TYPE_UNMATCHED, varType,
                            varValue));}
                this.var.setInitialized();}}
        return true;
    }
}