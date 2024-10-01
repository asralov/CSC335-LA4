import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getAuthor().equals(authorDesc)) {
				fitDesc.add(books.get(i));
			}
		} 
		return fitDesc;
	}
	
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

	// added a comment
	public void appendCollection(String fileName) 
	{
		try
		{
			// File obj = new File("src/" + fileName);
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
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<Book> getBooksByAuthor() {
		return null;

	}
	public ArrayList<Book> getBooksByTitle() {
		return null;
		
	}
	public ArrayList<Book> getBooksByRead() {
		return null;
	}
	public ArrayList<Book> getBooksByUnread() {
		return null;
	}
	
    public String toString() {
        String temp = "";
        temp += "Books in the collection:\n";
        for (Book book : books) {
            temp += book.toString() + "\n";
        }
        return temp;
    }
}
