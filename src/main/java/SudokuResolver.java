import java.util.ArrayList;
import java.util.List;

public class SudokuResolver {


    public int[][] resolve(int[][] sudoku) {
        synchronized (this) {
            answerSize = 1;
            init(sudoku);
            placeNextNum();
            int[][] res = result.isEmpty() ? null : result.get(0);
            result = new ArrayList<>();
            return res;
        }
    }

    private static int answerSize = 1;
    private static List<int[][]> result = new ArrayList<>();


    private static int[][] elements = new int[9][9];
    private static int[][] rows = new int[9][9];
    private static int[][] cols = new int[9][9];
    private static int[][] boxes = new int[9][9];

    private void init(int[][] initial) {
        for (int i = 0; i < initial.length; i++) {
            for (int j = 0; j < initial[i].length; j++) {
                if (initial[i][j] != 0) {
                    placeNum(i, j, initial[i][j]);
                }
            }
        }
    }

    private void placeNextNum() {
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                if (elements[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (couldPlace(i, j, k)) {
                            placeNum(i, j, k);
                            placeNextNum();
                            if (result.size() >= answerSize) {
                                return;
                            }
                            removeNum(i, j, k);
                        }
                    }
                    return;
                }
                if (i == elements.length - 1 && j == elements[i].length - 1) {
                    result.add(elements);
                    printCurrentPermutation();
                    if (result.size() >= answerSize) {
                        return;
                    }
                }
            }
        }
    }

    private boolean couldPlace(int row, int col, int value) {
        value--;
        return rows[row][value] + cols[col][value] + boxes[row / 3 * 3 + col / 3][value] == 0;
    }

    private void placeNum(int row, int col, int value) {
        value--;
        rows[row][value]++;
        cols[col][value]++;
        boxes[row / 3 * 3 + col / 3][value]++;
        elements[row][col] = ++value;
    }

    private void removeNum(int row, int col, int value) {
        value--;
        rows[row][value]--;
        cols[col][value]--;
        boxes[row / 3 * 3 + col / 3][value]--;
        elements[row][col] = 0;
    }

    private void printCurrentPermutation() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(elements[i][j] + " ");
                if (j % 3 == 2) {
                    System.out.print("  ");
                }
            }
            if (i % 3 == 2) {
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    private static final SudokuResolver resolver = new SudokuResolver();

    private SudokuResolver() {
    }

    public static SudokuResolver getInstance() {
        return resolver;
    }
}
