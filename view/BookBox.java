package view;
/**
 * Author: Abrorjon Asralov, Pulat Uralov
 * File: BookBox.java
 * Class: CSC335 - Fall 24
 * Purpose: helps creating a box with a picture of the default
 *          book, auhor name, title and rate button to be shown
 *          in the scrollable area, so user could interract with it
 */
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class BookBox extends JPanel {
    private JButton rateButton;
    private MyLibraryGUIListeners listeners;
    private String title;
    public BookBox(String title, String author, int rating, MyLibraryGUIListeners myLibraryGUIListeners) {
        this.listeners = myLibraryGUIListeners;
        this.title = title;
        // starting setting the layout for boxes
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(650, 100)); 
        setMaximumSize(new Dimension(650, 100));
        setMinimumSize(new Dimension(650, 100));
        setBackground(new Color(50, 50, 50));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // book icon panel
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  
        iconPanel.setLayout(new BorderLayout());  
        iconPanel.setPreferredSize(new Dimension(50, 120));  
        ImageIcon bookIcon = new ImageIcon("book.png");
        Image bookImage = bookIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(bookImage));
        add(iconLabel, BorderLayout.WEST);
        // info panel is about showing author and title of the book with rating
        JPanel infoPanel = new JPanel();
        infoPanel.add(Box.createRigidArea(new Dimension(0, 25))); 
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(50, 50, 50)); 
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        infoPanel.add(titleLabel);
        JLabel authorLabel = new JLabel("by " + author + " (" + rating + " starts)");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        authorLabel.setForeground(Color.LIGHT_GRAY);
        infoPanel.add(authorLabel);
        add(infoPanel, BorderLayout.CENTER);
        // adding a rate button which helps to update the rating of the book
        rateButton = new JButton();
        rateButton.addActionListener(e -> openRateWindow());
        ImageIcon starIcon = new ImageIcon("star.png");
        // adding a star icon for our rate button
        Image starPic = starIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        rateButton.setIcon(new ImageIcon(starPic));
        rateButton.setContentAreaFilled(false);
        rateButton.setBorderPainted(false);
        rateButton.setFocusPainted(false);
        Dimension size = new Dimension(50,5);
        rateButton.setPreferredSize(size);
        rateButton.setMinimumSize(size);
        rateButton.setMaximumSize(size);
        add(rateButton, BorderLayout.EAST);
    }

    /**
     * Helps to open a dialogue window to help rating the book
     */
    public void openRateWindow()
    {
        JDialog popWindow = new JDialog(MyLibraryGUI.mainWindow, "Rate the Book", true);
        popWindow.setSize(400, 250);
        popWindow.setLocationRelativeTo(MyLibraryGUI.mainWindow);
        popWindow.getContentPane().setBackground(new Color(45,45,45));
        popWindow.setLayout(new GridBagLayout());
        String[] ratingOptions = {"1", "2", "3", "4", "5"};
        JComboBox<String> ratingComboBox = new JComboBox<>(ratingOptions);
        ratingComboBox.setPreferredSize(new Dimension(150, 25));
        ratingComboBox.setForeground(Color.WHITE);
        ratingComboBox.setBackground(Color.BLACK);  
        ratingComboBox.setFocusable(false);                   
        ratingComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                        boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER); 
                label.setFont(new Font("Arial", Font.PLAIN, 12));  
                label.setForeground(Color.WHITE);                    
                label.setBackground(Color.BLACK);                    
                label.setPreferredSize(new Dimension(150, 25));
                if (isSelected) {
                    label.setBackground(new Color(70, 70, 70));     
                }         
                return label;
            }
        });
        ratingComboBox.setBorder(BorderFactory.createEmptyBorder());
        ratingComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton();
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setVisible(false); 
                return arrowButton;
            }
        });
        JButton rateButton = new JButton("RATE");
        rateButton.setPreferredSize(new Dimension(150, 25));
        rateButton.setMinimumSize(new Dimension(150, 25));
        rateButton.setMaximumSize(new Dimension(150, 25));
        rateButton.setForeground(Color.WHITE);
        rateButton.setFont(new Font("Arial", Font.PLAIN, 12));
        MyLibraryGUI.styleButton(rateButton);
        rateButton.addActionListener(e -> {
            String selectedRating = (String) ratingComboBox.getSelectedItem();
            listeners.UpdateRating(title, Integer.parseInt(ratingComboBox.getSelectedItem().toString()));
            System.out.println("Rating selected: " + selectedRating);
            popWindow.dispose(); 
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); 
        gbc.anchor = GridBagConstraints.CENTER;
        popWindow.add(ratingComboBox, gbc);
        gbc.gridx = 1; 
        popWindow.add(rateButton, gbc); 
        popWindow.setVisible(true);
    }
}
