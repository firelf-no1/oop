package oop.ex6.parser;

import oop.ex6.ex6Exception.*;
import oop.ex6.methods.*;
import oop.ex6.regex.Regex;
import oop.ex6.scope.*;
import oop.ex6.variables.Variable;

import java.io.*;
import java.util.*;

/**
 * scanning the code line by line, create suitable objects (methods,
 * scopes, variables) and check if it's a valid code
 */
public class Parser {
    private String fileName;
    private Map<String, Variable> outerVars = new HashMap<>();
    private Map<String, Method> innerMethods = new HashMap<>();
    private int blockLines;

    /**
     * holds the file name
     * @param fileName the file name
     */
    public Parser(String fileName){this.fileName = fileName;}

    /**
     * analize the full file
     * @throws Ex6Exception if the file is illegal
     * @throws IOException if there is no file
     */
    public void fileAnalyzer() throws Ex6Exception, IOException {
        this.readFile();
        for (String s : this.innerMethods.keySet()) {this.methodAnalyzer(this.innerMethods.get(s));}
    }

    /*
     * Checks if the method called in the line exists in the class and if it
     * called properly. it checks if the input variable in the call is the same as
     * the method declaration.
     * @param method
     * @throws Ex6Exception
     */
    private void methodAnalyzer(Method method) throws Ex6Exception{
        Iterator<Line> iter = method.methodLinesIterator();
        List<Variable> vars = method.getMethodVar();
        HashMap<String, Variable> varsTry = new HashMap<>(this.outerVars), testMap = new HashMap<>();
        Boolean returnChecker = false;
        for (Variable var : vars) {
            if (testMap.containsKey(var.getVarName())){
                throw new Ex6Exception(String.format(Messages.PARAM_DOUBLE, var.getVarName(),
                        method.getVarName()));}
            testMap.put(var.getVarName(), var);}
        varsTry.putAll(testMap);
        while (iter.hasNext()){
            Line line = iter.next();
            if (line.getLineString().matches(Regex.ENDS_WITH_SEMICOLON)){
                returnChecker = this.singleLineAnalise(line.getLineString(), testMap, varsTry, method);}
            else{
                if (line.getLineString().matches(Regex.ENDS_WITH_OPEN_BRACKET)){
                    this.funcAnalyzer(line, iter, new HashMap<>(varsTry), method);
                    returnChecker = false;}}}
        if (!returnChecker){
            throw new Ex6Exception(String.format(Messages.METHOD_RETURN_NOT_FOUND, method.getVarName()));}
    }

    /*
     * checks if the line is a if/while line
     * @param line the line we need to check
     * @param iter the method line iterator
     * @param vars all of the vars in the scope
     * @param method the method we are checking it
     * @throws Ex6Exception
     */
    private void funcAnalyzer(Line line, Iterator<Line> iter, Map<String,Variable> vars, Method method)
            throws Ex6Exception{
        HashMap<String, Variable> tmpVars = new HashMap<>();
        boolean checker = line.getLineString().matches(Regex.IF_REGEX);
        if (checker){
            IfScope ifScope = new IfScope(vars, this.outerVars, this.innerMethods);
            checker = ifScope.lineAnalyzer(line.getLineString());}
        if (!checker){
            checker = line.getLineString().matches(Regex.WHILE_REGEX );
            if(checker){
                WhileScope whileScope = new WhileScope(vars, this.outerVars, this.innerMethods);
                checker = whileScope.lineAnalyzer(line.getLineString());}
            if (!checker){throw new Ex6Exception(String.format(Messages.LINE_NOT_LEGAL, line));} }
        while (iter.hasNext()){
            Line tmpLine = iter.next();
            if(tmpLine.getLineString().matches(Regex.ENDS_WITH_OPEN_BRACKET)) {
                this.funcAnalyzer(tmpLine, iter, new HashMap<>(vars), method);}
            else{
                if (tmpLine.getLineString().matches(Regex.ENDS_WITH_SEMICOLON)){
                this.singleLineAnalise(tmpLine.getLineString(),tmpVars,vars,method);}
            else {if (tmpLine.getLineString().matches(Regex.ENDS_WITH_CLOSED_BRACKET)){return;}}}}
        throw new Ex6Exception(String.format(Messages.SCOPE_IS_OPEN, method.getVarName()));
    }

    /*
     * checking which type of line was received
     * @param line the line to check
     * @param outerVars the vars in the scope
     * @param innerVars the vars in the method
     * @param method the method
     * @return true if its a return line false otherwise
     * @throws Ex6Exception if the line has a problem
     */
    private boolean singleLineAnalise(String line, Map<String, Variable> outerVars, Map<String, Variable>
            innerVars, Method method) throws Ex6Exception {
        VariableScope variableScope = new VariableScope(innerVars, this.outerVars, this.innerMethods);
        if(variableScope.lineAnalyzer(line, outerVars, false)){return false;}
        else{
            MethodCallScope methodCallScope=new MethodCallScope(innerVars,this.outerVars,this.innerMethods);
            if (methodCallScope.lineAnalyzer(line)){return false;}
            else{
                ReturnScope returnScope = new ReturnScope(innerVars,this.outerVars,this.innerMethods,method);
                if (returnScope.lineAnalyzer(line)){return true;}
                throw new Ex6Exception(String.format(Messages.METHOD_ARG_IS_UNVALID, line));}}
    }

