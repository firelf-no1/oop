
/**
 * This class represents a library patron that has a name and assigns values to different literary aspects
 * of books.
 */
public class Patron extends Object {

    /* The first and last name of the patron. */
    private String patronFirstName, patronLastName;

    /* The weight the patron assigns to aspects of books. and The minimal literary value a book must have for this
    patron to enjoy it. also a counter for the book that where taken and a counter fot the books that was recommended*/
    private int comicTendency, dramaticTendency, educationalTendency, patronEnjoymentThreshold, booksTakenCounter = 0,
            booksRecommendedCounter = 0;
    /*an array for the books the patron has taken and an array for the books the patron has been recommended on*/
    private Book[] booksTaken, booksRecommended;

    /**
     * Creates a new patron with the given characteristics.
     * @param patronFirstName - The first name of the patron
     * @param patronLastName - The last name of the patron.
     * @param comicTendency - The weight the patron assigns to the comic aspects of books.
     * @param dramaticTendency - The weight the patron assigns to the dramatic aspects of books.
     * @param educationalTendency - The weight the patron assigns to the educational aspects of books.
     * @param patronEnjoymentThreshold - The minimal literary value a book must have for this patron to enjoy it.
     */
    public Patron(String patronFirstName, String patronLastName, int comicTendency,
                  int dramaticTendency, int educationalTendency, int patronEnjoymentThreshold) {
        this.patronFirstName = patronFirstName;
        this.patronLastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        this.patronEnjoymentThreshold = patronEnjoymentThreshold;
    }

    /**
     * Returns a string representation of the patron, which is a sequence of its first and last name,
     * separated by a single white space.
     * @return the String representation of this patron.
     */
    String stringRepresentation() { return this.getPatronFirstName() + " " + this.getPatronLastName(); }

    /**
     * Calculate the literary value this patron assigns to the given book.
     * @param book The book to asses.
     * @return the literary value this patron assigns to the given book.
     */
    int getBookScore(Book book) {
        return ((this.getComicTendency() * book.getComicValue()) + (this.getDramaticTendency() *
                book.getDramaticValue()) + (this.getEducationalTendency() * book.getEducationalValue()));
    }


    /**
     * Checks if this patron will enjoy the given book, false otherwise.
     * @param book The book to asses.
     * @return true if this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book) { return (this.getBookScore(book) > this.getPatronEnjoymentThreshold()); }

    /**
     * @return the First Name of this patron.
     */
    String getPatronFirstName() { return this.patronFirstName; }

    /**
     * @return the Last Name of this patron.
     */
    String getPatronLastName() { return this.patronLastName; }

    /**
     * @return the weight the patron assigns to the comic aspects of books.
     */
    int getComicTendency() { return this.comicTendency; }

    /**
     * @return the weight the patron assigns to the dramatic aspects of books.
     */
    int getDramaticTendency() { return this.dramaticTendency; }

    /**
     * @return the weight the patron assigns to the educational aspects of books.
     */
    int getEducationalTendency() { return this.educationalTendency; }

    /**
     * @return the minimal literary value a book must have for this patron to enjoy it.
     */
    int getPatronEnjoymentThreshold() { return this.patronEnjoymentThreshold; }

    /**
     * an array with all the books this patron was suggested.
     * @param book a book we want to store.
     */
    void setBooksRecommended(Book book) {
        if (!bookChecker(book, booksRecommended, booksRecommendedCounter)){
            this.booksRecommended[booksRecommendedCounter] = book;
            booksRecommendedCounter++;
        }
    }

    /**
     * an array with all the books this patron has taken.
     * @param book a book the patron took.
     */
    void setBooksTaken(Book book) {
        if (!bookChecker(book, booksTaken, booksTakenCounter)) {
            this.booksTaken[booksTakenCounter] = book;
            booksTakenCounter++;
        }
    }

    /**
     * checking if a book is in a given array.
     * @param book the book we are looking for.
     * @param arr the array we are looking in.
     * @param counter the counter of the given array so we will know when to stop.
     * @return true if the book is in the array false otherwise.
     */
    boolean bookChecker(Book book, Book[] arr, int counter){
        for (int i = 0; i < counter; i++) { if (arr[i].equals(book)){ return true; } }
        return false;
    }

    /**
     * @return the array with all the books this patron was suggested.
     */
    Book[] getBooksRecommended() { return this.booksRecommended; }

    /**
     * @return the array with all the books this patron as taken.
     */
    Book[] getBooksTaken() { return this.booksTaken; }

    /**
     * configuring the array to the right size out of the assumption that two libraries won't have the same books.
     * @param number the new max books variable.
     */
    void initialiseBooksTaken(int number){
        if (booksTaken == null){ booksTaken = new Book[number]; }
        else{
            Book[] testArray = new Book[booksTaken.length + number];
            System.arraycopy(booksTaken,0, testArray,0, booksTaken.length);
            booksTaken = testArray;
            }
    }

     /**
     * configuring the array to the right size out of the assumption that two libraries won't have the same books.
     * @param number the new max books variable.
     */
    void initialiseBooksRecommended(int number){
        if (booksRecommended == null){ booksRecommended = new Book[number]; }
        else{
            Book[] testArray = new Book[booksRecommended.length + number];
            System.arraycopy(booksRecommended,0, testArray,0, booksRecommended.length);
            booksRecommended = testArray;
        }
    }

}