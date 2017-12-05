import java.util.*;

public class Main {
    public static void main(String[] args) {
		HashMap<Integer, Point> hashMap = new HashMap<>();

        //dimension of board
        int dimensions = 6;
        int max = dimensions * dimensions;

        /**
         * X increases, Y increases, then
         * X decreases, Y decreases then
         * repeat;
         * Continue this cycle until point.value is equal to (dimension of board squared minus one);
         *
         * if dimensions = odd number, blank value : x increases
         * if dimensions = even number, blank value : x decreases
         */

        //initialising counters
		int inc_x = 0;
		int top_left_x_y = 0;
		int inc_y = 1;

		int bot_right_x_y = dimensions - 1;
		int dec_x = dimensions - 2;
		int dec_y = dimensions - 2;

        //initialising x limits
        int x_lower_limit = -1;
        int x_upper_limit = dimensions - 1;

        //initialising y limits
        int y_lower_limit = 1;
        int y_upper_limit = dimensions - 1;

        int value = 1;

        //setting tiles
        while (value < (max)) {
			for (int x = inc_x; x <= x_upper_limit && value < max; ++x) {
                hashMap.put(value, new Point<>(x, top_left_x_y));
				++value;
			}
			--x_upper_limit;
            for (int y = inc_y; y <= y_upper_limit && value < max; ++y) {
                hashMap.put(value, new Point<>(bot_right_x_y, y));
				++value;
            }
            --y_upper_limit;
			++x_lower_limit;
			for (int x = dec_x; x >= x_lower_limit && value < max; --x) {
                hashMap.put(value, new Point<>(x, bot_right_x_y));
				++value;
            }
            for (int y = dec_y; y >= y_lower_limit && value < max; --y) {
				hashMap.put(value, new Point<>(top_left_x_y, y));
				++value;
			}
			++y_lower_limit;

			++inc_x;
			++inc_y;
			--dec_x;
			--dec_y;

			++top_left_x_y;
			--bot_right_x_y;
        }

        //setting blank tile
        if (dimensions % 2 == 0)
			hashMap.put(0, new Point<>(bot_right_x_y, top_left_x_y));
        else
			hashMap.put(0, new Point<>(bot_right_x_y, top_left_x_y));
        Point tile;

        for (int i = 1; i < hashMap.size(); ++i) {
            tile = hashMap.get(i);
            System.out.println("Tile " + i + ": (" + tile.getX() + ";" + tile.getY() + ")" );
			}

        tile = hashMap.get(0);
		System.out.println("Tile " + 0 + ":  (" + tile.getX() + ";" + tile.getY() + ")" );
		System.out.println("HashMap contains " + hashMap.size() + " values.");
    }
}