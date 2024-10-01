import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyLibrary
{
	private static Scanner sc;
	
    public static void main(String[] args)
    {
    	// Writing a welcome message
    	WelcomeMessage wm = new WelcomeMessage();
    	System.out.println(wm);
    	System.out.println("If you need to see a set of commands, please enter \"help\"");
    	System.out.println("If you want to exit the program, please enter \"stop\"");

    	
    	// craeting an instance of librarian who will control books
    	// librarian = new Librarian(new BooksCollections());
    	
    	// getting mapped our librarian's tasks to map to his duties with description
    	Map<String, String> duties = new HashMap<>();
    	duties.put("search", "helps to search for books by title, author, or rating");
    	duties.put("addBook", "asks the user for an extra information such as author,\ntitle names and rating to add a new book to the book collection");
    	duties.put("setToRead", "sets a status of the book to be read");
    	duties.put("rate", "puts a rating on the user's desired book");
    	duties.put("getBooks", "lists a collection of books by title, author, rating,\nor read status");
    	duties.put("suggestRead", "gets a random book from the library and suggests\nit to the user");
    	duties.put("addBooks", "reads a file in a specific format, so those books are\nadded to the book collection");

		sc = new Scanner(System.in);
		CommandProcessor commandProcessor = new CommandProcessor(sc, new BooksCollections());

		Map<String, Runnable> cmdList = new HashMap<>();

		cmdList.put("search", () -> {commandProcessor.searchBook();});
		cmdList.put("addbook", () -> {commandProcessor.addBook();});
		cmdList.put("settoread", () -> {commandProcessor.setToRead();});
		cmdList.put("rate", () -> {commandProcessor.rateBook();});
		cmdList.put("getbooks", () -> {commandProcessor.getBooks();});
		cmdList.put("suggestread", () -> {commandProcessor.suggestRead();});
		cmdList.put("addbooks", () -> {commandProcessor.addBooks();});

    	
    	String userInput = sc.nextLine().toLowerCase();
    	while (!userInput.equals("stop"))
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
    		// else case when user entered some invalid or miss spelled command
    		// so we show that no command was executed and ask them to enter a valid
    		// command
    		
			// gets called when searching for a book by rating or when rating
			// needs to account for those inputs
    		if (cmdList.get(userInput) == null)
    		{
    			System.out.print("Oopsie, it seems you entered an invalid or incorrectly spelled\ncommand,");
    			System.out.println(" please go over the list of commands and try again!");
    		} else {
				cmdList.get(userInput).run();
			}
			// to read a next new command
			userInput = sc.nextLine().toLowerCase();
    	}
    	
    	
    	sc.close();
    	
    }
}
