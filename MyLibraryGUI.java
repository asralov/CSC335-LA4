import javax.swing.*;

import java.awt.*;
import java.io.File;;
public class MyLibraryGUI 
{
    private static JFrame mainWindow;

    // command buttons
    private static JButton addBookButton;
    private static JButton addBooksButton;
    private static JButton searchButton;
    

    private static void setUp()
    {
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
        // creating a body
        setupBody();
        // makinf the close opereation to exit the program
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // showing the window
        mainWindow.setVisible(true);
    }

    private static void createHeader()
    {
        mainWindow.setLayout(new BorderLayout());
        // Create a panel for the header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30,30,30)); // rgb: 70,130,180
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        
        ImageIcon logoIcon = new ImageIcon("logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        headerPanel.add(logoLabel);

        
        // Add the app name next to the logo
        JLabel appNameLabel = new JLabel("My Library");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        appNameLabel.setForeground(Color.WHITE); // Set text color to white
        headerPanel.add(appNameLabel);

        // Add the header panel to the top of the frame
        mainWindow.add(headerPanel, BorderLayout.NORTH);
    }

    private static void setupSidebar() {
        // Create a sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(40, 40, 40)); // Darker gray for sidebar
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(300, 0)); // Set sidebar width

        //Section 1: Librarian picture
        JLabel librarianLabel = new JLabel();
        ImageIcon librarianIcon = new ImageIcon("librarian.png"); 
        Image librarianImage = librarianIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        librarianLabel.setIcon(new ImageIcon(librarianImage));
        librarianLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing at top
        sidebarPanel.add(librarianLabel);

        // JLabel librarianLabel = new JLabel(new ImageIcon("librarian.png"));
        // librarianLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing at top
        // sidebarPanel.add(librarianLabel);

        // Section 2: Command buttons
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between image and buttons
        addBookButton = new JButton("ADD BOOK");
        addBooksButton = new JButton("ADD BOOKS");
        searchButton = new JButton("SEARCH");
        Dimension buttonSize = new Dimension(200, 40);
        addBookButton.setPreferredSize(buttonSize);
        addBookButton.setMaximumSize(buttonSize);
        addBookButton.setMinimumSize(buttonSize);
        
        addBooksButton.setPreferredSize(buttonSize);
        addBooksButton.setMaximumSize(buttonSize);
        addBooksButton.setMinimumSize(buttonSize);
        
        searchButton.setPreferredSize(buttonSize);
        searchButton.setMaximumSize(buttonSize);
        searchButton.setMinimumSize(buttonSize);
        addBookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBooksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebarPanel.add(addBookButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        sidebarPanel.add(addBooksButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(searchButton);

        // Add sidebar panel to the left of the main window
        mainWindow.add(sidebarPanel, BorderLayout.WEST);
    }


    private static void setupBody() {
        // Create a panel for the body
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(new Color(45, 45, 45)); // Darker background for the body
        bodyPanel.setLayout(new BorderLayout()); // Layout for body panel

        // You can add components to the body panel here
        JLabel bodyLabel = new JLabel("Welcome to My Library!");
        bodyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        bodyLabel.setForeground(Color.WHITE); // Set text color to white
        bodyLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the label

        // Add the body label to the center of the body panel
        bodyPanel.add(bodyLabel, BorderLayout.CENTER);

        // Add the body panel to the frame
        mainWindow.add(bodyPanel, BorderLayout.CENTER);
    }


    public static void main(String[] args)
    {
        setUp();
    }
}
