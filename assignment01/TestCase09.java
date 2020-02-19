// Sean Szumlanski
// COP 3503, Summer 2019

// =============================
// SneakyQueens: TestCase09.java
// =============================
// A larger test case. None of the queens in this one are able to attack one
// another. This is a copy of the TestCase03.java file that was released with
// the assignment.


import java.io.*;
import java.util.*;

public class TestCase09
{
	private static void checkTest(boolean success)
	{
		System.out.println(success ? "Hooray!" : "fail whale :(");
	}

	public static void main(String [] args) throws Exception
	{
		Scanner in = new Scanner(new File("input_files/TestCase09-input.txt"));
		ArrayList<String> list = new ArrayList<String>();

		// Read each line from the input file into the ArrayList.
		while (in.hasNext())
			list.add(in.next());

		checkTest(SneakyQueens.allTheQueensAreSafe(list, 10000) == true);
	}
}
