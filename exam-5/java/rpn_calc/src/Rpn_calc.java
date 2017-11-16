import java.util.ArrayList;
import java.util.List;

public class Rpn_calc {
	public static void main(String[] args) {
		/*stack to perform operations on*/
		Stack list = new Stack();

		/*input string in the form of a char array*/
		char[] equation = args[0].toCharArray();



		/*iterates through each element in the array to preserve integers greater than 9*/
		for (int i = 0; i < equation.length; ++i) {

			/*Array to store multiple characters to convert to integers*/
			List<Character> tempList = new ArrayList<>();


			if (equation[i] == ' ') continue; //iterates over whitespace

			/*if an integer is greater than 9 (more than 2 digits) the program needs to convert that number into 1 number
			and not two separate characters*/
			while ( (i < equation.length) && (equation[i] != ' '))
				tempList.add(equation[i++]);

			/*concatenating multiple characters together*/
			StringBuilder str = new StringBuilder(tempList.size());
			for (Character c : tempList) {
				str.append(c);
			}
			String number = str.toString(); //converts character array to string
			list.add(number); //adds number to stack


			tempList.clear(); //resets temporary array
			str.setLength(0); //resets temporary string
		}

		/*prints stack*/
		System.out.println("" + list.operate());
		System.exit(0);
	}
}
