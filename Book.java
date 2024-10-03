/**
 * File: Book.java
 * Author: Abrorjon Asralov, Pulat Uralov
 * Username: asralov, uralovpulya
 * 
 * Purpose: This is a class for creating instances of Book objects.
 * it has author, title, rating, status properties so we can work 
 * with them. We reach encapsulation by having private variables
 * so user cannot directly interract with them. And getters with 
 * setters thorougly work with them, since we should have our status
 * and rating changing
 */

public class Book
{
	private String title;
	private String author;
	private int rating;
	private boolean isRead;

	// Constructor with rating
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
		this.isRead = false; // setting to be false since at first book need not to be read
	}
	
	
	/**
	 * This is a method that helps to update the rating of the book between 0-5
	 * Like putting starts. Very satisfied is 5 starts 
	 * @param newRating is an integer that should be set to be a new rating of
	 * the book
	 */
	public void updateRating(int newRating)
	{
		// checking for boundries without making it problematic for user side
		if (newRating >= 5)
		{
			this.rating = 5;
			return;
		}
		if (newRating <= 0)
		{
			this.rating = 0;
			return;
		}
		this.rating = newRating;
		return;
	}
	
	
	/**
	 * This is a getter method that returns an integer that is the rating of the book
	 * @return an integer that is a rating of the book
	 */
	public int getRating()
	{
		return this.rating;
	}
	
	/**
	 * This is a getter method that returns a string that is the name of the title
	 * @return a string that is a title for the book
	 */
	public String getTitle()
	{
		return this.title;
	}
	
	/**
	 * This is a getter method that returns a string that is the name of the author
	 * @return a string that is an author name
	 */
	public String getAuthor()
	{
		return this.author;
	}
	
	/**
	 * This is a setter method that helps to change unread status to be read
	 */
	public void read()
	{
		this.isRead = true; // making a book to be read, and we cannot unread it
	}
	
	/**
	 * This is a getter method that helps to know the status of the book,
	 * whether it is read or not
	 * @return a boolean, true if read, false otherwise
	 */
	public boolean isRead()
	{
		return this.isRead; // return the status of the book whether it is read or not
	}
	
	/**
	 * This is a method that is used for printing purposes. It is used for printing 
	 */
	public String toString()
	{
	 	return this.title + " - " + this.author + " (" + this.rating + " stars)";
	}
	
	
}