    /*
     * gating the file and analyzing it
     * @throws Ex6Exception if the file has a problem
     * @throws IOException if the file does not exist
     */
    private void readFile() throws Ex6Exception, IOException {
        File file = new File(fileName);
        ArrayList<String> scope = fileToList(file);
        Method innerBlock = null;
        LinkedList<String> scopeVars = new LinkedList<>();
        LinkedList<String> emptyList = new LinkedList<>();
        this.blockLines = 0;
        for (String tmpLine : scope) {
            String thisLine = tmpLine.trim();
            if (this.blockLines > 0) {innerBlock = outerLineAnalyzer(innerBlock, thisLine);}
            else{
                if (thisLine.matches(Regex.ENDS_WITH_SEMICOLON)) { scopeVars.add(thisLine); }
                else {innerBlock = methodLineAnalyzer(innerBlock, thisLine);}}}
        if (this.blockLines > 0) {
            assert innerBlock != null;
            throw new Ex6Exception(String.format(Messages.METHOD_IS_OPEN, innerBlock.getVarName()));}
        else { iteratorHelper(scopeVars, emptyList);}
    }

    /*
     * analise the method initializer line
     * @param innerBlock the block we are in
     * @param thisLine the method call line
     * @return the inner block
     * @throws Ex6Exception if the line is illegal
     */
    private Method methodLineAnalyzer(Method innerBlock, String thisLine) throws Ex6Exception{
        if (thisLine.matches(Regex.ENDS_WITH_OPEN_BRACKET)) {
            MethodLineScope method = new MethodLineScope(this.outerVars, this.innerMethods);
            if (!method.lineAnalyzer(thisLine)) {
                throw new Ex6Exception(String.format(Messages.METHOD_NOT_LEGAL_LINE, thisLine));}
            Method testMethod = method.getMethod();
            innerBlock = testMethod;
            if (this.innerMethods.containsKey(testMethod.getVarName())) {
                throw new Ex6Exception(String.format(Messages.METHOD_DOUBLE,
                        testMethod.getVarName()));}
            this.innerMethods.put(testMethod.getVarName(), testMethod);
            this.blockLines++;}
        else {if (!thisLine.matches(Regex.BLANK_LINE)){
            throw new Ex6Exception(String.format(Messages.LINE_NOT_LEGAL, thisLine));}}
        return innerBlock;
    }

    /*
     * organizing the lines of the code by there blocks
     * @param innerBlock the block we are in
     * @param thisLine the line we are checking
     * @return the inner block
     * @throws Ex6Exception if the line is illegal
     */
    private Method outerLineAnalyzer(Method innerBlock, String thisLine) throws Ex6Exception{
        if (thisLine.matches(Regex.ENDS_WITH_OPEN_BRACKET)) {
            innerBlock.addLines(thisLine);
            this.blockLines++;}
        else {
            if (thisLine.matches(Regex.ENDS_WITH_SEMICOLON)) {innerBlock.addLines(thisLine);}
            else {
                if (thisLine.matches(Regex.ENDS_WITH_CLOSED_BRACKET)) {
                    this.blockLines--;
                    if (this.blockLines > 0) {innerBlock.addLines(thisLine);}}
                else {
                    if (!thisLine.matches(Regex.BLANK_LINE)) {
                        throw new Ex6Exception(String.format(Messages.LINE_NOT_LEGAL, thisLine));}}}}
        return innerBlock;
    }

    /*
     * checks all of the vars in the scope
     * @param scopeVars the vars in the scope
     * @param emptyList a control list
     * @throws Ex6Exception if the line is illegal
     */
    private void iteratorHelper(LinkedList<String> scopeVars, LinkedList<String> emptyList)
            throws Ex6Exception{
        Iterator<String> scopeIt = scopeVars.iterator();
        String tmpLine;
        Variable variable;
        do {
            if (!scopeIt.hasNext()) {
                scopeIt = emptyList.iterator();
                MethodCallScope method;
                do {
                    if (!scopeIt.hasNext()) {return;}
                    tmpLine = scopeIt.next();
                    method = new MethodCallScope(this.outerVars, this.outerVars, this.innerMethods);}
                while (method.lineAnalyzer(tmpLine));
                throw new Ex6Exception(String.format(Messages.METHOD_ARG_IS_UNVALID, tmpLine));}
            tmpLine = scopeIt.next();
            VariableScope var = new VariableScope(this.outerVars, this.outerVars, this.innerMethods);
            if (!var.lineAnalyzer(tmpLine, this.outerVars, true)) {
                throw new Ex6Exception(String.format(Messages.LINE_NOT_LEGAL_MEMBER, tmpLine));}
            variable = var.getVar();}
        while (variable != null);
        throw new Ex6Exception(String.format(Messages.LINE_NOT_LEGAL_MEMBER, tmpLine));
    }

    /*
     *a simple helper to read the given file and organize it in an array
     * @param file the file we want to read
     * @return an array of the lines that are not comment or blank
     * @throws Ex6Exception if it finds an illegal line
     * @throws IOException if the file dose not excise
     */
    private static ArrayList<String> fileToList(File file) throws Ex6Exception, IOException {
        ArrayList<String> lineInFile = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNextLine()){
            String thisLine = s.nextLine();
            if (isLineBlank(thisLine)){lineInFile.add(thisLine);}}
        s.close();
        return lineInFile;
    }

    /*
     * checking if it's a legal line or if it's not a blank line or a comment
     * @param line the line we want to check
     * @return true if its a legal line false aether
     * @throws Ex6Exception all the relevant exceptions
     */
    private static boolean isLineBlank(String line) throws Ex6Exception{
        if (line.matches(Regex.ILLEGAL_COMMENT) && (!line.matches(Regex.COMMENT))) {throw new Ex6Exception
                (String.format(Messages.LINE_NOT_LEGAL, line.trim()));}
        if (line.matches(Regex.ILLEGAL_LINE)){ throw new Ex6Exception(String.format(Messages.LINE_NOT_LEGAL,
                line.trim()));}
        else {return !line.matches(Regex.BLANK_LINE) && !line.matches(Regex.COMMENT);}
    }
}