import java.util.ArrayList;
import java.util.Scanner;

public class MyLibrary
{
    public static void main(String[] args)
    {
    	// Writing a welcome message
    	WelcomeMessage wm = new WelcomeMessage();
    	System.out.println(wm);
    	System.out.println("If you need to see a set of commands, please enter \"help\"");
    	System.out.println("If you want to exit the program, please enter \"stop\"");

    	
    	// craeting an instance of librarian who will control books
    	Librarian pulatLibrarian = new Librarian(new BooksCollections());
    
    	
    	// commands list
    	ArrayList<String> commands = new ArrayList<>();
    	commands.add("search");
    	commands.add("addBook");
    	commands.add("setToRead");
    	commands.add("rate");
    	commands.add("getBooks");
    	commands.add("suggestRead");
    	commands.add("addBooks");
    	
    	Scanner sc = new Scanner(System.in);
    	String userInput = sc.nextLine().toLowerCase();
    	while (!userInput.equals("stop"))
    	{
//    		System.out.println(" I AM IN THE CONTROLLED LOOP");
    		
    		if (userInput.equals("help"))
    		{
    			System.out.println("Available Commands:");
    			for (String command: commands)
    			{
    				System.out.println(command);
    			}
    		}
    		
    		
    		userInput = sc.nextLine().toLowerCase();
    		
    		
    	}
    	
    	
    	sc.close();
    	
    }

}
