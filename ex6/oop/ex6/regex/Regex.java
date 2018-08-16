package oop.ex6.regex;

/**holds a library of final regex strings that are relevant for Ex6*/
public class Regex {

    public static final String
    /*legal names for variables and methods*/
    TYPE="int|double|boolean|char|String",
    VAR_VALID_1 = "\\s*[-]?\\s*([_][a-zA-Z0-9]+", VAR_VALID_2 = "[a-zA-Z]+[a-zA-Z0-9]*)\\s*",
    VAR_VALID = VAR_VALID_1 + "|" + VAR_VALID_2, VAR_NAME = "(?:[a-zA-Z]+\\w*|_\\w)\\w*)",

    /* Types of variables - Literal name */
    TYPE_INT = "[ \t]*int[ \t]*", TYPE_DOUBLE = "[ \t]*double[ \t]*", STRING_TYPE = "[ \t]*String[ \t]*",
    TYPE_BOOLEAN = "[ \t]*boolean[ \t]*", CHAR_TYPE = "[ \t]*char[ \t]*",VOID_TYPE = "[ \t]*void[ \t]*",
    TYPE_RETURN = "^\\s*return\\s*(.*)\\s*;\\s*$",

    /* Input Expression that matches a value of a specific type.*/
    INPUT_INT = "[ \t]*(-)?[ \t]*[\\d]+[ \t]*", INPUT_STRING = "[ \t]*\"[^\"]*\"[ \t]*",
    INPUT_DOUBLE = "([ \t]*(-)?[ \t]*[\\d]+(\\.)([\\d]+)?[ \t]*|"+INPUT_INT+")",
    INPUT_BOOLEAN="[ \t]*(true|false|"+INPUT_INT+"|"+INPUT_DOUBLE+")[ \t]*", INPUT_CHAR="[ \t]*'[^']'[ \t]*",

    /*general line types*/
    ENDS_WITH_CLOSED_BRACKET = "^(.*\\}\\s*)$", SPLIT = "\\s*&&|\\|\\|+", ENDS_WITH_SEMICOLON = "^(.*;\\s*)$",
    REGEX_END = "\\s*\\(+\\s*(.*?)\\s*\\)+\\s*\\{\\s*$", ENDS_WITH_OPEN_BRACKET = "^(.*\\{\\s*)$",
    FINAL_LINE = "\\s*(final+\\s\\s*)?([a-zA-Z]+\\w*)\\s\\s*", IF_REGEX = "^\\s*if"+REGEX_END,
    LEGAL_METHOD= "^"+FINAL_LINE+"("+VAR_NAME+"$",WHILE_REGEX= "^\\s*while"+REGEX_END, COMMENT = "^\\/\\/+.*$",
    ENDS_WITH_EQUAL = "\\s*(?:=\\s*([^;+]*?)\\s*)?", SEPARATOR = "\\s*,+\\s*|$", ILLEGAL_LINE = "^[ ]+\\S+$",
    VAR_INITIALIZE_LINE = "^\\s*"+ VAR_VALID + ENDS_WITH_EQUAL+"$", COMMA = ",", ILLEGAL_COMMENT = "\\/+.*",
    LEGAL_VAR_INITIALIZED_LINE = "^\\s*(?:"+FINAL_LINE+")?(("+VAR_NAME+ENDS_WITH_EQUAL+")\\s*",
    METHOD_CALL = "^\\s*([a-zA-Z]+\\w*)\\s*\\(+\\s*(.*?)\\s*\\)+\\s*;+?\\s*$", BLANK_LINE = "^\\s*$",
    VAR_INITIALIZED1 = LEGAL_VAR_INITIALIZED_LINE+";\\s*$", VAR_INITIALIZED2= LEGAL_VAR_INITIALIZED_LINE+"$",
    METHOD_INITIALIZER_REGEX = "^"+FINAL_LINE+"\\s*([a-zA-Z]+\\w*)\\s*\\(\\s*([^)]*?)\\s*\\)\\s*\\{+\\s*$";
}