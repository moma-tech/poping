package top.moma.example.kata;

/**
 * SudokuValidator
 *
 * <p>Sudoku Background <br>
 * Sudoku is a game played on a 9x9 grid. The goal of the game is to fill all cells of the grid with
 * digits from 1 to 9, so that each column, each row, and each of the nine 3x3 sub-grids (also known
 * as blocks) contain all of the digits from 1 to 9. (More info at:
 * http://en.wikipedia.org/wiki/Sudoku)
 *
 * <p>Sudoku Solution Validator <br>
 * Write a function validSolution/ValidateSolution/valid_solution() that accepts a 2D array
 * representing a Sudoku board, and returns true if it is a valid solution, or false otherwise. The
 * cells of the sudoku board may also contain 0's, which will represent empty cells. Boards
 * containing one or more zeroes are considered to be invalid solutions.
 *
 * <p>The board is always 9 cells by 9 cells, and every cell only contains integers from 0 to 9.
 *
 * <p>Examples <br>
 * validSolution( [<br>
 * [5, 3, 4, 6, 7, 8, 9, 1, 2], <br>
 * [6, 7, 2, 1, 9, 5, 3, 4, 8], <br>
 * [1, 9, 8, 3, 4, 2, 5, 6, 7], <br>
 * [8, 5, 9, 7, 6, 1, 4, 2, 3], <br>
 * [4, 2, 6, 8, 5, 3, 7, 9, 1], <br>
 * [7, 1, 3, 9, 2, 4, 8, 5, 6], <br>
 * [9, 6, 1, 5, 3, 7, 2, 8, 4], <br>
 * [2, 8, 7, 4, 1, 9, 6, 3, 5], <br>
 * [3, 4, 5, 2, 8, 6, 1, 7, 9] ]); <br>
 * // => true validSolution([<br>
 * [5, 3, 4, 6, 7, 8, 9, 1, 2], <br>
 * [6, 7, 2, 1, 9, 0, 3, 4, 8], <br>
 * [1, 0, 0, 3, 4, 2, 5, 6, 0], <br>
 * [8, 5, 9, 7, 6, 1, 0, 2, 0], <br>
 * [4, 2, 6, 8, 5, 3, 7, 9, 1], <br>
 * [7, 1, 3, 9, 2, 4, 8, 5, 6], <br>
 * [9, 0, 1, 5, 3, 7, 2, 1, 4], <br>
 * [2, 8, 7, 4, 1, 9, 6, 3, 5], <br>
 * [3, 0, 0, 4, 8, 1, 1, 7, 9] ]); <br>
 * // => false
 *
 * @version 1.0
 * @author Created by ivan at 11:06.
 */
public class SudokuValidator {
  public static boolean check(int[][] sudoku) { // do your magic
    int[][] heng = new int[10][10];
    int[][] shu = new int[10][10];
    int[][] three = new int[10][10];
    for (int x = 0; x < 9; x++) {
      for (int y = 0; y < 9; y++) {
        int n = sudoku[x][y];
        int m = sudoku[y][x];
        if (n == 0 || m == 0) {
          return false;
        }
        if (heng[x][n] != 0) {
          return false;
        } else {
          heng[x][n] = 1;
        }
        if (shu[x][m] != 0) {
          return false;
        } else {
          shu[x][m] = 1;
        }
        if (three[x / 3 * 3 + y / 3][n] != 0) {
          return false;
        } else {
          three[x / 3 * 3 + y / 3][n] = 1;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[][] sudoku = {
      {5, 3, 4, 6, 7, 8, 9, 1, 2},
      {6, 7, 2, 1, 9, 5, 3, 4, 8},
      {1, 9, 8, 3, 4, 2, 5, 6, 7},
      {8, 5, 9, 7, 6, 1, 4, 2, 3},
      {4, 2, 6, 8, 5, 3, 7, 9, 1},
      {7, 1, 3, 9, 2, 4, 8, 5, 6},
      {9, 6, 1, 5, 3, 7, 2, 8, 4},
      {2, 8, 7, 4, 1, 9, 6, 3, 5},
      {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };
    System.out.println(SudokuValidator.check(sudoku));
    sudoku[0][0]++;
    sudoku[1][1]++;
    sudoku[0][1]--;
    sudoku[1][0]--;
    System.out.println(SudokuValidator.check(sudoku));
    sudoku[0][0]--;
    sudoku[1][1]--;
    sudoku[0][1]++;
    sudoku[1][0]++;
    sudoku[4][4] = 0;
    System.out.println(SudokuValidator.check(sudoku));
  }
}
