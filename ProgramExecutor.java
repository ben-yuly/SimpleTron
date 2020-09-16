// Ben Yuly				3:26 PM 1/26/2011

// note: in this program, any boolean true means an error has occured.

import java.util.Scanner;

public class ProgramExecutor
{

public static int[] memory; // this is the memory (this gets imported to from the editor)

private static int accumulator = 0; // this is the accumulator
private static int instructioncounter = 0; // this keeps track of the location in memory being acted upon
private static int instructionregister = 0; // this holds the current word
private static int operation = 0; // this is the current operation in the word
private static int operand = 0; /* this is the operand that the current operation will perform with the 
			   the operand in the accumulator*/


// operations

// input/output operations

final static int READ = 10;
final static int WRITE = 11;
final static int NEWLINE = 12;

// memory operations
final static int LOAD = 20;
final static int STORE = 21;

// arithmetic operations
final static int ADD = 30;
final static int SUBTRACT = 31;
final static int DIVIDE = 32;
final static int MULTIPLY = 33;

// transfer of control operations
final static int BRANCH = 40;
final static int BRANCHNEG = 41;
final static int BRANCHZERO = 42;
final static int BRANCHREF = 43;

// halt operations
final static int DUMP = 51;
final static int HALT = 50;

// character input/output operation
final static int WRITECHAR = 90;

	public static void programStartMessage()
	{

	System.out.println();
	System.out.println("** Program loading completed **");
	System.out.println("** Program execution begins  **");

	}

	public static void programExecution( int[] importedmemory )
	{

	memory = importedmemory;

	programStartMessage();

		while (instructioncounter < memory.length)
		{

		// loads current address into instructionregister
		instructionregister = memory[instructioncounter];

		// finds operand
		operand = instructionregister % 1000;

		// finds operation
		operation = instructionregister / 1000;

			switch( operation )
			{

			case READ:
			read(operand);
			break;

			case WRITE:
			write(operand);
			break;

			case NEWLINE:
			newline();
			break;

			case LOAD:
			load(operand);
			break;

			case STORE:
			store(operand);
			break;

			case ADD:
			add(operand);
			break;

			case SUBTRACT:
			subtract(operand);
			break;

			case DIVIDE:
			boolean error = divide(operand);

				if( error == true )
				divideByZeroError();

			break;

			case MULTIPLY:
			multiply(operand);
			break;

			case BRANCH:
			branch(operand);
			instructioncounter--; // otherwise execution will start after the user's specified location
			break;

			case BRANCHZERO:
			branchzero(operand);
			instructioncounter--; // otherwise execution will start after the user's specified location
			break;

			case BRANCHNEG:
			branchneg(operand);
			instructioncounter--; // see above
			break;

			case BRANCHREF:
			branchref(operand);
			instructioncounter--; // see above
			break;

			case WRITECHAR:
			writechar(operand);
			break;

			case DUMP:
			dump();
			break;

			case HALT:
			halt();
			break;

			default:
			notAnOpCodeError();

			}

		checkForAccumulatorOverflow();
		
		instructioncounter++;

		}

	}

	public static void read( int operand )
	{

	Scanner input = new Scanner( System.in ); // creates Scanner for user input

	System.out.print("Enter an integer: "); // prompts user

	int inputtedinteger = input.nextInt(); // inputs integer

	memory[operand] = inputtedinteger;

	}

	public static void write( int operand )
	{

	String wordtoprint = "" + memory[operand];

	System.out.print(wordtoprint);

	}

	public static void newline()
	{

	System.out.println();

	}

	public static void load( int operand )
	{

	accumulator = memory[operand];

	}

	public static void store( int operand )
	{

	memory[operand] = accumulator;

	}

	public static void add( int operand )
	{

	int word1 = memory[operand];

	int word2 = accumulator;

	accumulator = word2 + word1;

	}

	public static void subtract( int operand )
	{

	int word1 = memory[operand];

	int word2 = accumulator;

	accumulator = word2 - word1;

	}

