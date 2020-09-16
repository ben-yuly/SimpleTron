# SimpleTron
A simulated computer based on the excercise Deitel and Deitel's textbook.

## Intro
Welcome to SimpleTron, my very own simulated computer! With SimpleTron, you can run machine-code programs written in SML (SimpleTron Machine Language) on my simulated SimpleTron! You may use the Application.java blueprint to create programs that can be "remembered" from one execution to the other (this saves time by not having to type all the code in everytime you want to test it!). Also, you may use the SimpleTronEditor.class to type your programs in and run it easily!

## How does it work?
Every location (or word) in memory (of which the SimpleTron has 1000) is a five-digit instruction or data word. The simulated computer will start at location 0, and execute every subsequent memory location until it reaches a halt instruction, until it reaches a branching instruction, or until an error occurs. A branching instruction will change the next memory location to execute on to a custom one.

Every instruction looks like this:

XX-YYY (there is no dash in a real instruction; I just put one there for instructional purposes, to emphasise the difference between the instruction and operand)

XX -> operation code (this number signifies which operation to perform)

YYY -> operand (self explanatory; differs on which operation is being performed)

### Operation Codes
#### input/output operations
READ = 10	// reads an integer from the keyboard with the prompt "Enter an integer:".
		// operand: memory location to store the inputed integer in.

WRITE = 11	// prints the contents of a memory location to System.out.
		// operand: memory location to read from.

NEWLINE = 12 // prints a newline character to System.out.
		  // operand: none.
      
#### memory operations
LOAD = 20	// loads word from a specified memory location and stores it in the accumulator.
		// operand: memory location to load from.

STORE = 21	// stores word from accumulator to a specified memory location
		// operand: memory location to store to
arithmetic operations (note: all of these operations store results in the accumulator)
ADD = 30	// adds word from a specified memory location to the word in the accumulator.
		// operand: memory location to load the word to add to the one in the accumulator.

SUBTRACT = 31	// subtracts word from a specified memory location to the word in the 				// accumulator.
			// operand: memory location to load the word to subtract from the one in 			// the accumulator.

DIVIDE = 32	// divides word from the accumulator into the word from a specified memory 			// location.
		// operand: memory location to load the word from to divide the word in the 			// accumulator.

MULTIPLY = 33	// multiplies word from the accumulator by the word from a specified 				// memory location.
			// operand: memory location to load the word from to multiply the word in 			// the accumulator by.
      
#### transfer of control operations
BRANCH = 40		// branch to a specified memory location.
			// operand: memory location to branch to.

BRANCHNEG = 41	// branch to a specified memory location if the word in the accumulator is 			// negative.
			// operand: memory location to branch to.

BRANCHZERO = 42	// branch to a specified memory location if the word in the accumulator is
			// zero.
			// operand: memory location to branch to.

BRANCHREF = 43	// branch to a memory location referenced in a specified memory location.
			// operand: specified memory location.
      
#### halt operations
DUMP = 51	// dumps memory to the screen.
		// operand: none.

HALT = 50	// halts machine (dumps first).
		// operand: none.
character input/output operations
WRITECHAR = 90	// print a specified ASCII character to System.out.
			// operand: ASCII number of character to print.

### instruction example:

30999 - add what is in memory location 999 and add it to the
	number in the accumulator (leaves result in the
	accumulator).

