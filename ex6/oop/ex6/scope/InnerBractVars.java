package oop.ex6.scope;

import oop.ex6.ex6Exception.*;
import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.Variable;

import java.util.Map;

/**
 * a class that holds and menage all the variables that are in a method bract
 * extends scope
 */
public class InnerBractVars extends Scope {
    private final Variable var;

    /**
     * a constructor
     * @param innerVars a map of all of the vars in a a method
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     * @param var the var we want to store
     */
    InnerBractVars(Map<String, Variable> innerVars, Map<String, Variable> outerVars, Map<String, Method>
            innerMethods, Variable var){
        super(innerVars, outerVars, innerMethods);
        this.var = var;
    }

    /**
     * Analyzes the line and checks whether or not the line is legal according to the text.
     * @param line - the line to analyze
     * @return true for a good line false for a bad one
     * @throws Ex6Exception all the relevant exceptions
     */
    public boolean lineAnalyzer(String line) throws Ex6Exception {
        if(this.var == null){
            if (line.matches(Regex.BLANK_LINE)){return true;}
            else {throw new Ex6Exception(String.format(Messages.METHOD_VOID_RETURN_TYPE_VIOLATION, line));}}
        else{
            if (this.var.checkRegex(line)){return true;}
            else {
                Variable tmpVar;
                if((tmpVar = this.innerVars.get(line)) != null){
                    if (this.var.checkType(tmpVar.getVarType())){
                        throw new Ex6Exception(String.format(Messages.VAR_UNEXPECTED_TYPE,
                                tmpVar.getVarName(), tmpVar.getVarType(), this.var.getVarName()));
                    }
                    if (!tmpVar.isInitialized()){
                        throw new Ex6Exception(String.format(Messages.VAR_UNINITIALIZED_VIOLATION, line));}}
                else{
                    MethodCallScope method = new MethodCallScope(this.innerVars,  this.outerVars,
                            this.innerMethods);
                    if (!method.lineAnalyzer(line)){return false;}
                    if (method.getMethod().getMethodReturn() == null){
                        throw new Ex6Exception(String.format(Messages.PARAM_ILLEGAL_TYPE,
                                method.getMethod().getVarName(), this.var.getVarType()));}
                    if (this.var.checkType(method.getMethod().getMethodReturn().getVarType())){
                        throw new Ex6Exception(String.format(Messages.METHOD_ILLEGAL_RETURN_TYPE,
                                method.getMethod().getVarName(), method.getMethod().getMethodReturn()
                                        .getVarType(), this.var.getVarType()));}}
                return true;
            }
        }
    }
}