package oop.ex6.variables;

/**
 * an abstract class that hold all of the data about the variable except the name
 * extends Variables
 */
public abstract class Variable extends Variables {
    private final String varType;
    private final String varRegex;
    private final boolean finalDeclared;
    private boolean initialized;

    /**
     * a constructor
     * @param varName the name of the variable
     * @param finalDeclared is the file declared final
     * @param varType the type of the variable
     * @param varRegex the regex of the variable
     */
    public Variable(String varName, boolean finalDeclared, String varType, String varRegex){
        super(varName);
        this.finalDeclared = finalDeclared;
        this.varType = varType;
        this.varRegex = varRegex;
        this.initialized = false;
    }

    /**
     * Getter
     * @return the type of this variable
     */
    public String getVarType() {return this.varType;}

    /**
     * Getter
     * @return true is the file is final false otherwise
     */
    public boolean isFinalDeclared() {return this.finalDeclared;}

    /**
     * set the is initialized condition.
     */
    public void setInitialized() {this.initialized = true;}

    /**
     * Getter
     * @return if this variable is initialized or not.
     */
    public boolean isInitialized(){return this.initialized;}

    /**
     * checking the regex
     * @param varRegex the string we want to check
     * @return true if its equal false otherwise
     */
    public boolean checkRegex(String varRegex){return varRegex.matches(this.varRegex);}

    /**
     * checking if the type is the correct type
     * @param varType the type we want to check
     * @return true if its not the same type false otherwise
     */
    public boolean checkType(String varType){return !this.varType.equals(varType);}
}