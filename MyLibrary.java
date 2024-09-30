import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyLibrary
{
	private static Librarian librarian;

	private static Scanner sc;
	
    public static void main(String[] args)
    {
    	// Writing a welcome message
    	WelcomeMessage wm = new WelcomeMessage();
    	System.out.println(wm);
    	System.out.println("If you need to see a set of commands, please enter \"help\"");
    	System.out.println("If you want to exit the program, please enter \"stop\"");

    	
    	// craeting an instance of librarian who will control books
    	librarian = new Librarian(new BooksCollections());
    	
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
    		
    		// search case when we need to allow user to choose
    		// by what property of the book should we search by
    		//  it can rating, title, or author
    		else if (userInput.equals("search"))
    		{
				// moved the section to a separate function
    			searchBook();
    		}
    		
    		
    		// addBook command when we have to accepts three main characteristics of a new book
    		// that should be added to our collection
    		else if (userInput.equals("addbook"))
    		{
    			System.out.print("Please enter an author's name: ");
    			String authorName = sc.nextLine();
    			
    			System.out.print("Please enter a title of the book: ");
    			String titleDesc = sc.nextLine();
    			
    			System.out.print("Please enter a rating for the book: ");
    			int rating = sc.nextInt();
    			
    			
    			// To solve the issue with else block working
    			sc.nextLine(); // Consume the leftover newline
    			
    			librarian.addBook(titleDesc, authorName, rating);
    			System.out.println("Book added succesfully!");
    			// for debugging purposes
//    			System.out.print(librarian.books);
    		}
    		
    		
    		// setToRead command 
    		else if (userInput.equals("settoread"))
    		{
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
					selectedBookIdx = sc.nextInt();
				} else {
					selectedBookIdx = 0;
				}
				librarian.rate(booksFound.get(selectedBookIdx));


				System.out.println("Book successfully set to read!");
    		}
    		
    		// rate command
    		else if (userInput.equals("rate"))
    		{
    			// TODO need to work on searching for books
    		}
    		
    		
    		// getBooks command
    		else if (userInput.equals("getbooks"))
    		{
    			// TODO nned to think of the way of sorting and giving books by title, author or rating
    		}
    		
    		
    		// suggestRead command
    		else if (userInput.equals("suggestread"))
    		{
    			System.out.println("We highly recommend: " + librarian.suggestRead());
    		}
    		
    		
    		// addBooks command
    		else if (userInput.equals("addbooks"))
    		{
    			// TODO need to think of the way of finding 
    			System.out.println("Enter a valid file name with .txt extension");
    			System.out.println("(Note that a file should be located in the same directory)");
    			String fileName = sc.nextLine();
    			librarian.addBooks(fileName);
    			System.out.println("Books added succesfully!");	
    		}
    
    		// else case when user entered some invalid or miss spelled command
    		// so we show that no command was executed and ask them to enter a valid
    		// command
    		
    		else
    		{
    			System.out.print("Oopsie, it seems you entered an invalid or incorrectly spelled\ncommand,");
    			System.out.println(" please go over the list of commands and try again!");
    		}
    		
    		
    		// to read a next new command
    		userInput = sc.nextLine().toLowerCase();
    		
    		
    	}
    	
    	
    	sc.close();
    	
    }

	private static ArrayList<Book> searchBook() {
		System.out.println("Choose an option for search command and enter one of the letters:");
		System.out.println("\"A\" - searching by Author;\n\"T\" - searching by Title;\n\"R\" - searching by Rating;");
		String option = sc.nextLine().toLowerCase();
		if (option.equals("a"))
		{
			System.out.print("Please enter an author's name for searching: ");
			String authorName = sc.nextLine();
			
			// getting the list of found books
			ArrayList<Book> books = librarian.searchByAuthor(authorName);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				ArrayList<Book> booksToPrint = new ArrayList<Book>();
				System.out.println("We found for you (by Author): ");
				for (Book book:books)
				{
					System.out.println(book);
					booksToPrint.add(book);
				}

				return booksToPrint;
				
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
			ArrayList<Book> books = librarian.searchByTitle(titleName);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				ArrayList<Book> booksToPrint = new ArrayList<Book>();
				System.out.println("We found for you (by Title): ");
				for (Book book:books)
				{
					System.out.println(book);
					booksToPrint.add(book);
				}
				return booksToPrint;
			}
			else
			{
				System.out.println("Oopsie, we do not have that book in our storage!");
			}	
		}
		else if (option.equals("r"))
		{
			System.out.println("Please enter the rating of the book you are looking for: ");
			int rating = sc.nextInt();
			// getting the list of found books
			ArrayList<Book> books = librarian.searchByRating(rating);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				ArrayList<Book> booksToPrint = new ArrayList<Book>();
				System.out.println("We found for you (by Rating): ");
				for (Book book:books)
				{
					System.out.println(book);
					booksToPrint.add(book);
				}
				return booksToPrint;
			}
			else
			{
				System.out.println("Oopsie, we do not have that book in our storage!");
			}	
    	}
		return null;
	}



}
