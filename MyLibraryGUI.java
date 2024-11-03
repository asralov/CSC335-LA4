import javax.swing.*;

import java.awt.*;;
public class MyLibraryGUI 
{
    private static JFrame mainWindow;

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
