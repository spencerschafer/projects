import java.util.*;

public class Coordinates {
    public static void main(String[] args) {

        /**
		 * To find the snail solution of the N-puzzle:
		 *
         * X increases until X is equivalent to the dimension, then
		 * Y increases until Y is equivalent to the dimension, then
		 * X decreases until X is equivalent to starting_x_value + 1, then
		 * Y decreases until Y is equivalent to starting_y_value + 1, then
		 * repeat process adjusting the corresponding minimum and maximum values;
		 *
         * Continue this cycle until the key's value is  equal to (dimensions squared - 1);
         *
         * if dimensions is an odd number, x value of blank tile + 1
         * if dimensions is an even number, x value of blank tile - 1
         */

		//HashMap initialisation
		HashMap<Integer, Point> goalState = new HashMap<>();
		Scanner scan = new Scanner(System.in);
		System.out.printf("Enter the size of the board (i.e. 3 for 3x3):\n");
		int dimensions = scan.nextInt();
		if (!(dimensions >= 0)) {
			System.out.println("Please enter a value greater than 0.");
			System.exit(1);
		}
		int max = dimensions * dimensions;

		//increasing counters
		int increasingX = 0;
		int xDecreasingY_yIncreasingX = 0; //x value for decreasing y; y value for increasing x
		int increasingY = 1;

		//decreasing counters
		int xIncreasingY_yDecreasingX = dimensions - 1; //x value for increasing y, y value for decreasing x
		int decreasingX = dimensions - 2;
		int decreasingY = dimensions - 2;

		//initialising x limits
		int xLowerLimit = -1;
		int xUpperLimit = dimensions - 1;

		//initialising y limits
		int yLowerLimit = 1;
		int yUpperLimit = dimensions - 1;

		//key representing value stored at individual coordinate
		int key = 1;

		//setting coordinates of keys, aka 'tiles'
		while (key < max) {

			//increasing x values
			for (int x = increasingX; x <= xUpperLimit && key < max; ++x) {
				goalState.put(key, new Point<>(x, xDecreasingY_yIncreasingX));
				++key;
			}
			--xUpperLimit;

			//increasing y values
			for (int y = increasingY; y <= yUpperLimit && key < max; ++y) {
				goalState.put(key, new Point<>(xIncreasingY_yDecreasingX, y));
				++key;
			}
			--yUpperLimit;
			++xLowerLimit;

			//decreasing x values
			for (int x = decreasingX; x >= xLowerLimit && key < max; --x) {
				goalState.put(key, new Point<>(x, xIncreasingY_yDecreasingX));
				++key;
			}

			//decreasing y values
			for (int y = decreasingY; y >= yLowerLimit && key < max; --y) {
				goalState.put(key, new Point<>(xDecreasingY_yIncreasingX, y));
				++key;
			}
			++yLowerLimit;

			++increasingX;
			++increasingY;
			--decreasingX;
			--decreasingY;

			++xDecreasingY_yIncreasingX;
			--xIncreasingY_yDecreasingX;
		}

		//setting blank tile
		goalState.put(0, new Point<>(xIncreasingY_yDecreasingX, xDecreasingY_yIncreasingX));

		//test code for printing coordinates
        Point tile;
        for (int i = 1; i < goalState.size(); ++i) {
            tile = goalState.get(i);
            System.out.println("Tile " + i + ": (" + tile.getX() + ";" + tile.getY() + ")" );
			}

		//blank tile printed last to correspond with coordinates
        tile = goalState.get(0);
		System.out.println("Tile " + 0 + ":  (" + tile.getX() + ";" + tile.getY() + ")" );
		System.out.println("HashMap contains " + goalState.size() + " values.");
    }
}