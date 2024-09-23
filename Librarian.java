import java.util.ArrayList;

public class Librarian 
{
	private BooksCollections books;
	public Librarian(BooksCollections bookCollections)
	{
		this.books = bookCollections;
	}
	
	
	public ArrayList<Book> search(String searchBy, String desc)
	{
		if (searchBy.equals("title"))
		{
			return this.books.searchByTitle(desc);
		}
		if (searchBy.equals("author"))
		{
			return this.books.searchByAuthor(desc);
		}
		else
		{
			return this.books.searchByRating(desc);
		}
	}
	
	public void addBook(String title, String author, int rating)
	{
		// creating a new book
		Book newBook = new Book(title, author, rating);
		this.books.add(newBook);
	}
	
	
	// need to be implemented
	public void setToRad()
	{
		
	}
	
	// need to implement
	public void rate()
	{
	
	}
	
	// need to be implemented
	public ArrayList<Book> getBooks()
	{
		return null;
	}
	
	public Book suggestRead()
	{
		return this.books.getRandomBook();  // returning a random book to be suggested
	}
	
	
	public void addBooks()
	{
		this.books.appendCollection(); // after raeding a file, enriching a book collection
	}
}
