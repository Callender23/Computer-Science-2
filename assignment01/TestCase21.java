// Sean Szumlanski
// COP 3503, Summer 2019

// =============================
// SneakyQueens: TestCase21.java
// =============================
//
// THIS IS A BONUS TEST CASE.
//
// A large test case where the first two queens in the ArrayList can attack one
// another. As soon as you see that two queens can attack one another in
// allTheQueensAreSafe(), the rational thing to do is to stop and return false
// immediately. This test case checks that the function returns very quickly.


import java.io.*;
import java.util.*;

public class TestCase21
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

		Scanner in = new Scanner(new File("input_files/TestCase21-input.txt"));
		ArrayList<String> list = new ArrayList<String>();

		// Read each line from the input file into the ArrayList.
		while (in.hasNext())
			list.add(in.next());

		final long start = System.nanoTime();
		checkTest(SneakyQueens.allTheQueensAreSafe(list, 60000) == false);
		final long end = System.nanoTime();

		double totalTimeInSeconds = (end - start) / 1e9;

		// My solution was taking less than 0.0004 seconds to run this test
		// case, so a limit of 0.0030 seconds seemed pretty generous.
		if (totalTimeInSeconds <= 0.003)
			System.out.println("Super speedy! Neat!");
		else
			System.out.println("That felt a bit slow...");
	}
}
