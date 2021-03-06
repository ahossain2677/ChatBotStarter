import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBot1 chatbot1 = new ChatBot1();
		ChatBot2 chatbot2 = new ChatBot2();
		ChatBot3 chatbot3 = new ChatBot3();
		

		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the chatbot, nice to meet you.");
		System.out.println("Type 1 to talk with someone that likes movies.");
		System.out.println("Type 2 to talk to ");
		System.out.println("Type 3 to speak to a ghost.");
		String statement = in.nextLine();



		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1

			if (statement == "1")
			{
				chatbot1.chatLoop(statement);
			}
			else if (statement == "2")
			{
				chatbot2.chatLoop(statement);
			}
			else if (statement == "3")
			{
				chatbot3.chatLoop(statement);
			}

			statement = in.nextLine();

		}
	}

}
