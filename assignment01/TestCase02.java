// Sean Szumlanski
// COP 3503, Summer 2019

// =============================
// SneakyQueens: TestCase02.java
// =============================
// Tests that hoursSpent() is implemented correctly and returns a reasonable
// estimate of time spent.


import java.io.*;
import java.util.*;

public class TestCase02
{
	public static void main(String [] args)
	{
		double hours = SneakyQueens.hoursSpent();
		System.out.println((hours <= 0.0 || hours > 60.0) ? "fail whale :(" : "Hooray!");
	}
}
