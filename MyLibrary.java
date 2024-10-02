/*
 * File: MyLibrary.java
 * Authors: Abrorjon Asralov, Pulat Uralov
 * Purpose: By using MVC pattern, this is a file for V - View. User can 
 * only see how program should work interfacewise. All the processing is
 * happening on the background.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyLibrary
{
	private static Scanner sc;
	
    public static void main(String[] args)
    {
    	// writing a welcome message
    	WelcomeMessage wm = new WelcomeMessage();
    	System.out.println(wm);
    	System.out.println("If you need to see a set of commands, please enter \"help\"");
    	System.out.println("If you want to exit the program, please enter \"stop\"");

		// creating a scanner object to get user input
		sc = new Scanner(System.in);

		// craeting an instance of librarian who will control books
		Librarian commandProcessor = new Librarian(sc, new BooksCollections());

    	// getting mapped our librarian's tasks to map to his duties with description
		// for user interface purpose only
    	Map<String, String> duties = new HashMap<>();
    	duties.put("search", "helps to search for books by title, author, or rating");
    	duties.put("addBook", "asks the user for an extra information such as author,\ntitle names and rating to add a new book to the book collection");
    	duties.put("setToRead", "sets a status of the book to be read");
    	duties.put("rate", "puts a rating on the user's desired book");
    	duties.put("getBooks", "lists a collection of books by title, author, rating,\nor read status");
    	duties.put("suggestRead", "gets a random book from the library and suggests\nit to the user");
    	duties.put("addBooks", "reads a file in a specific format, so those books are\nadded to the book collection");

		// this is a hashmap where we point user's processed command to run a lamda function
		// that is managed by the linrarian class. If user enters a valid command, it should
		// trigger that lambda function and librarian will take care of everything on its side
		Map<String, Runnable> cmdList = new HashMap<>();
		cmdList.put("search", () -> {commandProcessor.searchBook();});
		cmdList.put("addbook", () -> {commandProcessor.addBook();});
		cmdList.put("settoread", () -> {commandProcessor.setToRead();});
		cmdList.put("rate", () -> {commandProcessor.rateBook();});
		cmdList.put("getbooks", () -> {commandProcessor.getBooks();});
		cmdList.put("suggestread", () -> {commandProcessor.suggestRead();});
		cmdList.put("addbooks", () -> {commandProcessor.addBooks();});

		System.out.print("> ");
		// converting to lowercase to avoid case sensitivity of the commands
    	String userInput = sc.nextLine().toLowerCase();  
    	while (!userInput.equals("stop"))  // if user wants to exit the prgram
    	{	
    		// printing the helper command where we show all other available commands
    		if (userInput.equals("help"))
    		{
    			System.out.println("Available Commands:");
    			System.out.println("*****************************************************************");
    	        for (Map.Entry<String, String> entry : duties.entrySet()) {
    	            System.out.println(entry.getKey() + " -- " + entry.getValue());
    	            System.out.println("*****************************************************************");
    	        }
    		}	
			// if command does not exist, we print a message to the user to try again with valid command
    		if (cmdList.get(userInput) == null)
    		{
    			System.out.print("Oopsie, it seems you entered an invalid or incorrectly spelled\ncommand,");
    			System.out.println(" please go over the list of commands and try again!");
    		} 
			// otherwise, we succesfully trigger a lambda function
			else 
			{
				cmdList.get(userInput).run();
			}
			// to read a next new command
			System.out.print("> ");
			userInput = sc.nextLine().toLowerCase();
    	}	
    	sc.close();	 // closing the scanner to prevent memory leak
    }
}
