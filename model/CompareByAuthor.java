/**
 * File: CompareByAuthor.java
 * Author: Abrorjon Asralov, Pulat Uralov 
 * Username: asralov, uralovpulya
 * 
 * Purpose: This is a little class that uses Comparator interface
 * so we can specifically say to compare objects, in our case, by
 * author
 */

package model;
import java.util.Comparator;

public class CompareByAuthor implements Comparator<Book> {
    public int compare(Book book1, Book book2) {
        return book1.getAuthor().compareTo(book2.getAuthor());
    }
}
