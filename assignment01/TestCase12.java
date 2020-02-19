// Sean Szumlanski
// COP 3503, Summer 2019

// =============================
// SneakyQueens: TestCase12.java
// =============================
// Runs several small test cases included in BaselineTests.java. These are novel
// test cases that were not released with the assignment. This is a duplicate
// of TestCase11.java.


import java.io.*;
import java.util.*;

public class TestCase12
{
	public static void main(String [] args) throws Exception
	{
		// This ensures that allTheQueensAreSafe() isn't a one-liner that just
		// always returns the same value in an attempt to pass a few test cases
		// for free. It is also designed to ensure that everyone is creating and
		// testing small test cases in addition to the ones released with each
		// assignment.
		BaselineTests.runBaselineTests();
	}
}
