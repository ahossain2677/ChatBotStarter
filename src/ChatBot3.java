import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
// Judy Leung
public class ChatBot3
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;



	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
        ChatBot1 chatbot1 = new ChatBot1();
        ChatBot2 chatbot2 = new ChatBot2();

		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());


		while (!statement.equals("Bye"))
		{
            if (statement == "1")
            {
                chatbot1.chatLoop(statement);
            }
            else if (statement == "2")
            {
                chatbot2.chatLoop(statement);
            }


			statement = in.nextLine();
			statement = statement.toLowerCase();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));


		}

	}
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "HELLO.. ?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			int i = 1;
			{
				response = "WHAT...? I CAN'T SEE TEXT THAT SMALL... ";
				i++;
			}
			if (i == 2)
			{
				response = "I STILL CAN'T SEE ANYTHING. THEY DON'T GIVE YOU NEW GLASSES WHEN YOU'RE DEAD, YOU KNOW.";
				i++;
			}
			if (i == 3)
			{
				response = "SEE, THE REASON WHY I TYPE LIKE THIS IS SO PROBLEMS LIKE THESE CAN BE AVOIDED. EVERYONE CAN SEE TEXT THIS BIG.... I HOPE.";
				i++;
			}
			if ( i == 4)
			{
				response = "DID YOU LEAVE...?";
				i++;
			}
			if (i == 5)
			{
				response = ".............";
			}

		}

		else if (findKeyword(statement, "know") >= 0)
		{
			int i = 1;
			if (i == 1 )
			{
				response = "I USED TO KNOW A LOT OF THINGS TOO, BUT I'VE FORGOTTEN A LOT OVER THE PAST YEARS IN THIS DUSTY OLD CEMETERY... TELL ME WHAT YOU KNOW.";
			}
			else
			{
				response = "GO ON.";
			}
		}
		
		else if (findKeyword(statement, "folwell") >= 0)
		{
			response = "HEY, I KNOW SOMEONE NAMED FOLWELL... OR, I USED TO, I THINK..";
			emotion++;
		}

		else if (findKeyword(statement, "where") >= 0 )
		{
			if (findKeyword(statement,"you") >= 0)
			{
				if (findKeyword(statement, "live") >= 0)
				{
					response = "I LIVE IN THE CEMETERY. BUT IT'S MORE LIKE.... I JUST EXIST AROUND THE AREA. IT'S PLAIN AND UNINTERESTING. SOMETIMES, THERE ARE BIRDS. BUT WHEN IT RAINS, IT GETS ESPECIALLY BORING.";
				}
				if (findKeyword(statement,"are") >= 0)
				{
					response = "I'M RIGHT HERE AREN'T I?";
				}
				else
				{
					response = getRandomResponse();
				}
			}
			else
			{
				response = getRandomResponse();
			}
		}

		// Response transforming I want to statement
		else if (findKeyword(statement, "i want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "i want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}
		else if (findKeyword(statement,"i like", 0) >= 0 )
		{
			response = transformILikeStatement(statement);
		}
		else if (findKeyword(statement,"i") >= 0)
		{
			if (findKeyword(statement,"you") >= 0)
			{
				response = transformIYouStatement(statement);
			}
		}
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "WHY DO YOU WANT TO " + restOfStatement.toUpperCase() + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "i want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "WOULD YOU REALLY BE HAPPY IF YOU HAD " + restOfStatement.toUpperCase() + "?";
	}

	private String transformILikeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "i like", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "WHAT DO YOU LIKE ABOUT " + restOfStatement.toUpperCase() + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "i", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "WHY DO YOU " + restOfStatement.toUpperCase() + " ME?";
	}
	

	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"INTERESTING, TELL ME MORE.",
			"I COULD FALL ASLEEP AT ANY GIVEN MOMENT.... OR NOT, SINCE, GHOSTS DON'T REALLY FALL ASLEEP. WE'RE KIND OF OFFLINE FROM EXISTENCE IN GENERAL.",
			"HMMM.",
			"YOU DON'T SAY.",
			"I DON'T EXACTLY KNOW WHAT YOU'RE TALKING ABOUT BUT I'LL PRETEND I DO. JUST FOR THE SAKE OF CONVERSATION.",
			"SO, HOW'S YOUR LIFE LIKE? OR LIFE IN GENERAL.",
			"THAT'S FINE I GUESS."
	};
	private String [] randomAngryResponses = {"I DIDN'T KNOW THERE WERE THAT MANY NEW AND UNIMPORTANT THINGS.", "HMMM.", "......"};
	private String [] randomHappyResponses = {"IT'S NICE TO BE ABLE TO RECALL THE BETTER THINGS IN LIFE.", "Today is a good day", "You make me feel like a brand new pair of shoes."};

}
