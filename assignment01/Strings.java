
public class Strings
{
	public static void main(String [] args)
	{
		String str = "bjygg4331";
		String[] part = str.split("(?<=\\D)(?=\\d)");



		System.out.println(part[0]);
		System.out.println(part[1]);
	}

}

	String colLocation = "";
	}
		for(int i = 0; i < coordinates.length(); i++)
		{
    	if(coordinates.charAt(i) >= 'a'  && coordinates.charAt(i) <= 'z')
			{
      	colLocation += coordinates.charAt(i);
     	}
     }
   return colLocation;
	}
	// We put the string of soley the letter into the string called otherColm to be stored
	// this string is split using the split functiom in which was made to sepreate the two
	//String otherColm = string[0];



	// Assigned the string to variable string and split the string into two part using regex
	//String str = coordinateStrings;

	// String has been broken here into non digits and digits part
	//String[] string = str.split("(?<=\\D)(?=\\d)");
