package oop.ex6.main;

import oop.ex6.ex6Exception.Ex6Exception;
import oop.ex6.ex6Exception.Messages;
import oop.ex6.parser.Parser;

import java.io.IOException;

/**
 * This class is used to run the compiler, prints 0,1 or 2 accordingly.
 */
public class Sjavac {

    // Constants

    private static final String FILE_END = ".sjava";
    private final static int SUCCESS = 0, FILE_INDEX = 0, ILLEGAL_CODE = 1 ,VALID_LENGTH = 1, IO_ERROR = 2;

    /**
     * Default constructor
     */
    public Sjavac() {}

    /**
     * Method used before running the compiler, to make sure the compiler receives one s-java file to check
     * @param args File's data
     * @throws Ex6Exception Will be thrown for wrong argument length, or non s-java file.
     */
    private static void lengthCheck(String[] args) throws Ex6Exception {
        if (args.length != VALID_LENGTH) throw new Ex6Exception(Messages.ARGUMENT_LENGTH_IS_NOT_VALID);
        else{if (!args[FILE_INDEX].endsWith(FILE_END)) throw new Ex6Exception(Messages.FILE_IS_NOT_VALID);}
    }

    /**
     * Method is used for running the lengthCheck and compiler, and print the suitable results.
     * @param args File's data
     */
    public static void main(String[] args) {
        try {
            lengthCheck(args);
            Parser parser = new Parser(args[FILE_INDEX]);
            try {parser.fileAnalyzer();}
            catch (IOException e) {
                System.out.println(IO_ERROR); // for un-valid file, prints 2
                return;
            }
        }
        catch (Ex6Exception f) {
            System.out.println(ILLEGAL_CODE); // If the s-java file format is not valid, prints 1
            f.exceptionPrint();
            return;
        }
        System.out.println(SUCCESS); // If no exceptions were thrown by compiler, prints 0
    }
}