package oop.ex6.scope;

import oop.ex6.ex6Exception.*;
import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.Variable;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * extends scope
 * represents a method call line  scope
 */
public class MethodCallScope extends Scope{
    private Method method;

    /**
     * a constructor
     * @param innerVars a map of all of the vars in a a method
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     */
    public MethodCallScope(Map<String, Variable> innerVars, Map<String, Variable> outerVars,
                           Map<String, Method> innerMethods){
        super(innerVars, outerVars, innerMethods);
        this.pattern = Regex.METHOD_CALL;
    }

    /**
     * get the method
     * @return this method
     */
    public Method getMethod() {return this.method;}

    /**
     * Analyzes the line and checks whether or not the line is legal according to the text.
     * @param line - the line to analyze
     * @return true for a good line false for a bad one
     * @throws Ex6Exception all the relevant exceptions
     */
    public boolean lineAnalyzer(String line) throws Ex6Exception{
        Matcher matcher = (Pattern.compile(this.pattern)).matcher(line);
        if (!matcher.matches()){ return false;}
        else{
            String methodName = matcher.group(1);
            String bracket = matcher.group(2);
            if (!this.innerMethods.containsKey(methodName)){
                throw new Ex6Exception(String.format(Messages.METHOD_CALLING_TO_UNKNOWN, methodName));}
            else {
                Method testMethod = this.innerMethods.get(methodName);
                List varList = testMethod.getMethodVar();
                if (varList.size() == 0){
                    if (!bracket.matches(Regex.BLANK_LINE)) {
                        throw new Ex6Exception(String.format(Messages.PARAM_UNEXPECTED_SIZE, methodName,
                                0, varList.size()));}}
                else {
                    if (bracket.matches(Regex.BLANK_LINE)) {
                        throw new Ex6Exception(String.format(Messages.PARAM_UNEXPECTED_SIZE, methodName,
                                0, varList.size()));}
                    int start = 0, end = 0;
                    Matcher tmpMatcher = (Pattern.compile(Regex.SEPARATOR)).matcher(bracket);
                    while (true) {
                        if (end >= varList.size()) {
                            String tmp = bracket.substring(start);
                            if (!tmp.matches(Regex.BLANK_LINE) || bracket.substring(start - 1, start).
                                    equals(Regex.COMMA)) {
                                throw new Ex6Exception(String.format(Messages.METHOD_ILLEGAL_LINE_ARG,
                                        bracket,testMethod.getVarName()));}
                            break;}
                        Variable variable = (Variable)varList.get(end);
                        InnerBractVars innerBractVars = new InnerBractVars(this.innerVars, this.outerVars,
                                this.innerMethods, variable);
                        boolean initialize = false;
                        while (true) {
                            if (tmpMatcher.find()) {
                                String tmpLine = bracket.substring(start, tmpMatcher.start());
                                if (!innerBractVars.lineAnalyzer(tmpLine)) {continue;}
                                initialize = true;
                                start = tmpMatcher.end();}
                            if (!initialize) {return false;}
                            end++;
                            break;}}}
                this.method = testMethod;
                return true;}}
    }
}