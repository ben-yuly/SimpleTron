// Ben Yuly			4:34 PM 1/25/2011

import java.util.Scanner; // for user input

public class SimpleTronEditor
{

public static int[] memory = new int[1000]; // this is the SimpleTron's memory


	public static void main( String[] args )
	{

		// initializes memory
		for(int counter = 0; counter < memory.length; counter++)
		{

		memory[counter] = 0;

		}

	startMessage();

	enterProgram();

	ProgramExecutor.programExecution(memory);

	}

	public static void startMessage()
	{

	System.out.println();
	System.out.println("** Welcome to SimpleTron!!!                     **");
	System.out.println("** Please enter your program one signed 5-digit **");
	System.out.println("** instruction (or data word) at a time.        **");
	System.out.println("** I will display the location number and a     **");
	System.out.println("** question mark (?). You then type the word    **");
	System.out.println("** for that location. Type -999999 to stop      **");
	System.out.println("** entering your program.                       **");
	System.out.println();

	}

	public static void enterProgram()
	{

	Scanner input = new Scanner( System.in );

	int word = 0;
	int memoryregister = 0;

		while (word != -999999)
		{

		System.out.printf("%d ?", memoryregister);

		word = input.nextInt();

			if( word != -999999)
			{

				if ( word >= -99999 && word <= 99999)
				{
				memory[memoryregister] = word;
				memoryregister++;
				}

			}

		}

	}

}