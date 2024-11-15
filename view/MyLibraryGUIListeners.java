package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.BooksCollections;
import model.Book;
import java.util.ArrayList;

public class MyLibraryGUIListeners {

    private BooksCollections booksCol;

    public MyLibraryGUIListeners(BooksCollections booksCollections) {
        this.booksCol = booksCollections;
        booksCollections.appendCollection("books.txt");
    } 

    public void UpdateSearch(String text, String filter, JPanel booksPanel) {
        booksPanel.removeAll();
        if (filter.equals("Author")) {
            booksCol.FilterByAuthor();
            ArrayList<Book> searchBooks = booksCol.searchByAuthorBestMatch(text);
            AddToBookPanel(searchBooks, booksPanel);
            // search by title too, adds books by title after books by author name
            searchBooks = booksCol.searchByTitleBestMatch(text);
            AddToBookPanel(searchBooks, booksPanel);
        } else if (filter.equals("Title")) {
            booksCol.FilterByTitle();
            ArrayList<Book> searchBooks = booksCol.searchByTitleBestMatch(text);
            AddToBookPanel(searchBooks, booksPanel);
            searchBooks = booksCol.searchByAuthorBestMatch(text);
            AddToBookPanel(searchBooks, booksPanel);
        }
        booksPanel.revalidate();
        booksPanel.repaint();
    }

    public void addBooks(File file, JPanel bookPanel)
    {
        booksCol.appendCollection(file.getName());
        this.updateBookPanel(bookPanel);
    }


    public void updateBookPanel(JPanel bookPanel)
    {
        if (booksCol.getCopy().size() == 0)
        {
            bookPanel.setLayout(new BorderLayout());
            JLabel noBooksText = new JLabel("Oopsie, no books in the database . . . ", SwingConstants.CENTER);
            noBooksText.setFont(new Font("Arial", Font.PLAIN, 16)); // Optional: set font size
            noBooksText.setForeground(Color.WHITE);
            bookPanel.add(noBooksText, BorderLayout.CENTER);
            return;
        }
        AddToBookPanel(booksCol.getCopy(), bookPanel);
    }

    private void AddToBookPanel(ArrayList<Book> books, JPanel bookPanel) {
        int bookCount = 0;
        for (Book b: books)
        {
            String author  = b.getAuthor();
            String title = b.getTitle();
            int rating = b.getRating();
            BookBox bookBox = new BookBox(title, author, rating);
        
            bookPanel.add(bookBox);
            bookPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            bookCount++;
            if (bookCount == 20) break;
        }
    }
}

