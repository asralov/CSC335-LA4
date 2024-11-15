package view;

// AUTHOR:M ABRORJON ASRALOV, PU TSOY
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

import model.BooksCollections;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
<<<<<<< Updated upstream
import java.io.File;
=======
>>>>>>> Stashed changes


public class MyLibraryGUI 
{
    public static JFrame mainWindow;

    // command buttons
    private JButton addBookButton;
    private JButton addBooksButton;
    private JComboBox<String> optionBox;
    private JPanel searchPanel;
    private JTextField searchBar;
    private  JPanel booksPanel;
    private MyLibraryGUIListeners listeners;   

    private MyLibraryGUI() {
        listeners = new MyLibraryGUIListeners(new BooksCollections());
        // creating a Java Frame
        mainWindow = new JFrame("My Library");
        // height and width
        mainWindow.setSize(1250, 750);
        // making the default location to be centered
        mainWindow.setLocationRelativeTo(null);
        // creating a header
        createHeader();
        // creating a sidebar
        setupSidebar();
        // creating a body`
        setupBody();
        // makinf the close opereation to exit the program
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // showing the window
        mainWindow.setVisible(true);
    }


    public static void styleButton(JButton button)
    {
        Color defaultColor = new Color(0,0,0);
        button.setOpaque(true);
        button.setBackground(defaultColor);
        button.setForeground(Color.WHITE);

        // Remove the default border if not needed
        button.setBorder(BorderFactory.createEmptyBorder());
        
        // Disable the content area fill to avoid default button background color effects
        button.setContentAreaFilled(true);            // true if you want the color to cover the button completely

        // Optional: Remove focus paint (e.g., dotted border when clicked or tabbed to)
        button.setFocusPainted(false);
        //button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // can change the border

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                button.setBackground(new Color(55,55, 55));
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                button.setBackground(defaultColor);
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                button.setBackground(new Color(80, 80, 80));
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                button.setBackground(new Color(55,55,55));
            }
        });
    }


    private void createHeader() {
        mainWindow.setLayout(new BorderLayout());
        
        // Create a panel for the header with GridBagLayout
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 30, 30)); // Background color
        headerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexibility
    
        // Create a GridBagConstraints object for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Logo setup
        ImageIcon logoIcon = new ImageIcon("logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        
        // Add logo to the left side
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.insets = new Insets(0, 10, 0, 10); // Padding around the logo
        headerPanel.add(logoLabel, gbc);
    
        // App name
        JLabel appNameLabel = new JLabel("My Library");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        appNameLabel.setForeground(Color.WHITE); // Set text color to white
        
        // Add app name next to the logo
        gbc.gridx = 1; // Column 1
        headerPanel.add(appNameLabel, gbc);
    
        // Create a separate panel for filters
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(30, 30, 30)); // Match the header panel color
        filterPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Right alignment for combo boxes
    
        // Author filter combo box
        JLabel filteredByText = new JLabel("Filtered By ");
        filteredByText.setFont(new Font("Arial", Font.PLAIN, 14));
        filteredByText.setForeground(Color.WHITE); // Set text color to white

        String[] options = {"Author", "Title"};
        optionBox = new JComboBox<>(options);
        optionBox.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                listeners.UpdateSearch(searchBar.getText(), optionBox.getSelectedItem().toString(), booksPanel);
           } 
        });
        // Basic styling
        optionBox.setPreferredSize(new Dimension(150, 25));
        optionBox.setForeground(Color.WHITE);     // Set text color
        optionBox.setBackground(Color.BLACK);     // Set background color
        optionBox.setFocusable(false);                   
    
        // Custom renderer to apply background color to the main part of the combo box
        optionBox.setRenderer(new DefaultListCellRenderer() {
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

        optionBox.setBorder(BorderFactory.createEmptyBorder());
        // Hide the arrow icon
        optionBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                // Return a button with no icon or background to hide the arrow
                JButton arrowButton = new JButton();
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setVisible(false);  // Hide the button
                return arrowButton;
            }
        });

        filterPanel.add(filteredByText);
        filterPanel.add(optionBox);


    
        // Add the filter panel to the right side
        gbc.gridx = 2; // Column 2
        gbc.weightx = 1.0; // Allow the filter panel to take available space
        gbc.anchor = GridBagConstraints.EAST; // Align to the right
        headerPanel.add(filterPanel, gbc);
    
        // Add the header panel to the top of the frame
        mainWindow.add(headerPanel, BorderLayout.NORTH);
    }

    private void openAddBookWindow()
    {
        JDialog popWindow = new JDialog(mainWindow, "Add a New Book", true);
        popWindow.setSize(400, 250);
        popWindow.setLocationRelativeTo(mainWindow);
        popWindow.getContentPane().setBackground(new Color(45,45,45));
        // adding a grid layout for text fields
        popWindow.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel b = new JLabel("Book Title");
        b.setForeground(Color.WHITE);
        popWindow.add(b, gbc);

        JTextField titleField = new JTextField(20);
        titleField.setPreferredSize(new Dimension(100, 30));
        titleField.setFont(new Font("Arial", Font.PLAIN, 14));
        titleField.setForeground(Color.WHITE);
        titleField.setBackground(new Color(70, 70, 70));
        titleField.setCaretColor(Color.WHITE);
        titleField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        gbc.gridx = 1; gbc.gridy = 0;
        popWindow.add(titleField, gbc);

        // Author label and text field
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel a = new JLabel("Author");
        a.setForeground(Color.WHITE);
        popWindow.add(a, gbc);
        
        JTextField authorField = new JTextField(20);
        authorField.setPreferredSize(new Dimension(100, 30));
        authorField.setFont(new Font("Arial", Font.PLAIN, 14));
        authorField.setForeground(Color.WHITE);
        authorField.setBackground(new Color(70, 70, 70));
        authorField.setCaretColor(Color.WHITE);
        authorField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        gbc.gridx = 1; gbc.gridy = 1;
        popWindow.add(authorField, gbc);


        // Star Rating Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel ratingLabel = new JLabel("Rating (1-5)");
        ratingLabel.setForeground(Color.WHITE);
        popWindow.add(ratingLabel, gbc);
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

        // Star Rating field (using JComboBox)
        gbc.gridx = 1;
        gbc.gridy = 2;
        popWindow.add(ratingComboBox, gbc);

        // Add button to submit information and styling it
        JButton addBookConfirmButton = new JButton("Add");
        Dimension buttonSize = new Dimension(100, 30);
        addBookConfirmButton.setPreferredSize(buttonSize);
        addBookConfirmButton.setMaximumSize(buttonSize);
        addBookConfirmButton.setMinimumSize(buttonSize);
        styleButton(addBookConfirmButton);
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        popWindow.add(addBookConfirmButton, gbc);

        // Action listener for confirming the addition of the book
        addBookConfirmButton.addActionListener(e -> {
            String bookTitle = titleField.getText().trim();
            String author = authorField.getText().trim();

            // Simple validation
            if (!bookTitle.isEmpty() && !author.isEmpty()) {
                // You can add the book to your collection here
                System.out.println("Book added: " + bookTitle + " by " + author);
                listeners.AddSingleBook(bookTitle, author, ratingComboBox.getSelectedIndex()+1, booksPanel);
                popWindow.dispose(); // Close the dialog
            } else {
                // styling the warning window
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.ROMAN_BASELINE, 14));
                UIManager.put("OptionPane.messageForeground", Color.RED);
                JOptionPane.showMessageDialog(popWindow, "Please enter both title and author.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            }
        });

        popWindow.setVisible(true);
    }
    
    private void setupSidebar() {
        // Create a sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(40, 40, 40)); // Darker gray for sidebar
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(300, 0)); // Set sidebar width

        //Librarian picture
        JLabel librarianLabel = new JLabel();
        ImageIcon librarianIcon = new ImageIcon("librarian.png"); 
        Image librarianImage = librarianIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        librarianLabel.setIcon(new ImageIcon(librarianImage));
        librarianLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing at top
        sidebarPanel.add(librarianLabel);

       //Command buttons
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between image and buttons
        addBookButton = new JButton("ADD BOOK");

        // adding an event listener to the addBook button
        addBookButton.addActionListener(e -> openAddBookWindow());

        addBooksButton = new JButton("ADD BOOKS");

        // adding an event listener to the addBooks button
        addBooksButton.addActionListener(e -> listeners.openFileChooser(booksPanel));

        Dimension buttonSize = new Dimension(200, 40);
        addBookButton.setPreferredSize(buttonSize);
        addBookButton.setMaximumSize(buttonSize);
        addBookButton.setMinimumSize(buttonSize);
        
        addBooksButton.setPreferredSize(buttonSize);
        addBooksButton.setMaximumSize(buttonSize);
        addBooksButton.setMinimumSize(buttonSize);
        
        addBookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBooksButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // styling the buttons
        styleButton(addBookButton);
        styleButton(addBooksButton);

        sidebarPanel.add(addBookButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        sidebarPanel.add(addBooksButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add sidebar panel to the left of the main window
        mainWindow.add(sidebarPanel, BorderLayout.WEST);
    }


    private void setupBody() {
        // Create a panel for the body
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(new Color(45, 45, 45)); // Darker background for the body
        bodyPanel.setLayout(new BorderLayout()); // Layout for body panel

      
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(new Color(45, 45, 45));

        // adding a search bar
        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(650, 30));
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
        searchBar.setForeground(Color.WHITE);
        searchBar.setBackground(new Color(70, 70, 70));
        searchBar.setCaretColor(Color.WHITE);
        searchBar.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        searchPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        searchPanel.add(searchBar);
        bodyPanel.add(searchPanel, BorderLayout.NORTH);

        booksPanel = new JPanel(); // main inner pannel for showing books
        booksPanel.setBackground(new Color(45, 45, 45));
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS));

        // ---------- Sarch Bar Listener ----------
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                Update();
            }
            public void removeUpdate(DocumentEvent e) {
                Update();
            }
            public void insertUpdate(DocumentEvent e) {
                Update();
            }

            public void Update() {
                listeners.UpdateSearch(searchBar.getText(), optionBox.getSelectedItem().toString(), booksPanel);
            }
        });
    

        // if (books.length == 0)
        // {
        //     booksPanel.setLayout(new BorderLayout());
        //     JLabel noBooksText = new JLabel("Oopsie, no books in the database . . . ", SwingConstants.CENTER);
        //     noBooksText.setFont(new Font("Arial", Font.PLAIN, 16)); // Optional: set font size
        //     noBooksText.setForeground(Color.WHITE);
        //     booksPanel.add(noBooksText, BorderLayout.CENTER);
        // }

        // else 
        // {
        //     addBooksToPanel(booksPanel);
        // }
        listeners.updateBookPanel(booksPanel);


        // Add booksPanel to a scroll pane for scrolling
        JScrollPane scrollPane = new JScrollPane(booksPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Faster scrolling speed
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        bodyPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the body panel to the frame
        mainWindow.add(bodyPanel, BorderLayout.CENTER);
    }


    public static void main(String[] args)
    {
        new MyLibraryGUI();
    }
}
