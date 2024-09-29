/**
 * @author Abrorjon Asralov, Uralov Pulat
 * Since this is an immutable class where we set up 
 * a book class to be a Book with title and author, 
 * the only thing we can change is rating
 */
public class Book 
{
	private String title;
	private String author;
	private int rating;
	private boolean isRead;
	// Constructor
	public Book(String title, String author, int rating)
	{
		this.title = title;
		this.author = author;
		this.updateRating(rating); 
		this.isRead = false;  // setting to be false since at first book need not to be read
	}
	
	// Constructor without rating
	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
		this.rating = 0;  // by default
		this.isRead = false;
	}
	
	
	/**
	 * This is a method that helps to update the rating of the book between 1-5
	 * Like putting starts. Very satisfied is 5 starts 
	 * @param newRating
	 */
	public void updateRating(int newRating)
	{
		// checking for boundries without making it problematic for user side
		if (newRating >= 5)
		{
			this.rating = 5;
			return;
		}
		if (newRating <= 1)
		{
			this.rating = 1;
			return;
		}
		this.rating = newRating;
		return;
	}
	
	
	/**
	 * This is a getter method that returns an integer that is the rating of the book
	 * @return
	 */
	public int getRating()
	{
		return this.rating;
	}
	
	/**
	 * This is a getter method that returns a string that is the name of the title
	 * @return
	 */
	public String getTitle()
	{
		return this.title;
	}
	
	/**
	 * This is a getter method that returns a string that is the name of the author
	 * @return
	 */
	public String getAuthor()
	{
		return this.author;
	}
	
	public void read()
	{
		this.isRead = true; // making a book to be read, and we cannot unread it
	}
	
	public boolean isRead()
	{
		return this.isRead; // return the status of the book whether it is read or not
	}
	
	public String toString()
	{
	 	return this.title + " - " + this.author + " (" + this.rating + " stars)";
	}
	
	
}
