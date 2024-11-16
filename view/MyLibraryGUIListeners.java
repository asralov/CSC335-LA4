/*
 * File: MyLibraryGUIListeners.java
 * Authors: Pulat Uralov & Abror Asralov
 * Purpose: This is the controller component of MyLibraryGUI,
 * transfers data from BooksCollections to MyLibraryGUI
 * and from MyLibraryGUI to BooksCollections
 */

package view;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import model.BooksCollections;
import model.Book;

public class MyLibraryGUIListeners {

    private BooksCollections booksCol;
    private JPanel booksPanel;
    private String currentSearchText;
    private String currentFilter;

    /**
     * @param booksCol - instance of BooksCollections class
     * @param booksPanel - JPanel that displayes the list of books
     */
    public MyLibraryGUIListeners(BooksCollections booksCol, JPanel booksPanel) {
        this.booksCol = booksCol;
        this.booksPanel = booksPanel;
    }

    /**
     * UpdateSearch -- is called every time the search bar is changed. Updates
     * currentSearchText and currentFilter, and calls PerformSearch
     * @param searchText - current text that's in the search bar
     * @param filter - current filter selected
     */
    public void UpdateSearch(String searchText, String filter) {
        this.currentSearchText = searchText;
        this.currentFilter = filter;
        PerformSearch();
    }

    /**
     * PerformSearch -- find books using the current search text and filter
     * and adds them to the panel
     */
    private void PerformSearch() {
        // if search is empty then call RefreshView and return
        if (currentSearchText == null) {
            RefreshView();
            return;
        }
        booksPanel.removeAll();

        ArrayList<Book> searchResults = new ArrayList<>();
        if (currentFilter.equals("Author")) {
                booksCol.FilterByAuthor();
                searchResults = booksCol.searchByAuthorBestMatch(currentSearchText);
        }
        else if (currentFilter.equals("Title")) {
                booksCol.FilterByTitle();
                searchResults = booksCol.searchByTitleBestMatch(currentSearchText);
        }

        PopulateBooksPanel(searchResults);
        RefreshBooksPanel();
    }

    /**
     * AddSingleBook -- adds one book to booksCol
     * @param title - title of the new book
     * @param author - author of the new book
     * @param rating - rating of the new book
     */
    public void AddSingleBook(String title, String author, int rating) {
        Book newBook = new Book(title, author);
        newBook.updateRating(rating);
        booksCol.add(newBook);
        RefreshView();
    }

    /**
     * UpdateRating -- updates the rating of a book
     * @param title - title of the book to update
     * @param rating - new rating
     */
    public void UpdateRating(String title, int rating) {
        Book bookToUpdate = booksCol.searchByTitle(title).stream().findFirst().orElse(null);
        if (bookToUpdate != null) {
            bookToUpdate.updateRating(rating);
            PerformSearch();
        }
    }

    /**
     * RefreshView -- if no books present, call ShowNoBooksMessage,
     * otherwise call PopulateBooksPanel. Call RefreshBooksPanel
     */
    public void RefreshView() {
        if (booksCol.getCopy().isEmpty()) {
            ShowNoBooksMessage();
        } else {
            PopulateBooksPanel(booksCol.getCopy());
        }
        RefreshBooksPanel();
    }

    /**
     * PopulateBooksPanel -- adds books boxes to the panel. Only shows
     * 20 or fewer books at a time to increase performance
     * @param books
     */
    private void PopulateBooksPanel(ArrayList<Book> books) {
        books = new ArrayList<>(new HashSet<>(books)); // Remove duplicates
        booksPanel.removeAll();
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < Math.min(books.size(), 20); i++) {
            Book book = books.get(i);
            BookBox bookBox = new BookBox(book.getTitle(), book.getAuthor(), book.getRating(), this);
            booksPanel.add(bookBox);
            booksPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    /**
     * ShowNoBooksMessage -- shows a message in the middle of the panel in case there 
     * are no books present in booksCol
     */
    private void ShowNoBooksMessage() {
        booksPanel.setLayout(new BorderLayout());
        JLabel noBooksLabel = new JLabel("No books in the database.", SwingConstants.CENTER);
        noBooksLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        noBooksLabel.setForeground(Color.WHITE);
        booksPanel.add(noBooksLabel, BorderLayout.CENTER);
    }

    /**
     * OpenFileChooser -- appends a collection of books to booksCol
     */
    public void OpenFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a File");

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            booksCol.appendCollection(selectedFile.getName());
            RefreshView();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("File selection canceled.");
        }
    }

    /**
     * RefreshBooksPanel -- refreshed booksPanel with current elements 
     */
    private void RefreshBooksPanel() {
        booksPanel.revalidate();
        booksPanel.repaint();
    }
}
