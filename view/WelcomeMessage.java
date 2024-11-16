package view;

public class WelcomeMessage 
{
	private String message;
	public WelcomeMessage()
	{
		// added a comment
		this.message = generateWelcomeMessage();
	}
	
	
    public String generateWelcomeMessage() 
    {
    	String line1 = "*****************************************************************";
    	String line2 = "*                                                               *";
    	String line3 = "*                   Welcome To MyLibrary App!                   *";
    	String line4 = "*                                                               *";
    	String line5 = "*                    Your books, Your rules                     *";
    	String line6 = "*                                                               *";
    	String line7 = "*****************************************************************";
    	return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n"+line5 + "\n" + line6 + "\n" + line7;
    }
    
    public String toString()
    {
    	return this.message;
    }
}
