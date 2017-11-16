import javax.sound.midi.SysexMessage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Stack {
	private List<String> list = new ArrayList<>();

	public void add (String number) {
		//if (integer == '+' || integer == '-' || integer == '*' || integer == '/' || integer == '%')
			list.add(number);
		//else
		//	list.add(integer);
	}

	public void remove (int index) {
		list.remove(index);
	}

	public int size() {
		return (this.list.size());
	}

	public String get(int index) {
		return(this.list.get(index));
	}

	}
	public int operate () {
		if (list.size() < 3) {
			System.out.println("Cannot perform operation. Invalid entry.");
			System.exit(1);
		}
		int temp = 0;
		int operators = 0;
		int operands = 0;

		for (int index = 0; index < list.size(); ++index) {
			int a = 0;
			int b = 0;
			if (list.get(index).length() == 1 ) {
				if (list.get(index).equals("+")) {
						a = Integer.parseInt(list.get((index - 2)));
						b = Integer.parseInt(list.get((index - 1)));
					temp = a + b;
					list.set(index, Integer.toString(temp));
					++operators;
				} else if (list.get(index).equals("-")) {
					a = Integer.parseInt(list.get((index - 2)));
					b = Integer.parseInt(list.get((index - 1)));
					temp = a - b;
					list.set(index, Integer.toString(temp));
					++operators;
				} else if (list.get(index).equals("*")) {
					a = Integer.parseInt(list.get((index - 2)));
					b = Integer.parseInt(list.get((index - 1)));
					temp = a * b;
					list.set(index, Integer.toString(temp));
					++operators;
				} else if (list.get(index).equals("/")) {
					a = Integer.parseInt(list.get((index - 2)));
					b = Integer.parseInt(list.get((index - 1)));
					temp = a / b;
					list.set(index, Integer.toString(temp));
					++operators;
				} else if (list.get(index).equals("%")) {
					a = Integer.parseInt(list.get((index - 2)));
					b = Integer.parseInt(list.get((index - 1)));
					temp = a % b;
					list.set(index, Integer.toString(temp));
					++operators;
				}
				else
					++operands;
			}
			else {
				++operands;
				continue;
			}
		}
		if (operators != (operands - 1)) {
			System.out.println("Error. Invalid entry.");
			System.exit(1);
		}

		return (Integer.parseInt(list.get(list.size() - 1)));
	}

	public void print() {
		for (String item : list)
			System.out.println(item);
	}

	public void printFinal() {
		int index = this.list.size() - 1;
		System.out.println(this.list.get(index));
	}
}
