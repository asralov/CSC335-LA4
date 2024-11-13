package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.BooksCollections;
import model.Book;

public class MyLibraryGUIListeners {

    private BooksCollections booksCol;

    public MyLibraryGUIListeners(BooksCollections booksCollections) {
        this.booksCol = booksCollections;
        booksCollections.appendCollection("books2.txt");
    } 

    public void UpdateSearch(String text, String filter, JPanel panel) {
        System.out.println(text + " " + filter + " " + panel );
        
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
        for (Book b: booksCol.getCopy())
        {
            String author  = b.getAuthor();
            String title = b.getTitle();
            int rating = b.getRating();
            BookBox bookBox = new BookBox(title, author, rating);
        
            bookPanel.add(bookBox);
            bookPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }
}

