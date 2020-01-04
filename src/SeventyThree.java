import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yanl
 * @date 2020-01-03 4:54 下午
 */

public class SeventyThree {
    public void setZeroes(int[][] matrix) {
//        方法一：使用额外的空间
//        // 行
//        int R = matrix.length;
//        // 列
//        int C = matrix[0].length;
//
//        Set<Integer> rows = new HashSet<>();
//        Set<Integer> cols = new HashSet<>();
//
//        // 通过额外空间储存为0的位置
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                if (matrix[i][j] == 0){
//                    rows.add(i);
//                    cols.add(j);
//                }
//            }
//        }
//
//        // 通过额外的set集合来给行列赋0
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                if (rows.contains(i) || cols.contains(j)){
//                    matrix[i][j] = 0;
//                }
//            }
//        }

        // 方法二： O(1)空间的暴力
        //        同理，将为0的值所在行与列添加到一个set中，通过第二次遍历来赋0

        // 方法三：
        // 推迟对行和列赋零的操作。 空间复杂度o(1)
        int R = matrix.length;
        int C = matrix[0].length;
        // 设置一个flag，看第一列是否需要置0
        boolean isCol = false;

        for (int i = 0; i < R; i++){
            if (matrix[i][0] == 0){
                isCol = true;
            }
            for (int j = 0; j < C; j++){
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < R; i++){
            for (int j = 1; j < C; j++){
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        // 第一列
        if (isCol){
            for (int tmpj = 0; tmpj < R; tmpj++){
                matrix[tmpj][0] = 0;
            }
        }
        // 第一行
        if (matrix[0][0] == 0){
            for (int tmpj = 0; tmpj < C; tmpj++){
                matrix[0][tmpj] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0},
                          {3, 4, 5, 2},
                          {1, 3, 1, 5}};
        SeventyThree seventyThree = new SeventyThree();
        seventyThree.setZeroes(matrix);

        // 展示处理后的数组
        for (int[] ints: matrix) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j]+" ");
            }
            System.out.println();
        }
    }
}
