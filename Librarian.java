import java.util.ArrayList;

public class Librarian 
{
	private BooksCollections books;
	public Librarian(BooksCollections bookCollections)
	{
		this.books = bookCollections;
	}
	
	
//	public ArrayList<Book> search(String searchBy, String desc)
//	{
//		if (searchBy.equals("t"))
//		{
//			return this.books.searchByTitle(desc);
//		}
//		if (searchBy.equals("a"))
//		{
//			return this.books.searchByAuthor(desc);
//		}
//		else
//		{
//			return this.books.searchByRating(desc);
//		}
//	}
	
	public ArrayList<Book> searchByAuthor(String authorName)
	{
		return this.books.searchByAuthor(authorName);
	}
	
	public ArrayList<Book> searchByTitle(String titleName)
	{
		return this.books.searchByTitle(titleName);
	}
	public ArrayList<Book> searchByRating(int rating)
	{
		return this.books.searchByRating(rating);
	}
	
	
	public void addBook(String title, String author, int rating)
	{
		// creating a new book
		Book newBook = new Book(title, author, rating);
		this.books.add(newBook);
	}
	
	
	// need to be implemented
	public void setToRead()
	{
		
	}
	
	// need to implement
	public void rate(String bookDesc, String status)
	{
	
	}

	// rates a book by its title
	public void rate(Book book)
	{
		book.read();
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
	
	
	public void addBooks(String fileName)
	{
		this.books.appendCollection(fileName); // after raeding a file, enriching a book collection
	}
}
