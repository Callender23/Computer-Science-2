// Sean Szumlanski
// COP 3503, Summer 2019

// =============================
// SneakyQueens: TestCase14.java
// =============================
// A large test case. This is simply a copy of TestCase13.


import java.io.*;
import java.util.*;

public class TestCase14
{
	private static void checkTest(boolean success)
	{
		System.out.println(success ? "Hooray!" : "fail whale :(");
	}

	public static void main(String [] args) throws Exception
	{
		// This ensures that allTheQueensAreSafe() isn't a one-liner that just
		// always returns the same value in an attempt to pass a few test cases
		// for free. It is also designed to ensure that everyone is creating and
		// testing small test cases in addition to the ones released with each
		// assignment.
		BaselineTests.runBaselineTests();

		Scanner in = new Scanner(new File("input_files/TestCase14-input.txt"));
		ArrayList<String> list = new ArrayList<String>();

		// Read each line from the input file into the ArrayList.
		while (in.hasNext())
			list.add(in.next());

		checkTest(SneakyQueens.allTheQueensAreSafe(list, 30000) == true);
	}
}
