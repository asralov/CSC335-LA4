/**
 * File: Librarian.java
 * Author: Abrorjon Asralov, Pulat Uralov
 * Purpose: This is a vital class where all controlling and 
 * brain related part is hapenning. It makes sense to call
 * it librarian because all job is done by this file which
 * takes care of passing things around and handling messages
 * for user interface.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Librarian 
{
    private Scanner sc;
    private BooksCollections booksCol;

	/**
	 * This is a public constructor which takes two parameters, first one is a
	 * scanner that is passed from MyLibrary class, second is a BooksCollections
	 * class that is a collection of books to which librarian has access and can
	 * do commands to satisfy user's valid commands
	 * @param scanner is a scanner object passed from UI
	 * @param booksCollection is a collection of book objects
	 */
    public Librarian(Scanner scanner, BooksCollections booksCollection) {
        this.sc = scanner;
        this.booksCol = booksCollection;
    }


	/**
	 * This is a public getter method that should search for books depending on
	 * the search option, the reason for making it return a collection of books
	 * after search is because some commands require for a specific search to work 
	 * with book objects, so we can just search and return for other commands to
	 * work with
	 * @return an array list of book objects
	 */
    public ArrayList<Book> searchBook() {
		System.out.println("Choose an option for search command and enter one of the letters:");
		System.out.println("\"A\" - searching by Author;\n\"T\" - searching by Title;\n\"R\" - searching by Rating;");
		String option = sc.nextLine().toLowerCase();
		if (option.equals("a"))
		{
			System.out.print("Please enter an author's name for searching: ");
			String authorName = sc.nextLine();
			
			// getting the list of found books
			ArrayList<Book> books = booksCol.searchByAuthor(authorName);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				System.out.println("We found for you (by Author): ");
				printBooks(books);

				return books;
				
			}
			else
			{
				System.out.println("Oopsie, we do not have that book in our storage!");
			}		
		}
		else if (option.equals("t"))
		{
			System.out.println("Please enter the title of the book you are looking for: ");
			String titleName = sc.nextLine();
			// getting the list of found books
			ArrayList<Book> books = booksCol.searchByTitle(titleName);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				System.out.println("We found for you (by Title): ");
				printBooks(books);

				return books;
			}
			else
			{
				System.out.println("Oopsie, we do not have that book in our storage!");
			}	
		}
		else if (option.equals("r"))
		{
			System.out.println("Please enter the rating of the book you are looking for: ");
			String rating = sc.nextLine();
			if (isNumeric(rating))
			{
				int validRate = Integer.parseInt(rating)  ;   
				// getting the list of found books
				ArrayList<Book> books = booksCol.searchByRating(validRate);
				if (books.size() > 0)
				{
					// createa an arrayList and append each element to it
					System.out.println("We found for you (by Rating): ");
					printBooks(books);

					return books;
				}
				else
				{
					System.out.println("Oopsie, we do not have that book in our storage!");
				}	
			}
			// else case when it is not a valid input
			else
			{
				System.out.println("Please enter a valid non-negative integer in range 1 -> 5");
			}
    	}
		// return empty ArrayList
		return new ArrayList<Book>();
	}

	private boolean isNumeric(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 */
	public void setToRead() {
		// prompts the user to enter the book to flag as read and calls
		// searchBook() function
		System.out.println("Please enter the book that you have read: \n");
		ArrayList<Book> booksFound = searchBook();
		// if none foudn return
		if (booksFound.size() == 0) return;

		int selectedBookIdx;
		// If there are multiple books found
		if (booksFound.size() > 1) {
			System.out.println("Please specify the book from the list: ");
			String userIDInput = sc.nextLine();
			if (isNumeric(userIDInput))
			{
				selectedBookIdx = Integer.parseInt(userIDInput);
			}
			else
			{
				System.out.println("Please enter a valid integer from the given IDs");
				return;
			}
			

		} else {
			selectedBookIdx = 0;
		}
		booksFound.get(selectedBookIdx).read();


		System.out.println("Book successfully set to read!");
	}

	/**
	 * 
	 */
	public void rateBook() {

		System.out.println("Please enter the book to rate: \n");

		// THE FOLLOWING SECTION IS A COPY FROM setToRead()

		ArrayList<Book> booksFound = searchBook();
		// if none foudn return
		if (booksFound.size() == 0) return;
		int selectedBookIdx;
		// If there are multiple books found
		if (booksFound.size() > 1) {
			System.out.println("Please enter the ID of the book: ");
			String userIDInput = sc.nextLine();
			if (isNumeric(userIDInput))
			{
				selectedBookIdx = Integer.parseInt(userIDInput);
				if (selectedBookIdx >= booksFound.size())	
				{
					System.out.println("Oopsie, enter a valid existing ID");
					return;
				}
			}
			else
			{
				System.out.println("Please enter a valid integer from the given IDs");
				return;
			}
		} else {
			selectedBookIdx = 0;
		}

		System.out.println("Please enter a rating from 1 to 5: ");
		String rating  = sc.nextLine();
		if (isNumeric(rating))
		{
			int validRate = Integer.parseInt(rating);	

			booksFound.get(selectedBookIdx).updateRating(validRate);
			System.out.println("Rating updated!");
		}
		else
		{
			System.out.println("Please enter a valid non-negative integer in range 1 -> 5");
			return;
		}
        // sc.nextLine();


	}

	/**
	 * 
	 */
    public void addBook() {
        System.out.print("Please enter an author's name: ");
        String authorName = sc.nextLine();
        
        System.out.print("Please enter a title of the book: ");
        String titleDesc = sc.nextLine();
        
        System.out.print("Please enter a rating for the book: ");
        String rating = sc.nextLine();
		if (isNumeric(rating))
		{
			int validRate = Integer.parseInt(rating);
			Book newBook = new Book(titleDesc, authorName, validRate);
			booksCol.add(newBook);
			System.out.println("Book added succesfully!");
		}
		else
		{		
			System.out.println("Please enter a valid non-negative integer in range 1 -> 5");	
			return;	
		}
         
        // To solve the issue with else block working
        // sc.nextLine(); // Consume the leftover newline

        // for debugging purposes
        //System.out.print(librarian.books);
    }

	/**
	 * 
	 */
    public void getBooks() {
		System.out.println("Choose an option for getBooks command and enter one of the letters:");
		System.out.println("\"A\" - get books by Author;" +
							"\n\"T\" - get books by Title;" +
							"\n\"R\" - get book that have been read;" +
							"\n\"U\" - get book that have not been read;");
		String option = sc.nextLine().toLowerCase();
		Map<String, Runnable> cmdList = new HashMap<>();
		
		cmdList.put("a", () -> {ArrayList<Book> booksToPrint = booksCol.getBooksByAuthor();printBooks(booksToPrint);});
        cmdList.put("t", () -> {ArrayList<Book> booksToPrint = booksCol.getBooksByTitle();printBooks(booksToPrint);});
        cmdList.put("r", () -> {ArrayList<Book> booksToPrint = booksCol.getBooksByRead();printBooks(booksToPrint);});
        cmdList.put("u", () -> {ArrayList<Book> booksToPrint = booksCol.getBooksByUnread();printBooks(booksToPrint);});

		if (cmdList.get(option) == null) {
			System.out.println("Command not found!");
			return;
		}

		cmdList.get(option).run();

	}

	/**
	 * 
	 */
    public void suggestRead() {
        System.out.println("We highly recommend: " + booksCol.getRandomBook());
    }

	/**
	 * 
	 */
    public void addBooks() {
        System.out.println("Enter a valid file name with .txt extension");
        System.out.println("(Note that a file should be located in the same directory)");
        String fileName = sc.nextLine();
        boolean isSuccess = booksCol.appendCollection(fileName);
		if (!isSuccess)
		{
			System.out.println("Make sure the valid file is in the same directory!");
		}
		else
		{
			System.out.println("Books added succesfully!");	
		}
  
    }

	/**
	 * 
	 * @param books
	 */
	private void printBooks(ArrayList<Book> books) {
		int idx = 0;
		for (Book book:books)
		{
			System.out.println("id: " + idx + " - " +  book);
			idx++;
		}
	}
    


}
