package view;
/**
 * Authors: Abrorjon Asralov, Pulat Uralov
 * File: MyLibraryGUI.java
 * Class: CSC335 - Fall 24
 * Purpose: 
 */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import model.BooksCollections;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyLibraryGUI 
{
    // making it pubic static so we could reuse it
    // in other class as well
    public static JFrame mainWindow; 

    // storing the command buttons with option box
    // and panel so we can reuse them in other parts
    // of the class
    private JButton addBookButton;
    private JButton addBooksButton;
    private JComboBox<String> optionBox;
    private JPanel searchPanel;
    private JTextField searchBar;
    private  JPanel booksPanel = new JPanel();
    private MyLibraryGUIListeners listeners;   

    /**
     * This is a private constructor that will be called once when program starts
     * running and it creates an instance of this class which works as GUI.
     */
    private MyLibraryGUI() {
        listeners = new MyLibraryGUIListeners(new BooksCollections(), booksPanel);
        // creating a Java Frame
        mainWindow = new JFrame("My Library");
        // height and width of the main window
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


    /**
     * This is a helper button that helps to style buttons. It is 
     * really useful to be used outside of the class as well, we 
     * just pass the refernce to the button in our layout to be
     * changed, and it styles in one way with the same coloring
     * and effects.
     * @param button is J button 
     */
    public static void styleButton(JButton button)
    {
        Color defaultColor = new Color(0,0,0);
        button.setOpaque(true);
        button.setBackground(defaultColor);
        button.setForeground(Color.WHITE);
        // getting off the borders in the buttons
        button.setBorder(BorderFactory.createEmptyBorder());
        // filling the button completely with our desired color
        button.setContentAreaFilled(true);         
       // no focus pain when button is being pressed
        button.setFocusPainted(false);
        // setting a listener so we can have a cool animation
        // of hovering and clicking
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


    /**
     * This is a private helper method that helps to create a header with logo
     * and app name on top. Called once when program starts to set up the header
     * of the app.
     */
    private void createHeader() {
        mainWindow.setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 30, 30)); 
        // using the grid layout for flexible placing
        headerPanel.setLayout(new GridBagLayout()); 
        // gbc so we can put positions in our grid
        GridBagConstraints gbc = new GridBagConstraints(); 
        // scaling the logo of the app
        ImageIcon logoIcon = new ImageIcon("logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        // on the left most side we have our logo
        gbc.gridx = 0;
        gbc.gridy = 0;
        // 10 px padding around the logo
        gbc.insets = new Insets(0, 10, 0, 10); 
        headerPanel.add(logoLabel, gbc);
        // adding the app name
        JLabel appNameLabel = new JLabel("My Library");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        appNameLabel.setForeground(Color.WHITE);
        // adding the name next to our logo
        gbc.gridx = 1; 
        headerPanel.add(appNameLabel, gbc);
        // creating a section for filtering the books {book, title}
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(30, 30, 30)); 
        filterPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        // option box for choosing the filtering
        JLabel filteredByText = new JLabel("Filtered By ");
        filteredByText.setFont(new Font("Arial", Font.PLAIN, 14));
        filteredByText.setForeground(Color.WHITE);
        String[] options = {"Author", "Title"};
        optionBox = new JComboBox<>(options);
        optionBox.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                listeners.UpdateSearch(searchBar.getText(), optionBox.getSelectedItem().toString());
           } 
        });
        // styling
        optionBox.setPreferredSize(new Dimension(150, 25));
        optionBox.setForeground(Color.WHITE);     
        optionBox.setBackground(Color.BLACK);     
        optionBox.setFocusable(false);                   
        optionBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                        boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // centering texts
                label.setHorizontalAlignment(SwingConstants.CENTER); 
                label.setFont(new Font("Arial", Font.PLAIN, 12)); 
                label.setForeground(Color.WHITE);                    
                label.setBackground(Color.BLACK);             
                label.setPreferredSize(new Dimension(150, 25));
                // changing the background color when an item is selected
                // for a cool effect
                if (isSelected) {
                    label.setBackground(new Color(70, 70, 70));  
                }         
                return label;
            }
        });
        optionBox.setBorder(BorderFactory.createEmptyBorder());
        // hiding the default option buttons.
        optionBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton();
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setVisible(false); 
                return arrowButton;
            }
        });
        filterPanel.add(filteredByText);
        filterPanel.add(optionBox);
        gbc.gridx = 2; 
        gbc.weightx = 1.0; 
        gbc.anchor = GridBagConstraints.EAST;
        headerPanel.add(filterPanel, gbc);
        // appending the header panel to the top of the frame
        mainWindow.add(headerPanel, BorderLayout.NORTH);
    }


    /**
     * This is a private helper method that helps to open a dialogue within the main
     * window to make sure that we are getting all needed information from the user
     * about adding the individual book name
     */
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
        // for author field
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
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel ratingLabel = new JLabel("Rating (1-5)");
        ratingLabel.setForeground(Color.WHITE);
        popWindow.add(ratingLabel, gbc);
        // rating options
        String[] ratingOptions = {"1", "2", "3", "4", "5"};
        JComboBox<String> ratingComboBox = new JComboBox<>(ratingOptions);
        // styling
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
                // changing the background color when item is selected
                if (isSelected) {
                    label.setBackground(new Color(70, 70, 70)); 
                }         
                return label;
            }
        });
        ratingComboBox.setBorder(BorderFactory.createEmptyBorder());
        // same procedure, for hiding the default arrow icons in the option box
        ratingComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton();
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setVisible(false); 
                return arrowButton;
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        popWindow.add(ratingComboBox, gbc);
        JButton addBookConfirmButton = new JButton("Add");
        Dimension buttonSize = new Dimension(100, 30);
        addBookConfirmButton.setPreferredSize(buttonSize);
        addBookConfirmButton.setMaximumSize(buttonSize);
        addBookConfirmButton.setMinimumSize(buttonSize);
        styleButton(addBookConfirmButton);
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        popWindow.add(addBookConfirmButton, gbc);
        // adding a listener for confirming the addition of the book
        addBookConfirmButton.addActionListener(e -> {
            String bookTitle = titleField.getText().trim();
            String author = authorField.getText().trim();

            // validating if user have given an author and name for the book
            if (!bookTitle.isEmpty() && !author.isEmpty()) {
                System.out.println("Book added: " + bookTitle + " by " + author);
                listeners.AddSingleBook(bookTitle, author, ratingComboBox.getSelectedIndex()+1);
                popWindow.dispose(); 
            } else {
                // styling the warning window
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.ROMAN_BASELINE, 14));
                UIManager.put("OptionPane.messageForeground", Color.RED);
                JOptionPane.showMessageDialog(popWindow, "Please enter both title and author.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            }
        });
        popWindow.setVisible(true);
    }
    
    /**
     * This is a private helper method to set a side bar of the app 
     */
    private void setupSidebar() {
        // a sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(40, 40, 40)); 
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(300, 0));
        // librarian picture
        JLabel librarianLabel = new JLabel();
        ImageIcon librarianIcon = new ImageIcon("librarian.png"); 
        Image librarianImage = librarianIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        librarianLabel.setIcon(new ImageIcon(librarianImage));
        librarianLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        sidebarPanel.add(librarianLabel);
       // adding the command buttons add book and add books
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        addBookButton = new JButton("ADD BOOK");
        // adding an event listener to the addBook button
        addBookButton.addActionListener(e -> openAddBookWindow());
        addBooksButton = new JButton("ADD BOOKS");
        // adding an event listener to the addBooks button
        addBooksButton.addActionListener(e -> listeners.OpenFileChooser());
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
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        sidebarPanel.add(addBooksButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        // appending sidebar panel to the left of the main window
        mainWindow.add(sidebarPanel, BorderLayout.WEST);
    }

    /**
     * This is a private helper method that helps to create a body part for the app
     * and append it to the main window. It creates a search bar and a scrollable 
     * view box for user to interract with books and see what is out there.
     */
    private void setupBody() {
        // a panel to store components of the body section of out app
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(new Color(45, 45, 45)); 
        bodyPanel.setLayout(new BorderLayout());
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
                listeners.UpdateSearch(searchBar.getText(), optionBox.getSelectedItem().toString());
            }
        });
        listeners.RefreshView();
        // it is for overflowing reasons. If we have many books, it should be a scorrable box where
        // we can scroll a certain area and see books in bigger quantities
        JScrollPane scrollPane = new JScrollPane(booksPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        bodyPanel.add(scrollPane, BorderLayout.CENTER);
        // appending a body to the main window
        mainWindow.add(bodyPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args)
    {
        new MyLibraryGUI();
    }
}
