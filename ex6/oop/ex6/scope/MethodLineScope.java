package oop.ex6.scope;

import oop.ex6.ex6Exception.*;
import oop.ex6.methods.Method;
import oop.ex6.regex.Regex;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariablesFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  a class that's handle and store all of the method initializer lines
 *  extends Scope
 */
public class MethodLineScope extends Scope {
    private static final String PARM = Regex.LEGAL_METHOD, VOID_TYPE = Regex.VOID_TYPE;
    private Method method;

    /**
     * a constructor
     * @param outerVars a map of all of the vars in a scope
     * @param innerMethods a map of all of the method is a scope
     */
    public MethodLineScope(Map<String, Variable> outerVars, Map<String, Method> innerMethods){
        super(new HashMap<>(), outerVars, innerMethods);
        this.pattern = Regex.METHOD_INITIALIZER_REGEX;
    }

    /**
     * Analyzes the line and checks whether or not the line is legal according to the text.
     * @param line - the line to analyze
     * @return true for a good line false for a bad one
     * @throws Ex6Exception all the relevant exceptions
     */
    public boolean lineAnalyzer(String line) throws Ex6Exception {
        Matcher matcher = (Pattern.compile(this.pattern)).matcher(line);
        if (!matcher.matches()){return false;}
        else {
            String methodType = matcher.group(2);
            String methodName = matcher.group(3);
            String methodVars = matcher.group(4);
            Object outerVars = null;
            if (!methodType.matches(VOID_TYPE)) {
                throw new Ex6Exception(String.format(Messages.METHOD_RETURN_TYPE_UNVALID, methodName));
            }
            else {
                List<Variable> bracketVars = bracketBreaking(methodVars);
                this.method = new Method(methodName, (Variable)outerVars, bracketVars);
                return true;}}
    }

    /*
     * This method allows you to get a string representation of an expression
     * that originally was inside of brackets in some expression.
     * @param line the method line
     * @return a link list with all the vars in the bracket
     * @throws ex6Exception all the relevant exceptions
     */
    private List<Variable> bracketBreaking(String line) throws Ex6Exception {
        LinkedList<Variable> bracketVars = new LinkedList<>();
        if (line.matches(Regex.BLANK_LINE)) {return bracketVars;}
        else {
            String[] brackets = line.split(Regex.SEPARATOR);
            for (String v : brackets) {
                Matcher matcher = (Pattern.compile(PARM)).matcher(v);
                if (!matcher.matches()) {
                    throw new Ex6Exception(String.format(Messages.METHOD_PARAM_IS_UNVALID, v));}
                String varType =  matcher.group(2);
                String varName = matcher.group(3);
                boolean initialized = matcher.group(1) != null;
                Variable var = VariablesFactory.createType(varType, varName, initialized);
                if (var == null){throw new Ex6Exception(String.format(Messages.TYPE_IS_NOT_VALID, varName));}
                var.setInitialized();
                bracketVars.add(var);}
            return bracketVars;
        }
    }

    /**
     * get the relevant method
     * @return the method
     */
    public Method getMethod() {return this.method;}
}