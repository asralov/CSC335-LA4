package view;

import javax.swing.*;
import java.awt.*;

public class BookBox extends JPanel {
    public BookBox(String title, String author, int rating) {
        // Set layout for BookBox panel
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(650, 100)); // Adjust as necessary
        setMaximumSize(new Dimension(650, 100));
        setMinimumSize(new Dimension(650, 100));
        setBackground(new Color(50, 50, 50)); // Background color for the box
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left side: Book Icon
        
        // Create a panel to hold the icon and center it vertically
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // This centers the icon horizontally
        iconPanel.setLayout(new BorderLayout());  // You can use FlowLayout or BoxLayout here as well
        iconPanel.setPreferredSize(new Dimension(50, 120));  // Adjust this based on your box size

        // Create the ImageIcon and scale it
        ImageIcon bookIcon = new ImageIcon("book.png");
        Image bookImage = bookIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

        // Create the label for the book image
        JLabel iconLabel = new JLabel(new ImageIcon(bookImage));

        // Add the iconPa
        add(iconLabel, BorderLayout.WEST);

        // Center: Title, Author, and Rating
        JPanel infoPanel = new JPanel();
        infoPanel.add(Box.createRigidArea(new Dimension(0, 25))); 
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(50, 50, 50)); // Same background color for seamless look

        // Title label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        infoPanel.add(titleLabel);

        // Author label
        JLabel authorLabel = new JLabel("by " + author + " (" + rating + " starts)");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        authorLabel.setForeground(Color.LIGHT_GRAY);
        infoPanel.add(authorLabel);

        add(infoPanel, BorderLayout.CENTER);

        // Right side: Rate Button
        JButton rateButton = new JButton();

        // Load the star image (make sure "star.png" is in your project folder)
        ImageIcon starIcon = new ImageIcon("star.png");
        Image starPic = starIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        rateButton.setIcon(new ImageIcon(starPic));

        // styling the button
        rateButton.setContentAreaFilled(false);
        rateButton.setBorderPainted(false);
        rateButton.setFocusPainted(false);

        Dimension size = new Dimension(50,5);
        rateButton.setPreferredSize(size);
        rateButton.setMinimumSize(size);
        rateButton.setMaximumSize(size);
        add(rateButton, BorderLayout.EAST);
    }
}
