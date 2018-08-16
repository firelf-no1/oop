
import java.util.Scanner;

/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library to be able
 * to check out books, if a copy of the requested book is available.
 */
public class Library extends Object {

    private int maxBookCapacity, //The maximal number of books this library can hold.
                maxBorrowedBooks, //The maximal number of books this library allows a single patron to
                                  //borrow at the same time.
                maxPatronCapacity, //The maximal number of registered patrons this library can handle.
                currentNumberOfBooks = 0, //a marker for the books count
                currentNumberOfPatron = 0; //a marker for the patrons count

    private Book[] libraryBooks; //an array of all the library books
    private Patron[] libraryPatrons; //an array of all the library patrons
    private int[] libraryBookLending; // an array of the numbers of book each patron has at this moment
    private Scanner scan = new Scanner(System.in); // a scanner to ask the user q

    /**
     * Creates a new library with the given parameters.
     * @param maxBookCapacity The maximal number of books this library can hold.
     * @param maxBorrowedBooks The maximal number of books this library allows a single patron
     *                         to borrow at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    public Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity){
        this.maxBookCapacity = maxBookCapacity;
        this.maxBorrowedBooks = maxBorrowedBooks;
        this.maxPatronCapacity = maxPatronCapacity;
        this.libraryBooks = new Book[maxBookCapacity];
        this.libraryPatrons = new Patron[maxPatronCapacity];
        this.libraryBookLending = new int[maxPatronCapacity];
    }

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     * @param book The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book){
        if (currentNumberOfBooks == this.maxBookCapacity){ return -1; }
        else{
            int id = getBookId(book);
            if (id != -1){ return id; }
            else {
                if (book.getCurrentBorrowerId() != -1) { book.returnBook();} // if the book was in a different library
                                                                             // or if the book was removed from this
                                                                             // library and returned by the patron.
                this.libraryBooks[currentNumberOfBooks] = book;
                currentNumberOfBooks++;
                return currentNumberOfBooks - 1;
            }
        }
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        return (bookId < this.maxBookCapacity) && (bookId >= 0) && this.libraryBooks[bookId] != null;
    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     * @param book The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book){
        for (int i=0; i < this.libraryBooks.length; i++){
            if (this.libraryBooks[i] == null) { return -1;}
            else { if (this.libraryBooks[i].equals(book)){ return i; } }
        }
        return -1;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId){
        return(isBookIdValid(bookId))&&(this.libraryBooks[bookId].getCurrentBorrowerId() == -1);
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     * @param patron The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully registered,
     * a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron){
        if (currentNumberOfPatron == this.maxPatronCapacity){ return -1; }
        else{
            int id = getPatronId(patron);
            if (id != -1){ return id; }
            else{
                patron.initialiseBooksTaken(this.maxBookCapacity);
                patron.initialiseBooksRecommended(this.maxBookCapacity);
                this.libraryPatrons[currentNumberOfPatron] = patron;
                currentNumberOfPatron++;
                return currentNumberOfPatron - 1;
            }
        }
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     * @param patronId The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        return (patronId < this.maxPatronCapacity) && (patronId >= 0) && this.libraryPatrons[patronId] != null;
    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     * @param patron The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron){
        for (int i=0; i < this.libraryPatrons.length; i++){
            if (this.libraryPatrons[i] == null) { return -1;}
            else {if (this.libraryPatrons[i].equals(patron)){ return i; } }}
        return -1;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id, if this
     * book is available, the given patron isn't already borrowing the maximal number of books allowed, and if
     * the patron will enjoy this book.
     * @param bookId The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId){
        if ((!isBookIdValid(bookId))||(!isPatronIdValid(patronId))){ return false; }
        else {
            if ((isBookAvailable(bookId))&&(this.libraryBookLending[patronId] <= maxBorrowedBooks)
                    &&(this.libraryPatrons[patronId].willEnjoyBook(this.libraryBooks[bookId]))){
                this.libraryBooks[bookId].setBorrowerId(patronId);
                this.libraryBookLending[patronId]++;
                this.libraryPatrons[patronId].setBooksTaken(this.libraryBooks[bookId]);
                return true;
            }
        }
        return false;
    }

    /**
     * Return the given book.
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId){
        if (!isBookAvailable(bookId)){
            this.libraryBookLending[this.libraryBooks[bookId].getCurrentBorrowerId()]--;
            this.libraryBooks[bookId].returnBook();
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most, out of all available books he will
     * enjoy and didn't already read or been suggested before, if any such exist.
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most. Null if no book is available.
     */
    Book suggestBookToPatron(int patronId){
        Book enjoyable = null;
        int maxScore = 0;
        for (Book libraryBook : this.libraryBooks) {
            if ((this.libraryPatrons[patronId].willEnjoyBook(libraryBook)) && (libraryBook.getCurrentBorrowerId() == -1)
                    && (!booksCheck(patronId, libraryBook)) && (maxScore <
                    this.libraryPatrons[patronId].getBookScore(libraryBook))){ enjoyable = libraryBook; }
        }
        if (enjoyable != null){ this.libraryPatrons[patronId].setBooksRecommended(enjoyable); }
        return enjoyable;
    }

