// Sean Szumlanski
// COP 3503, Summer 2019

// ================================
// SneakyQueens: BaselineTests.java
// ================================
// A suite of small tests to ensure allTheQueensAreSafe() is not just returning
// the same value no matter what the test case is. Each large test case must
// pass these smaller test cases in order to receive credit. These are also used
// to ensure that everyone is creating their own small test cases and not simply
// relying on the ones released with each assignment.


import java.io.*;
import java.util.*;

public class BaselineTests
{
	private static boolean checkTest(boolean success)
	{
		return success;
	}

	private static boolean checkTestFromFile(String filename, boolean expectedResult)
		throws FileNotFoundException
	{
		Scanner in = new Scanner(new File("input_files/" + filename));
		ArrayList<String> list = new ArrayList<String>();

		// For these test cases, we assume the first line in the input file is the
		// board size. I'm using parseInt() here because if you try to mix calls to
		// nextInt() and next() when dealing with a Scanner, it can cause some
		// weirdness with end-of-line characters not being consumed as expected.
		int boardSize = Integer.parseInt(in.next());

		// Read each line from the input file into the ArrayList.
		while (in.hasNext())
			list.add(in.next());

		return checkTest(SneakyQueens.allTheQueensAreSafe(list, boardSize) == expectedResult);
	}

	public static boolean runBaselineTests() throws FileNotFoundException
	{
		boolean success = true;

		success &= checkTestFromFile("baseline01.txt", true);
		success &= checkTestFromFile("baseline02.txt", false);
		success &= checkTestFromFile("baseline03.txt", false);
		success &= checkTestFromFile("baseline04.txt", true);
		success &= checkTestFromFile("baseline05.txt", true);
		success &= checkTestFromFile("baseline06.txt", true);
		success &= checkTestFromFile("baseline07.txt", true);
		success &= checkTestFromFile("baseline08.txt", false);

		System.out.println((success ? "Passes" : "Fails") + " baseline tests.");

		return success;
	}
}