	public static boolean divide( int operand )
	{

	int word1 = memory[operand];

	int word2 = accumulator;

		if (word1 == 0)
		return true;
		

	accumulator = word2/word1;

	return false;

	}

	public static void multiply( int operand )
	{

	int word1 = memory[operand];

	int word2 = accumulator;

	accumulator = word2 * word1;

	}

	public static void branch( int operand )
	{

	instructioncounter = operand;

	}

	public static void branchzero( int operand )
	{

	if( accumulator == 0 )
	instructioncounter = operand;

	}

	public static void branchneg( int operand )
	{

	if( accumulator < 0)
	instructioncounter = operand;

	}

	public static void branchref( int operand )
	{

		if (memory[operand] < 0 || memory[operand] > 999)
		branchToNonexistentMemoryLocationError();

	instructioncounter = memory[operand];

	}

	public static void writechar( int operand )
	{

		if( operand < 0 || operand > 127 )
		{
		invalidCharacterError();
		}

	char charactertoprint = (char) operand;

	System.out.print(charactertoprint);

	}

	public static void halt()
	{

	System.out.println();
	System.out.println("** Execution completed **");
	dump();

	System.exit(0);

	}

	public static void dump()
	{
	System.out.println();
	System.out.println("** Dump:               **");
	System.out.println();

	System.out.println("REGISTERS:");
	System.out.printf("accumulator: %d\n", accumulator);
	System.out.printf("instructionCounter: %d\n", instructioncounter);
	System.out.printf("instructionRegister: %d\n", instructionregister);
	System.out.printf("operationCode: %d\n", instructionregister / 1000);
	System.out.printf("operand: %d\n", instructionregister % 1000);

	System.out.println();

	System.out.println("Memory usage:");

	int usedmemorylocations = 0;

		for(int counter = 0; counter < memory.length; counter++)
		{
			if( memory[counter] != 0 )
			usedmemorylocations++;

		}

	System.out.printf("%d words / %d words\n", usedmemorylocations, memory.length);

	System.out.println();

	System.out.println("MEMORY:");

	System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");

	int currentline = 0;

	System.out.print("0");

		for(int counter = 0; counter < memory.length; counter++)
		{

			if( counter != 0 )
			{

				if( (counter % 10) == 0)
				{
				currentline++;
				System.out.println();
				System.out.print(currentline * 10);
				}
			}

		System.out.printf("\t%d", memory[counter]);

		}

	System.out.println();

	}

	// Error methods

	public static void divideByZeroError()
	{

	System.out.println();
	System.out.println("** Attempt to divide by zero.                 **");
	System.out.println("** SimpleTron execution abnormally terminated **");

	halt();

	}

	public static void notAnOpCodeError()
	{

	System.out.println();
	System.out.printf("** Memory location being executed (%d) does     **\n", instructioncounter);
	System.out.println("** not contain a valid operation code.         **");
	System.out.println("** SimpleTron execution abnormally terminated. **");

	halt();

	}

	public static void checkForAccumulatorOverflow()
	{

		// note: -1 means less than and 1 means greater than in this case


		if (accumulator < -99999)
		accumulatorOverflowError();

		if (accumulator > 99999)
		accumulatorOverflowError();

	}

	public static void accumulatorOverflowError()
	{

	System.out.println();
	System.out.println("** Accumulator overflow error. Accumulator can **");
	System.out.println("** only be a signed 5-digit word.              **");
	System.out.println("** SimpleTron execution abnormally terminated. **");

	halt();

	}

	public static void invalidCharacterError()
	{

	System.out.println();
	System.out.println("** Attempt to print an invalid ASCII character. **");
	System.out.println("** SimpleTron execution abnormally terminated.  **");

	halt();

	}

	public static void branchToNonexistentMemoryLocationError()
	{

	System.out.println();
	System.out.println("** Attempt to branch to a nonexistent memory location. **");
	System.out.println("** SimpleTron execution abnormally terminated.         **");

	halt();

	}

}