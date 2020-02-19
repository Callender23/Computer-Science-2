
// Jeffrey Callender
// COP3503 Summer 2019
// je410689

import java.io.*;
import java.util.*;

public class SneakyQueens
{
		public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
		{
			// Delcare the arrays for the four possible ways that the queens can attack each other
			// we use the boardsize for the size of the array plus one more of those inclusive cases
			int[] row = new int[boardSize + 1];
			int[] colm = new int[boardSize + 1];

			// We time the boardSize by a factor of two because this represent the total number of
			// diagnols in the postive direction right most
			int[] upDiagnol = new int[(boardSize * 2)];

			// We do the same for the down diagnols and then add one since we want to include the 0
			// when we count our diagnols
			int[] downDiagnol = new int[2 * boardSize + 1];


			//	For loop is gonna be use here to loop through the whole list of strings
			for(String locationOfStrings: coordinateStrings)
			{
				String col = parseTheColmun(locationOfStrings);

				//	Convert the string in which we have the letters to be in the base 10 instead of the
				//	the base 26. We do this using the conversion method, this way we can represent the 0
				int otherColm = conversion(col);

				String rows = locationOfStrings.replace(col,"");

				//	Put the string of row into parse int
				int otherRow = Integer.parseInt(rows);

				//	Checking for the row to see for collision when placing queens return false if it is 1
				if(colm[otherColm] == 0)
					colm[otherRow] = 1;
				else if(colm[otherRow] == 1)
					return false;

				if(row[otherRow] == 0)
					row[otherRow] = 1;
				else if(row[otherRow] == 1)
					return false;

				//	Checking if current spot is empty then placing queen. return false if colm is 1


				//	Formulas use to get the proper diagnols of both the up and down diagnols
				int upOtherDiagnol = otherRow - otherColm + boardSize;
				int downOtherDiagnol = otherRow + otherColm;

				//	We perform the same check here but for the up diagnols
				if(upDiagnol[upOtherDiagnol] == 0)
					upDiagnol[upOtherDiagnol] = 1;
				else if(upDiagnol[upOtherDiagnol] == 1)
					return false;

				//	We do the same for the down diagnol and check is there is a collision
				if(downDiagnol[downOtherDiagnol] == 0)
					downDiagnol[downOtherDiagnol] = 1;
				else if(downDiagnol[downOtherDiagnol] == 1)
					return false;

			}
			//	YAYY we made it down here that means all the queen appear to be safe so we can return true
			return true;
		}
		private static String parseTheColmun(String coordinates)
		{
			//	Set the string here to empty here since we dont want anything in it yet
			String col = "";

	 		//	Goes through the entire coordinates length
			for(int i = 0; i < coordinates.length(); i++)
			{
				if(coordinates.charAt(i) >= 'a'  && coordinates.charAt(i) <= 'z')
					col += coordinates.charAt(i);
			}

			//	We return the parse string here
			return col;
	 }
		//	Credit for Dr. Szmulanski for this awsomee horner rule code that i was able to use on here
		public static int conversion(String Colmn)
		{
			int retval = 0;
			//	for loop that will iteratively add our value
			for(int i = 0; i < Colmn.length(); i++)
				retval = retval * 26 + (Colmn.charAt(i) - ('a' - 1));

			return retval;
		}
		public static double difficultyRating()
		{
			return 3.0;
		}

		public static double hoursSpent()
		{
			return 30.0;
		}
}
