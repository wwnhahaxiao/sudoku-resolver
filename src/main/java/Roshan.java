import java.util.Scanner;

public class Roshan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int[][] sudoku = new int[9][9];
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            for (int j = 0; j < next.length(); j++) {
                sudoku[i][j] = Character.getNumericValue(next.charAt(j));
            }
            i++;
            if (i == 9) {
                System.out.println("begin resolve");
                SudokuResolver.getInstance().resolve(sudoku);
                break;
            }
        }
        scanner.close();
    }
}
