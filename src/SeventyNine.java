/**
 * @author yanl
 * @date 2020-01-07 8:35 上午
 */
class Solution {

    private boolean[][] marked;
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    // 行，列
    private int m;
    private int n;
    private String word;
    private char[][] board;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        if (m == 0) {
            return false;
        }
        n = board[0].length;
        marked = new boolean[m][n];
        this.board = board;
        this.word = word;

        // dfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int i, int j, int start) {
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (inArea(newX, newY) && !marked[newX][newY]) {
                    if(dfs(newX, newY, start + 1)){
                        return true;
                    }
                }
            }
            // 将之前设定为true的改成false，因为没有走下去
            marked[i][j]=false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

}

public class SeventyNine {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
        System.out.println(solution.exist(board, "ABCCED"));
    }
}