    /*checks if the suggested book was already taken by the patron or have been suggested to him in the past */
    private boolean booksCheck(int patronId, Book book){
        for (Book b1:this.libraryPatrons[patronId].getBooksRecommended()){
            if (b1 == null) { return false;}
            else {if (b1.equals(book)){return true;}}}
        for (Book b2:this.libraryPatrons[patronId].getBooksTaken()){
            if (b2 == null) {return false;}
            else {if (b2.equals(book)){return true;}}}
        return false;
    }

    /**
     * a code for removing a book from the library inventory.
     * @param bookId the id of the book we want to delete.
     */
   void removingABook(int bookId){
        if (!isBookAvailable(bookId)){
            int patronId = this.libraryBooks[bookId].getCurrentBorrowerId();
            System.out.println("Warning the book " + this.libraryBooks[bookId].getTitle() + " by " +
                    this.libraryBooks[bookId].getAuthor() + " is currently held by: " +
                    this.libraryPatrons[patronId].stringRepresentation()+" are you sure you want to remove it? Y/N: ");
            String s = scan.next();
            while (!(s.equals("Y")||s.equals("N"))){
                System.out.println("Wrong input please use Y for yes or N for no in capital letters: ");
                s = scan.next();
            }
            if (s.equals("Y")) {
                this.libraryBookLending[patronId]--;
                removingLoop(bookId, this.libraryBooks);
                currentNumberOfBooks--;
            }
        }
        else{
            System.out.printf("You are abate to remove the book " + this.libraryBooks[bookId].stringRepresentation()
                    +" are you sure? Y/N: ");
            String s = scan.next();
            while (!(s.equals("Y")||s.equals("N"))){
                System.out.println("Wrong input please use Y for yes or N for no in capital letters: ");
                s = scan.next();
            }
            if (s.equals("Y")) {
                removingLoop(bookId, this.libraryBooks);
                currentNumberOfBooks--;
            }
        }
    }

    /*a loop to help with the removing*/
    private void removingLoop(int id, Object[] arr){
        for (int i = id; i < arr.length - 1; i++) {
            if (arr[i + 1] != null) {
                arr[i] = arr[i + 1];
                arr[i + 1] = null;
            }
        }
    }

    /**
     * a code for removing a patron from the library
     * @param patronId the id we want to remove
     */
    void removingAPatron(int patronId){
        if (this.libraryBookLending[patronId] != 0){
            System.out.println("Warning the patron " + this.libraryPatrons[patronId].stringRepresentation() + " has "
                    + this.libraryBookLending[patronId] + " books in his possession are you sure you " +
                    "want to remove him? Y/N: ");
            String s = scan.next();
            while (!(s.equals("Y")||s.equals("N"))){
                System.out.println("Wrong input please use Y for yes or N for no in capital letters: ");
                s = scan.next();
            }
            if (s.equals("Y")) {
                for (Book libraryBook : this.libraryBooks ){
                    if (libraryBook.getCurrentBorrowerId() == patronId) {
                        removingABook(getBookId(libraryBook));
                        if (this.libraryBookLending[patronId] == 0){ break; }
                    }
                }
                removingLoop(patronId, this.libraryPatrons);
                currentNumberOfPatron--;
            }
        }
        else{
            System.out.printf("You are abate to remove the patron " +
                    this.libraryPatrons[patronId].stringRepresentation() +" are you sure? Y/N: ");
            String s = scan.next();
            while (!(s.equals("Y")||s.equals("N"))){
                System.out.println("Wrong input please use Y for yes or N for no in capital letters: ");
                s = scan.next();
            }
            if (s.equals("Y")) {
                removingLoop(patronId, this.libraryPatrons);
                currentNumberOfPatron--;
            }
        }
    }

    /**
     * a search engine by the book name, i made it public so you can later on use it on the web page of the library.
     * @param title the name of the book we are looking for.
     * @return the id of the book in the library.
     */
    public String bookTitleSearch(String title){
        int bookId = -1;
        StringBuilder location = new StringBuilder("Those are the title id's in our library: ");
        for (int i = 0; i< this.libraryBooks.length; i++){
            if (this.libraryBooks[i] == null) {
                if (bookId == -1) { return "We're sorry but the book is not found in our library"; }
                else {return location.toString();}
            }
            else {
                if (this.libraryBooks[i].getTitle().equals(title)){
                    bookId = i;
                    location.append("\nId: ");
                    location.append(bookId);
                    location.append(".");
                }
            }
        }
        return location.toString();
    }

    /**
     * a search engine by the author name. i made it public so you can later on use it on the web page of the library.
     * @param author the name of the author we are looking for.
     * @return the names of his books in the library and there library id
     */
    public String bookAuthorSearch(String author){
        int bookId = -1;
        StringBuilder location = new StringBuilder("Those are the books and there id by the author "
                +"\""+author+"\""+ ": ");
        for (int i = 0; i< this.libraryBooks.length; i++){
            if (this.libraryBooks[i] == null) {
                if (bookId == -1) { return "We're sorry but the author " + "\""+author+"\"" + " doesn't have any"+
                        " books in our library"; }
                else {return location.toString();}
            }
            else {
                if (this.libraryBooks[i].getAuthor().equals(author)){
                    bookId = i;
                    location.append("\nThe book: ");
                    location.append(this.libraryBooks[i].getTitle());
                    location.append(" id: ");
                    location.append(bookId);
                    location.append(".");
                }
            }
        }
        return location.toString();
    }
}