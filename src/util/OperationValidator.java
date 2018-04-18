package util;

import java.util.ArrayList;
import kenken.Cell;

public class OperationValidator {

    public static boolean isPowerOf2(int number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }

    public static boolean isDivideable(ArrayList<Cell> cells) {

        if (!notZero(cells)) {
            return false;
        }

        int result = cells.get(0).number;
        for (int i = 1; i < cells.size(); i++) {
            if (result == 0) {
                return false;
            }
            if (cells.get(i).number % result != 0) {
                return false;
            }
            result = result / cells.get(i).number;
        }
        return true;
    }

    public static boolean notZero(ArrayList<Cell> cells) {
        return cells.stream().noneMatch((cell) -> (cell.number == 0));
    }
}
