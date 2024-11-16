package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestingBooksCollectionsAndBooks {

	private BooksCollections bookCollection = new BooksCollections();

    @Test
    public void testAddBook() {
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 5);
        bookCollection.add(book1);
        assertEquals(1, bookCollection.getCopy().size());
    }
    
    @Test
    public void testSearchByAuthor() {
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 5);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 4);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);

        ArrayList<Book> result = bookCollection.searchByAuthor("J.R.R. Tolkien");
        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }

    @Test
    public void testSearchByAuthorBestMatch() {
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 5);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 4);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);

        ArrayList<Book> result = bookCollection.searchByAuthorBestMatch("J.R.R.");
        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }


    @Test
    public void testSearchByTitle() {
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 5);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 4);
        bookCollection.add(book1);
        bookCollection.add(book2);

        ArrayList<Book> result = bookCollection.searchByTitle("The Lord of the Rings");
        assertEquals(1, result.size());
        assertTrue(result.contains(book1));
    }

    @Test
    public void testSearchByTitleBestMatch() {
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 5);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 4);
        Book book3 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);

        ArrayList<Book> result = bookCollection.searchByTitleBestMatch("The");
        assertEquals(3, result.size());
    }

    @Test
    public void testSearchByRating() {
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 5);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 4);
        bookCollection.add(book1);
        bookCollection.add(book2);

        ArrayList<Book> result = bookCollection.searchByRating(5);
        assertEquals(1, result.size());
        assertTrue(result.contains(book1));
    }

    @Test
    public void testGetRandomBook_NotEmpty() {
        Book book1 = new Book("Book 1", "Author 1", 3);
        bookCollection.add(book1);
        Book randomBook = bookCollection.getRandomBook();
        assertEquals(book1, randomBook); //or assertTrue(randomBook != null)  depending on your needs.
    }


    @Test
    public void testGetRandomBook_Empty() {
        Book randomBook = bookCollection.getRandomBook();
        assertNull(randomBook);
    }

    @Test
    public void testAppendCollection_Success() {
        assertTrue(bookCollection.appendCollection("./books.txt"));
        assertEquals(90, bookCollection.getCopy().size());
    }


    @Test
    public void testAppendCollection_Failure() {
        assertFalse(bookCollection.appendCollection("nonexistent.txt"));
    }
    
    @Test
    public void testToString() {
    	Book book1 = new Book("Book1", "AuthorC", 3);
        Book book2 = new Book("Book2", "AuthorA", 4);
        Book book3 = new Book("Book3", "AuthorB", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);
        System.out.println(bookCollection);
        System.out.println(book1);
    }

    @Test
    public void testGetBooksByAuthor() {
        Book book1 = new Book("Book1", "AuthorC", 3);
        Book book2 = new Book("Book2", "AuthorA", 4);
        Book book3 = new Book("Book3", "AuthorB", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);

        ArrayList<Book> sortedBooks = bookCollection.getBooksByAuthor();
        assertEquals(3, sortedBooks.size());
        List<String> authors = Arrays.asList("AuthorA", "AuthorB", "AuthorC");
        for (int i = 0; i < 3; i++) {
            assertEquals(authors.get(i), sortedBooks.get(i).getAuthor());
        }
    }

    @Test
    public void testGetBooksByTitle() {
        Book book1 = new Book("BookC", "AuthorC", 3);
        Book book2 = new Book("BookA", "AuthorA", 4);
        Book book3 = new Book("BookB", "AuthorB", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);

        ArrayList<Book> sortedBooks = bookCollection.getBooksByTitle();
        assertEquals(3, sortedBooks.size());
        List<String> titles = Arrays.asList("BookA", "BookB", "BookC");
        for (int i = 0; i < 3; i++) {
            assertEquals(titles.get(i), sortedBooks.get(i).getTitle());
        }
    }

    @Test
    public void testGetBooksByRead() {
        Book book1 = new Book("Book1", "AuthorA", 4);
        Book book2 = new Book("Book2", "AuthorB", 5);
        Book book3 = new Book("Book3", "AuthorC", 3);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);
        book1.read();
        book2.read();

        ArrayList<Book> readBooks = bookCollection.getBooksByRead();
        assertEquals(2, readBooks.size());
        assertTrue(readBooks.contains(book1));
        assertTrue(readBooks.contains(book2));
        List<String> authors = Arrays.asList("AuthorA", "AuthorB");
        for (int i = 0; i < 2; i++) {
            assertEquals(authors.get(i), readBooks.get(i).getAuthor());
        }

    }

    @Test
    public void testGetBooksByUnread() {
        Book book1 = new Book("Book1", "AuthorA", 4);
        Book book2 = new Book("Book2", "AuthorB", 5);
        Book book3 = new Book("Book3", "AuthorC", 3);
        book1.read();
        book2.read();
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);
        ArrayList<Book> unreadBooks = bookCollection.getBooksByUnread();
        assertEquals(1, unreadBooks.size());
        assertTrue(unreadBooks.contains(book3));
    }

    @Test
    public void testFilterByAuthor(){
        Book book1 = new Book("Book1", "AuthorC", 3);
        Book book2 = new Book("Book2", "AuthorA", 4);
        Book book3 = new Book("Book3", "AuthorB", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);
        bookCollection.FilterByAuthor();
        List<String> authors = Arrays.asList("AuthorA", "AuthorB", "AuthorC");
        for (int i = 0; i < 3; i++) {
            assertEquals(authors.get(i), bookCollection.getCopy().get(i).getAuthor());
        }
    }

    @Test
    public void testFilterByTitle(){
        Book book1 = new Book("BookC", "AuthorC", 3);
        Book book2 = new Book("BookA", "AuthorA", 4);
        Book book3 = new Book("BookB", "AuthorB", 5);
        bookCollection.add(book1);
        bookCollection.add(book2);
        bookCollection.add(book3);
        bookCollection.FilterByTitle();
        List<String> titles = Arrays.asList("BookA", "BookB", "BookC");
        for (int i = 0; i < 3; i++) {
            assertEquals(titles.get(i), bookCollection.getCopy().get(i).getTitle());
        }
    }
    @Test
    public void testBooksRatings() {
    	Book book1 = new Book("BookA", "AuthorA", -20);
    	Book book2 = new Book("BookA", "AuthorA", 100);
    	assertEquals(0, book1.getRating());
    	assertEquals(5, book2.getRating());
    }
}
