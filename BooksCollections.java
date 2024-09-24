import java.util.ArrayList;
import java.util.Random;

public class BooksCollections
{

	private ArrayList<Book> books;
	public BooksCollections()
	{
		this.books = new ArrayList<>();
	}
	
	public ArrayList<Book> searchByAuthor(String authorDesc)
	{
		ArrayList<Book> fitDesc = new ArrayList<>();
		return fitDesc;
	}
	
	public ArrayList<Book> searchByTitle(String titleDesc)
	{
		ArrayList<Book> fitDesc = new ArrayList<>();
		return fitDesc;
	}
	public ArrayList<Book> searchByRating(String ratingDesc)
	{
		int rating = Integer.parseInt(ratingDesc);  // translating string to be int
		ArrayList<Book> fitDesc = new ArrayList<>();
		return fitDesc;
	}
	
	public void add(Book newBook)
	{
		this.books.add(newBook);
	}

	public Book getRandomBook() {
	    // Check if the list is empty to avoid exceptions
	    if (books.isEmpty()) {
	        return null;  // Or throw an exception depending on your needs
	    }
	    
	    // Create a random number generator
	    Random random = new Random();
	    
	    // Get a random index within the range of the books list
	    int randomIndex = random.nextInt(books.size());
	    
	    // Return the book at the random index
	    return books.get(randomIndex);
	}

	public void appendCollection() 
	{
		try
		{
			
		}
		catch(FileNotFoundException e)
		{
			
		}
	}
}
