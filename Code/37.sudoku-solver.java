// Category: algorithms
// Level: Hard
// Percent: 65.266785%



// Write a program to solve a Sudoku puzzle by filling the empty cells.
// 
// A sudoku solution must satisfy all of the following rules:
// 
// 
// 	Each of the digits 1-9 must occur exactly once in each row.
// 	Each of the digits 1-9 must occur exactly once in each column.
// 	Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
// 
// 
// The '.' character indicates empty cells.
// 
//  
// Example 1:
// 
// Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
// Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
// Explanation: The input board is shown above and the only valid solution is shown below:
// 
// 
// 
// 
//  
// Constraints:
// 
// 
// 	board.length == 9
// 	board[i].length == 9
// 	board[i][j] is a digit or '.'.
// 	It is guaranteed that the input board has only one solution.
// 

import java.util.HashSet;

class Solution {

    public Boolean solveSudoku(
        int row,
        int col,
        char[][] board,
        HashSet<Character>[] rows,
        HashSet<Character>[] columns,
        HashSet<Character>[][] boxes
    ) {
        // If out of bounds -> Solved!
        if (col == 9) {
            return true;
        }

        // For the recursive call
        int recursiveRow = row + 1;
        int recursiveCol = col;
        if (recursiveRow > 8) {
            recursiveRow = 0;
            recursiveCol++;
        }

        if (board[row][col] != '.') {
            // Number placed already -> Go to next cell
            return solveSudoku(recursiveRow, recursiveCol, board, rows, columns, boxes);
        }

        for (int newValue = 1; newValue <= 9; newValue++) {

            char cellValue = Integer.toString(newValue).charAt(0);

            if (rows[row].contains(cellValue)
            || columns[col].contains(cellValue) 
            || boxes[row / 3][col / 3].contains(cellValue)
            ) {
                continue; // Go to the next newValue
            }

            rows[row].add(cellValue);
            columns[col].add(cellValue);
            boxes[row / 3][col / 3].add(cellValue);

            board[row][col] = cellValue;

            // Successfully modified board!
            if (solveSudoku(recursiveRow, recursiveCol, board, rows, columns, boxes)) {
                return true;
            } else {
                // Unsuccessful, revert actions
                rows[row].remove(cellValue);
                columns[col].remove(cellValue);
                boxes[row / 3][col / 3].remove(cellValue);
                board[row][col] = '.';
            }
        }

        return false;
    }
    public void solveSudoku(char[][] board) {
        HashSet<Character>[] rows = (HashSet<Character>[]) new HashSet[9];
        HashSet<Character>[] columns = (HashSet<Character>[]) new HashSet[9];
        HashSet<Character>[][] boxes = (HashSet<Character>[][]) new HashSet[3][3];
        // Initialization
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            columns[i] = new HashSet<>();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = new HashSet<>();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];
                if (val == '.') {
                    continue;
                }
                rows[i].add(val);
                columns[j].add(val);
                boxes[i / 3][j / 3].add(val);
            }
        }
        solveSudoku(0, 0, board, rows, columns, boxes);
    }
}
