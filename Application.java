// Ben Yuly			4:34 PM 1/25/2011

// This is a basic starting place for building an application that will
// run on the SimpleTron ProgramExecutor. You will build your program
// from a memory "snapshot" by typing the SML (SimpleTron Machine
// Language) into the memory like this:

// memory[0] = 4300

// This will write the value 4300 to memory location 0 (in this case it
// will halt the program).


public class Application
{

public static int[] memory = new int[1000]; // this is the SimpleTron's memory


	public static void main( String[] args )
	{

	memory[0] = 0; // start writing code here


		// initializes remainder of memory to zero (just set counter
		// to the location after the last location of your program)
		for(int counter = 0; counter < memory.length; counter++)
		{

		memory[counter] = 0;

		}


	ProgramExecutor.programExecution(memory);

	}

}