package oop.ex6.ex6Exception;

/**a class that's stores all of the ex6 exception messages*/
public class Messages {
    public static final String
    // Un-valid s-java file or arguments length
    ARGUMENT_LENGTH_IS_NOT_VALID = "The arguments length is not valid (different than 1)",
    FILE_IS_NOT_VALID = "The file is not valid s-java file",
    // Line Violations messages
    LINE_NOT_LEGAL = "Line '%h' is not legal",
    LINE_NOT_LEGAL_MEMBER ="The line'%h' was found to be not legal line",
    LINE_DECLARE_VIOLATION = "Declaration violation in line %h, attempts to declare more than once",
    // Variable, parameters and types Violations messages
    TYPE_IS_NOT_VALID ="The type %h is not a valid type",
    VAR_UNINITIALIZED_VIOLATION = "Attempts to apply uninitialized variable %h",
    VAR_VALUE_TYPE_UNMATCHED = "Value - '%h' unmatched for Type - %h",
    VAR_FINAL_NOT_LEGAL_USE = "Attempts to set the variable %h even though it's final",
    VAR_UNINITIALIZED = "var %h was called, although it was not initialized",
    VAR_USE_OF_FINAL_ON_UNINITIALIZED = "Attempts to use final on %h which isn't initialized variable",
    VAR_UNEXPECTED_TYPE = "Actual type of the variable %h is %h. Although the expected type was %h",
    VAR_DOUBLE = "Variable %h was defined more than once",
    VAR_TASK_NOT_LEGAL = "The variable task %h wasn't legal",
    PARAM_ILLEGAL_TYPE = "The param %h is not legal use of %h type",
    PARAM_DOUBLE = "The name %h have been found more than once in method %h ",
    PARAM_UNEXPECTED_SIZE = "Expected - %h received %h parameters. Actual - received '%h' parameters",
    // Method Violations messages
    METHOD_NOT_LEGAL_LINE = "The line '%h' found to be illegal",
    METHOD_ARG_IS_UNVALID = "Method's %h arguments are not valid (at least one)",
    METHOD_IS_OPEN = "The method %h in the code found to be open",
    METHOD_ILLEGAL_LINE_ARG = "Illegal argument line '%h', in method %h",
    METHOD_CALLING_TO_UNKNOWN = "A try for calling unknown method %h has found in the code",
    METHOD_ILLEGAL_RETURN_TYPE = "The type method %h returns is %h, but it should have been %h",
    METHOD_PARAM_IS_UNVALID = "Method param '%h' is un valid",
    METHOD_DOUBLE ="Method %h was defined more than once",
    METHOD_VOID_RETURN_TYPE_VIOLATION = "Method had a void return type, but unexpectedly returned '%h'",
    METHOD_NOT_SUITABLE_RETURN = "The type of the value returned by method %h wasn't suitable",
    METHOD_RETURN_NOT_FOUND = "%h - does not ends with return.",
    METHOD_RETURN_TYPE_UNVALID = "The method %h return type isn't void",
    // scope violation return
    SCOPE_IS_OPEN = "%h scope wasn't closed";
}


