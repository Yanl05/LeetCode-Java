/**
 * @author yanl
 * @date 2020-01-13 8:58 上午
 */
public class OneHundredThirty {
    public void solve(char[][] board) {
        if (board==null || board.length==0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // 从边缘O开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m-1 || j == n-1;
                if(isEdge && board[i][j]=='O'){
                    dfs(board, i, j);
                }
            }
        }
        // 除边缘外所有的O替换成X，并将边缘部分替换成#的换回成O
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    public void dfs(char[][] board, int i, int j){
        if(i<0 || j<0 || i>=board.length || j>=board[0].length
                || board[i][j]=='X' || board[i][j]=='#'){
            return;
        }
        board[i][j] = '#';
        dfs(board, i-1, j);  // up
        dfs(board, i, j-1);  // left
        dfs(board, i+1, j);  // down
        dfs(board, i, j+1);  // right
    }


    public static void main(String[] args) {
        char[][] board = {{'X','X' ,'X' ,'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X'}};
        OneHundredThirty oneHundredThirty = new OneHundredThirty();
        oneHundredThirty.solve(board);
        for (char[] board1 :
                board) {
            for (char bo :
                    board1) {
                System.out.print(bo);
            }
            System.out.println();
        }
    }

}
