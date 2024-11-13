package view;

import javax.swing.JPanel;

import model.BooksCollections;

public class MyLibraryGUIListeners {

    private BooksCollections booksCol;

    public MyLibraryGUIListeners(BooksCollections booksCollections) {
        this.booksCol = booksCollections;
        booksCollections.appendCollection("books.txt");
    } 

    public void UpdateSearch(String text, String filter, JPanel panel) {
        System.out.println(text + " " + filter + " " + panel );
        
    }
}
