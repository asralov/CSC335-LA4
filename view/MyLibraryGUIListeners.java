package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
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
        //booksCollections.appendCollection("books.txt");
    } 

    public void UpdateSearch(String text, String filter, JPanel booksPanel) {
        booksPanel.removeAll();
        if (filter.equals("Author")) {
            booksCol.FilterByAuthor();
            ArrayList<Book> searchBooks = booksCol.searchByAuthorBestMatch(text);
            System.out.println("Search book size: " + searchBooks.size());
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
        RefreshBookList(booksPanel);
    }

    public void addBooks(File file, JPanel bookPanel)
    {
        booksCol.appendCollection(file.getName());
        updateBookPanel(bookPanel);
        RefreshBookList(bookPanel);
    }


    public void updateBookPanel(JPanel bookPanel)
    {
        System.out.println(booksCol.getCopy().size());
        if (booksCol.getCopy().size() == 0)
        {
            bookPanel.setLayout(new BorderLayout());
            JLabel noBooksText = new JLabel("Oopsie, no books in the database . . . ", SwingConstants.CENTER);
            noBooksText.setFont(new Font("Arial", Font.PLAIN, 16)); // Optional: set font size
            noBooksText.setForeground(Color.WHITE);
            bookPanel.add(noBooksText, BorderLayout.CENTER);
            return;
        } else {
            System.out.println("yes");
            bookPanel.removeAll();
            //bookPanel = new JPanel(); // main inner pannel for showing books
            bookPanel.setBackground(new Color(45, 45, 45));
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
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

    public void openFileChooser(JPanel booksPanel) {
        File selectedFile;
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
    
        // Set file chooser properties, such as the dialog title
        fileChooser.setDialogTitle("Select a file");
    
        // Show the file chooser dialog and capture the user response
        int userSelection = fileChooser.showOpenDialog(null);
    
        // Check if the user selected a file (approved the selection)
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Get the selected file and store it in the variable
            selectedFile = fileChooser.getSelectedFile();
            addBooks(selectedFile, booksPanel);
    
            // Optional: Display a message or perform an action with the selected file
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("File selection cancelled by user.");
        }
    }

    public void AddSingleBook(String title, String author, int rating, JPanel booksPanel) {
        System.out.println(title + " " + rating + " " + author);
        Book newBook = new Book(title, author);
        newBook.updateRating(rating);
        booksCol.add(newBook);
        updateBookPanel(booksPanel);
        RefreshBookList(booksPanel);
    }

    private void RefreshBookList(JPanel booksPanel) {
        booksPanel.revalidate();
        booksPanel.repaint();
    }
}

