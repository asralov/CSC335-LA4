/**
 * File: CompareByTitle.java
 * Author: Abrorjon Asralov, Pulat Uralov
 * Username: asralov, uralovpulya
 * 
 * Purpose: This is a little class that uses Comparator interface
 * so we can specifically say to compare objects, in our case, by
 * title
 */

package model;
import java.util.Comparator;

public class CompareByTitle implements Comparator<Book>{
    public int compare(Book book1, Book book2) {
        return book1.getTitle().compareTo(book2.getTitle());
    }
}
