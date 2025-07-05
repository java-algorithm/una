/**
 * 리트코드 36번 Valid Sudoku
 * - 구현
 */

import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] selected = new boolean[10];

        // 행 검사
        for (int i = 0; i < 9; i++) {
            selected = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] - '0' >= 1 && board[i][j] - '0' <= 9) {
                    if (selected[board[i][j] - '0']) return false;
                    selected[board[i][j] - '0'] = true;
                }
            }
        }
        
        // 열 검사
        for (int i = 0; i < 9; i++) {
            selected = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] - '0' >= 1 && board[j][i] - '0' <= 9) {
                    if (selected[board[j][i] - '0']) return false;
                    selected[board[j][i] - '0'] = true;
                }
            }
        }
        
        // 3x3 검사
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                selected = new boolean[10];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[3 * k + i][3 * l + j] - '0' >= 1 && board[3 * k + i][3 * l + j] - '0' <= 9) {
                            if (selected[board[3 * k + i][3 * l + j] - '0']) return false;
                            selected[board[3 * k + i][3 * l + j] - '0'] = true;
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
