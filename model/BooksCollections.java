/*
 * File: BooksCollections.java
 * Authors: Abrorjon Asralov, Pulat Uralov
 * Username: asralov, uralovpulya
 *
 * Purpose: this is the model of MyLibrary.java, takes care
 * of main logic, including searching and sorting, and
 * we have our array list to be private, so user cannot 
 * interract with this class. Actually, user cannot interract
 * with this class at all, because the all communication is done
 * through Librarian class, which gets this class instance and
 * fully control the workflow. 
 */

 package model;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.ArrayList;
 import java.util.Collections;
import java.util.HashSet;
import java.util.List;
 import java.util.Random;
 import java.util.Scanner;
 
 public class BooksCollections
 {
	 private ArrayList<Book> books; // an array list of book objects
	 public BooksCollections()
	 {
		 this.books = new ArrayList<>();
	 }
	 
	 /*
	  * searchByAuthor(String authorDesc) -- searches through the ArrayList
	  * of Book objects and returns a new ArrayList with author
	  * name equal to authorDesc
	  */
	 public ArrayList<Book> searchByAuthor(String authorDesc)
	 {
		 ArrayList<Book> fitDesc = new ArrayList<>();
		 for (int i = 0; i < books.size(); i++) {
			 if (books.get(i).getAuthor().equals(authorDesc)) {
				 fitDesc.add(books.get(i));
			 }
		 } 
		 return fitDesc;
	 }
 
	 /*
	  * searchByAuthorBestMatch(String authorBestMatch) -- searches through the ArrayList
	  * of Book objects and returns a new ArrayList where all books start with
	  * authorBestMatch
	  */
	 public ArrayList<Book> searchByAuthorBestMatch(String authorBestMatch) {
		 ArrayList<Book> fitDesc = new ArrayList<>();
		 for (int i = 0; i < books.size(); i++) {
			 if (books.get(i).getAuthor().startsWith(authorBestMatch)) {
				 fitDesc.add(books.get(i));
			 }
		 } 
		 
		 return fitDesc;
	 }
	 
	 /*
	  * searchByTitle(String titleDesc) -- searches through the ArrayList
	  * of Book objects and returns a new ArrayList with 
	  * title equal to titleDesc
	  */
	 public ArrayList<Book> searchByTitle(String titleDesc)
	 {
		 ArrayList<Book> fitDesc = new ArrayList<>();
		 for (int i = 0; i < books.size(); i++) {
			 if (books.get(i).getTitle().equals(titleDesc)) {
				 fitDesc.add(books.get(i));
			 }
		 } 
		 return fitDesc;
	 }
 
	 /*
	  * searchByTitleBestMatch(String titleBestMatch) -- searches through the ArrayList
	  * of Book objects and returns a new ArrayList where all books start with
	  * titleBestMatch
	  */
	 public ArrayList<Book> searchByTitleBestMatch(String titleBestMatch) {
		 ArrayList<Book> fitDesc = new ArrayList<>();
		 for (int i = 0; i < books.size(); i++) {
			 if (books.get(i).getTitle().startsWith(titleBestMatch)) {
				 fitDesc.add(books.get(i));
			 }
		 } 
		 return fitDesc;
	 }
 
	 /*
	  * searchByRating(int rating) -- searches through the ArrayList
	  * of Book objects and returns a new ArrayList where each Book
	  * has the rating specified in the argument
	  */
	 public ArrayList<Book> searchByRating(int rating)
	 {
		 ArrayList<Book> fitDesc = new ArrayList<>();
		 for (int i = 0; i < books.size(); i++) {
			 if (books.get(i).getRating() == rating) {
				 fitDesc.add(books.get(i));
			 }
		 } 
		 return fitDesc;
	 }
	 
	 /*
	  * add(Book newBook) -- adds a Book object to the array list
	  */
	 public void add(Book newBook)
	 {
		 this.books.add(newBook);
	 }
 
	 /*
	  * getRandomBook() -- returns a random unread book from the
	  * array list of unread books
	  */
	 public Book getRandomBook() {
		 ArrayList<Book> unreadBooks = getBooksByUnread();
		 // Check if the list is empty to avoid exceptions
		 if (unreadBooks.isEmpty()) {
			 return null;  // Or throw an exception depending on your needs
		 }
		 
		 // Create a random number generator
		 Random random = new Random();
		 
		 // Get a random index within the range of the books list
		 int randomIndex = random.nextInt(books.size());
		 
		 // Return the book at the random index
		 return unreadBooks.get(randomIndex);
	 }
 
	 /*
	  * appendCollection(String fileName) -- adds a collection of
	  * Book objects to the array list from a file specified by the
	  * given fileName. Returns true if file succesfully read and
	  * processed, false otherwise.
	  */
	 public boolean appendCollection(String fileName) 
	 {
		 try
		 {
			 File obj = new File(fileName);
			 Scanner reader = new Scanner(obj);
			 
			 while(reader.hasNextLine())
			 {
				 String data = reader.nextLine();
				 // split each line by ";" and add a Book object to 
				 // BookCollections
				 String[] dataSplit = data.split(";", 2);
				 String bookName = dataSplit[0];
				 String authorName = dataSplit[1];
				 // System.out.println(bookName + " " + authorName);
				 books.add(new Book(bookName, authorName));
			 }
			 reader.close();
			 return true;
		 }
		 catch(FileNotFoundException e)
		 {
			 System.out.println("Oopsie, something went wrong, please make sure");
			 System.out.println("you enterted a valid file with .txt extenstion");
			 return false; // returning false to indicate file is not valid
		 }
	 }
 
	 /*
	  * getBooksByAuthor() -- returns an array list of Book
	  * objects sorted by author name
	  */
	 public ArrayList<Book> getBooksByAuthor() {
		 ArrayList<Book> listToReturn = getCopy();
 
		 Collections.sort(listToReturn, new CompareByAuthor());
 
		 return listToReturn;
	 }
 
	 /*
	  * getBooksByTitle() -- returns and array list of Book
	  * objects sorted by book title
	  */
	 public ArrayList<Book> getBooksByTitle() {
		 ArrayList<Book> listToReturn = getCopy();
 
		 Collections.sort(listToReturn, new CompareByTitle());
 
		 return listToReturn;
	 }
 
	 /*
	  * getBooksByRead() -- returns an array list of read Book
	  * objects, sorted by author name
	  */
	 public ArrayList<Book> getBooksByRead() {
		 ArrayList<Book> listToReturn = new ArrayList<Book>();
 
		 for (int i = 0; i < books.size(); i++) {
			 if (books.get(i).isRead()) {
				 listToReturn.add(books.get(i));
			 }
		 }
		 Collections.sort(listToReturn, new CompareByAuthor());
		 return listToReturn;
	 }
 
	 /*
	  * getBooksByRead() -- returns an array list of UNread Book
	  * objects, sorted by author name
	  */
	 public ArrayList<Book> getBooksByUnread() {
		 ArrayList<Book> listToReturn = new ArrayList<Book>();
 
		 for (int i = 0; i < books.size(); i++) {
			 if (!books.get(i).isRead()) {
				 listToReturn.add(books.get(i));
			 }
		 }
		 Collections.sort(listToReturn, new CompareByAuthor());
		 return listToReturn;
	 }

	 /*
	  * FilterByAuthor() -- calls getBooksByAuthor on the current books
	  */
	 public void FilterByAuthor() {
		this.books = getBooksByAuthor();
	 }

	 /*
	  * FilterByTitle() -- calls getBooksByTitle on the current books
	  */
	 public void FilterByTitle() {
		this.books = getBooksByTitle();
	 }
 
	 /*
	  * getCopy() -- returns the copy of the array list of Book objects,
	  * used to achieve encapsulation and avoid escaping reference
	  */
	 public ArrayList<Book> getCopy() {
		 ArrayList<Book> copy = new ArrayList<>(List.copyOf(books));
 
		 return copy;
 
	 }
	 
	 /*
	  * toString() -- prints the BookCollections instance
	  * for debugging purposes.
	  */
	 public String toString() {
		 String temp = "";
		 temp += "Books in the collection:\n";
		 for (Book book : books) {
			 temp += book.toString() + "\n";
		 }
		 return temp;
	 }
 }
 