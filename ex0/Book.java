/**
 * This class represents a book, which has a title, author, year of publication and different literary aspects.
 */
class Book extends Object {
   
   /** The title of this book. */
   private final String title;
   
   /** The name of the author of this book. */
   private final String author;
   
   /** The year this book was published. */
   private final int yearOfPublication;

   /** The comic value of this book. */
   private int comicValue;
   
   /** The dramatic value of this book. */
   private int dramaticValue;
   
   /** The educational value of this book. */
   private int educationalValue;
   
   /** The id of the current borrow of this book. */
   private int currentBorrowerId = -1;

   /*----=  Constructors  =-----*/
   
   /**
    * Creates a new book with the given characteristic.
    * @param bookTitle The title of the book.
    * @param bookAuthor The name of the author of the book.
    * @param bookYearOfPublication The year the book was published.
    * @param bookComicValue The comic value of the book.
    * @param bookDramaticValue The dramatic value of the book.
    * @param bookEducationalValue The educational value of the book.
    */
   Book(String bookTitle, String bookAuthor, int bookYearOfPublication, int bookComicValue, int bookDramaticValue,
         int bookEducationalValue){
      this.title = bookTitle;
      this.author = bookAuthor;
      this.yearOfPublication = bookYearOfPublication;
      this.comicValue = bookComicValue;
      this.dramaticValue = bookDramaticValue;
      this.educationalValue = bookEducationalValue;
   }

   /*----=  Instance Methods  =-----*/
   
   /**
    * Returns a string representation of the book, which is a sequence of the title, author,
    * year of publication and the total literary value of the book, separated by commas,
    * inclosed in square brackets.
    * @return the String representation of this book.
    */
   String stringRepresentation(){ return "[" + this.getTitle() + "," + this.getAuthor() + "," +
           this.getYearOfPublication() + "," + this.getLiteraryValue() + "]"; }
   
   /**
    * @return the literary value of this book, which is defined as the sum of its comic value, its dramatic
    * value and its educational value.
    */
   int getLiteraryValue() { return this.getComicValue() + this.getDramaticValue() + this.getEducationalValue(); }
   
   /**
    * Sets the given id as the id of the current borrower of this book, -1 if no patron is currently borrowing it.
    * @param borrowerId the patron id to set
    */
   void setBorrowerId(int borrowerId){ this.currentBorrowerId = borrowerId; }

   /**
    * Marks this book as returned.
    */
   void returnBook(){ this.currentBorrowerId = -1; }

   /**
    * @return the id of the current borrower of this book.
    */
   int getCurrentBorrowerId(){ return this.currentBorrowerId; }

   /**
    * @return the comic Value of this book.
    */
   int getComicValue() { return this.comicValue; }

   /**
    * @return the dramatic Value of this book.
    */
   int getDramaticValue() { return this.dramaticValue; }

   /**
    * @return the educational Value of this book.
    */
   int getEducationalValue() { return this.educationalValue; }

   /**
    * @return the year Of Publication of this book.
    */
   int getYearOfPublication() { return this.yearOfPublication; }

   /**
    * @return the author of this book.
    */
   String getAuthor() { return this.author; }

   /**
    * @return the title of this book.
    */
   String getTitle() { return this.title; }
}