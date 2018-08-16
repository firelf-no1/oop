package oop.ex6.variables;

/**a abstract class that hold all of the variables names*/
public abstract class Variables {
    protected final String varName;

    /**
     * a constructor
     * @param name the name we whant to save
     */
    public Variables(String name){ this.varName = name;}

    /**
     * name geter
     * @return this var name
     */
    public String getVarName() {return this.varName;}
}