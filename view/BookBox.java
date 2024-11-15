package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.*;

public class BookBox extends JPanel {
    private JButton rateButton;
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
        rateButton = new JButton();
        rateButton.addActionListener(e -> openRateWindow());

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

    public void openRateWindow()
    {

        JDialog popWindow = new JDialog(MyLibraryGUI.mainWindow, "Rate the Book", true);
        popWindow.setSize(400, 250);
        popWindow.setLocationRelativeTo(MyLibraryGUI.mainWindow);
        popWindow.getContentPane().setBackground(new Color(45,45,45));
        popWindow.setLayout(new GridBagLayout());

        String[] ratingOptions = {"1", "2", "3", "4", "5"};
        JComboBox<String> ratingComboBox = new JComboBox<>(ratingOptions);
        
        // Basic styling
        ratingComboBox.setPreferredSize(new Dimension(150, 25));
        ratingComboBox.setForeground(Color.WHITE);     // Set text color
        ratingComboBox.setBackground(Color.BLACK);     // Set background color
        ratingComboBox.setFocusable(false);                   
    

        // Custom renderer to apply background color to the main part of the combo box
        ratingComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                        boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
                label.setFont(new Font("Arial", Font.PLAIN, 12));    // Keep font consistent
                label.setForeground(Color.WHITE);                    // Text color
                label.setBackground(Color.BLACK);                    // Background color
                label.setPreferredSize(new Dimension(150, 25));


                // Change the background color when an item is selected
                if (isSelected) {
                    label.setBackground(new Color(70, 70, 70));      // Slightly different color for selection
                }         
                return label;
            }
        });

        ratingComboBox.setBorder(BorderFactory.createEmptyBorder());
        // Hide the arrow icon
        ratingComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                // Return a button with no icon or background to hide the arrow
                JButton arrowButton = new JButton();
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setVisible(false);  // Hide the button
                return arrowButton;
            }
        });


        // Create and style the "Rate" button
        JButton rateButton = new JButton("RATE");
        rateButton.setPreferredSize(new Dimension(150, 25));
        rateButton.setMinimumSize(new Dimension(150, 25));
        rateButton.setMaximumSize(new Dimension(150, 25));
        rateButton.setForeground(Color.WHITE);
        rateButton.setFont(new Font("Arial", Font.PLAIN, 12));
        MyLibraryGUI.styleButton(rateButton);

        // Add action listener to the button if needed
        rateButton.addActionListener(e -> {
            String selectedRating = (String) ratingComboBox.getSelectedItem();
            System.out.println("Rating selected: " + selectedRating);
            popWindow.dispose(); // Close the dialog after rating is selected
        });

        // Use GridBagConstraints to center the components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Add some spacing between components
        gbc.anchor = GridBagConstraints.CENTER;

        popWindow.add(ratingComboBox, gbc); // Add combo box to dialog

        gbc.gridx = 1; // Move to the next row for the button
        popWindow.add(rateButton, gbc); // Add rate button below the combo box

        popWindow.setVisible(true);
        
    }
}
